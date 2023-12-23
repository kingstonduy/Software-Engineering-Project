package com.petcare.rest.webservices.restful.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.petcare.rest.webservices.restful.model.OrderedProduct;
import com.petcare.rest.webservices.restful.model.OrderedProductDTO;
import com.petcare.rest.webservices.restful.model.OtpRequest;
import com.petcare.rest.webservices.restful.model.OtpVerificationRequest;
import com.petcare.rest.webservices.restful.model.User;
import com.petcare.rest.webservices.restful.model.UserChangeInformation;
import com.petcare.rest.webservices.restful.repository.OrderedProductRepository;
import com.petcare.rest.webservices.restful.repository.ProductRepository;
import com.petcare.rest.webservices.restful.repository.UserRepository;

import lombok.Data;

@Service
@Data
public class UserService {
    UserRepository userRepository;
    OrderedProductRepository OrderedProductRepository;
    ProductRepository productRepository;

    @Autowired
    EmailSenderService emailService;


    // Constructor


    public UserService(UserRepository userRepository, OrderedProductRepository orderedProductRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        OrderedProductRepository = orderedProductRepository;
        this.productRepository = productRepository;
    }

    public List<User> retrieveUsers(){
        return userRepository.findAll();
    }

    public User getUser(Integer id){
        return  userRepository.findById(id).get();
    }

    public User getUserbyUsernameAndPassword(String username, String password){
        User user = userRepository.findByUserUserNameAndUserPassword(username,password);
        return user;
    }

    public boolean login(@RequestBody User userRequest) {
        User user = userRepository.findByUserUserNameAndUserPassword(userRequest.getUserUserName(), userRequest.getUserPassword());
        return user != null && user.getIsVerified() == true;

    }

    public User register(@RequestBody User user){
        // save a OTP version to db
        // then response an version without otp
        User savedUser = userRepository.findByUserUserName(user.getUserUserName());

        if(savedUser == null) {
            String otp = getOtp();

            user.setOtp(otp);
            user.setOtpTS(getOtpTS());
            user.setVerified(false);
            userRepository.save(user);

            emailService.sendEmail(new OtpRequest(user.getUserUserName(), user.getUserEmail(), otp));
            return user;
        } else if(savedUser.getIsVerified() == false) {
            return resendOtp(savedUser);
        } else {
            return null;
        }
    }

    public User resendOtp(@RequestBody User user) {
        User savedUser = userRepository.findByUserUserName(user.getUserUserName());
        savedUser.setOtp(getOtp());
        savedUser.setOtpTS(getOtpTS());
        savedUser.setVerified(false);

        userRepository.deleteUserByUserUserName(user.getUserUserName());
        userRepository.save(savedUser);
        return savedUser;
    }

    public boolean verfifyOtp(@RequestBody OtpVerificationRequest otpVerificationRequest) {
        User user = userRepository.findByUserUserName(otpVerificationRequest.getUserUserName());
        if(user != null) {
            if(user.getOtp().equals(otpVerificationRequest.getOtp())) {
                long otpTS = otpVerificationRequest.getTs();
                long now = getOtpTS();

                // set expiration time 5 minutes
                System.out.println("now" + now);
                System.out.println("otpTS" + otpTS);
                if(now - otpTS < 5 * 60 * 1000) {
                    user.setVerified(true);
                    userRepository.save(user);
                    return true;
                }
            }
        }
        return false;
    }

    public List<OrderedProductDTO> getOrderedProductEachUser(String username){
        User user= userRepository.findByUserUserName(username);
        List<OrderedProductDTO> orderedProductDTOS = new ArrayList<>();
        for(OrderedProduct orderedProduct : user.getOrderedProductsList()){
            OrderedProductDTO orderedProductDTO = new OrderedProductDTO();

            orderedProductDTO.setProductId(orderedProduct.getProduct().getId());
            orderedProductDTO.setOrderedProductId(orderedProduct.getId());
            orderedProductDTO.setOrderedProductDate(new Date());
            orderedProductDTO.setOrderedProductDTOProductName(orderedProduct.getProduct().getProductName());
            orderedProductDTO.setOrderedProductDTOQuantity(orderedProduct.getOrderedProductQuantity());
            orderedProductDTO.setOrderedProductDTOCategory(orderedProduct.getProduct().getProductCategory());
            orderedProductDTO.setOrderedProductDTOPrice(orderedProduct.getProduct().getProductPrice());
            orderedProductDTO.setOrderedProductDTOImageUrl(orderedProduct.getProduct().getProductImageUrl());

            orderedProductDTOS.add(orderedProductDTO);

        }
        return orderedProductDTOS;


    }

    public User getUserbyUsername(String username){
        User user = userRepository.findByUserUserName(username);
        return user;
    }

    public UserChangeInformation getUserChangeInformation(String username){
        User user = getUserbyUsername(username);
        if(user != null){
            UserChangeInformation userChangeInformation = new UserChangeInformation();
            userChangeInformation.setUserId(user.getId());
            userChangeInformation.setUsername(user.getUserUserName());
            userChangeInformation.setEmail(user.getUserEmail());
            userChangeInformation.setFullName(user.getUserFullName());

            return userChangeInformation;
        }
        return null;

    }

    public User ChangeUserInformation(UserChangeInformation userChangeInformation){

        User user = userRepository.findByUserUserName(userChangeInformation.getUsername());
        System.out.println(user.getId());
        if( user != null && user.getUserPassword().equals(userChangeInformation.getCurrentPassword())){
            System.out.println("VO DUOC DAY NE!!!!!");
            user.setUserFullName(userChangeInformation.getFullName());
            user.setUserPassword(userChangeInformation.getPassword());
            user.setUserEmail(userChangeInformation.getEmail());
            userRepository.save(user);
            return user;
        }
        System.out.println("LOI~ ROI!!!!!!!");
        return null;
    }

    private String getOtp() {
        List<String>digits = new ArrayList<>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));

        String res = "";

        for(int i = 1; i <= 6; i++) {
            int index = (int)(Math.random() * digits.size());
            res += digits.get(index);
        }

        return res;
    }

    private long getOtpTS() {
        Instant instant = Instant.now();  
        long timeStampMillis = instant.toEpochMilli();
        return timeStampMillis;
    }

}
