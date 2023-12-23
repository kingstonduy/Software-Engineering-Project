package com.petcare.rest.webservices.restful.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.rest.webservices.restful.model.OrderedProductDTO;
import com.petcare.rest.webservices.restful.model.OtpVerificationRequest;
import com.petcare.rest.webservices.restful.model.User;
import com.petcare.rest.webservices.restful.model.UserChangeInformation;
import com.petcare.rest.webservices.restful.service.UserService;

@RestController
@RequestMapping("is/v1")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> retrieveUsers(){
        return userService.retrieveUsers();
    }

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username){
        return userService.getUserbyUsername(username);
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User userRequest) {
        boolean ok = userService.login(userRequest);
        System.out.println("login is called sth:" + userRequest);
        if (ok == true) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/users/{username}/{password}")
    public User getUserbyUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        return userService.getUserbyUsernameAndPassword(username,password);
    }
    @PostMapping("/register")
    public ResponseEntity<User> Register(@RequestBody User userRegister){
       User user = userService.register(userRegister);
        if(user != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<User> verify(@RequestBody OtpVerificationRequest otpVerificationRequest){
        boolean isVerified = userService.verfifyOtp(otpVerificationRequest);
        if(isVerified){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/users/{username}/orederedProducts")
    public List<OrderedProductDTO> getOrderedProductEachUser(@PathVariable  String username){
        return  userService.getOrderedProductEachUser(username);

    }

    @GetMapping("/user/userChangeInformation/{username}")
    public UserChangeInformation getUserChangeInformation(@PathVariable String username){
        return userService.getUserChangeInformation(username);

    }

    @PutMapping("user/userChangeInformation/update")
    public ResponseEntity<User> ChangeUserInformation(@RequestBody UserChangeInformation
            userChangeInformation)

    {
        System.out.println(userChangeInformation);
        User user = userService.ChangeUserInformation(userChangeInformation);
        System.out.println("KO CÓ LỖI NÈEEEEE" + user.getUserFullName());
        if(user != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}


