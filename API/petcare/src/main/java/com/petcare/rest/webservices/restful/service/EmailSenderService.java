package com.petcare.rest.webservices.restful.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.petcare.rest.webservices.restful.config.EmailConfig;
import com.petcare.rest.webservices.restful.model.OtpRequest;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
    // @Autowired
    // private JavaMailSender mailSender;

    @Autowired
    private EmailConfig emailConfig;
    
    @Autowired
    SmtpAuthenticator authentication;

    public ResponseEntity<String> sendEmail(OtpRequest otpRequest) {
        String body = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"><title>Static Template</title><link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap\" rel=\"stylesheet\"></head><body style=\"margin:0;font-family:Poppins,sans-serif;background:#fff;font-size:14px\"><div style=\"max-width:680px;margin:0 auto;padding:45px 30px 60px;background:#f4f7ff;background-image:url(https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661497957196_595865/email-template-background-banner);background-repeat:no-repeat;background-size:800px 452px;background-position:top center;font-size:14px;color:#434343\"><header><table style=\"width:100%\"><tbody><tr style=\"height:0\"><td><img alt=\"\" src=\"https://raw.githubusercontent.com/kingstonduy/Pet-Care-Website/main/DATA/images/Dog-logo.png\" height=\"100px\" background-color=\"white\"></td><td style=\"text-align:right\"><span style=\"font-size:16px;line-height:30px;color:#fff\"></span></td></tr></tbody></table></header><main><div style=\"margin:0;margin-top:70px;padding:92px 30px 115px;background:#fff;border-radius:30px;text-align:center\"><div style=\"width:100%;max-width:489px;margin:0 auto\"><h1 style=\"margin:0;font-size:24px;font-weight:500;color:#1f1f1f\">Verify Your Email</h1><p style=\"margin:0;margin-top:17px;font-size:16px;font-weight:500\">Hey " +
                otpRequest.getUserUserName() +
                ",</p><p style=\"margin:0;margin-top:17px;font-weight:500;letter-spacing:.56px\">Thank you for choosing Petcare E-commerce application. So we can get you started, please enter the code below on the registration page. OTP is valid for<span style=\"font-weight:600;color:#1f1f1f\"> 5 minutes</span>. Do not share this code with others, including my teammates.</p><p style=\"margin:0;margin-top:60px;font-size:40px;font-weight:600;letter-spacing:25px;color:#ba3d4f\">" +
                otpRequest.getOtp() +
                "</p></div></div><p style=\"max-width:400px;margin:0 auto;margin-top:90px;text-align:center;font-weight:500;color:#8c8c8c\">Need help? Ask<a href=\"https://www.facebook.com/kduyyy\" style=\"color:#499fb6;text-decoration:none\"> Duy </a>or visit our <a href=\"https://github.com/HCMIU-Software-Engineering/Software-Engineering-Project\" target=\"_blank\" style=\"color:#499fb6;text-decoration:none\">Help Center</a></p></main><footer style=\"width:100%;max-width:490px;margin:20px auto 0;text-align:center;border-top:1px solid #e6ebf1\"><p style=\"margin:0;margin-top:40px;font-size:16px;font-weight:600;color:#434343\">Software Engineering Team 9</p><p style=\"margin:0;margin-top:8px;color:#434343\">Address Quarter 6, Linh Trung Ward, Thu Duc District, HCMC</p><div style=\"margin:0;margin-top:16px\"><a href=\"https://github.com/HCMIU-Software-Engineering/Software-Engineering-Project\" target=\"_blank\" style=\"display:inline-block\"><img width=\"36px\" alt=\"Facebook\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661502815169_682499/email-template-icon-facebook\"></a><a href=\"https://github.com/HCMIU-Software-Engineering/Software-Engineering-Project\" target=\"_blank\" style=\"display:inline-block;margin-left:8px\"><img width=\"36px\" alt=\"Instagram\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661504218208_684135/email-template-icon-instagram\"></a><a href=\"https://github.com/HCMIU-Software-Engineering/Software-Engineering-Project\" target=\"_blank\" style=\"display:inline-block;margin-left:8px\"><img width=\"36px\" alt=\"Twitter\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503043040_372004/email-template-icon-twitter\"></a><a href=\"https://github.com/HCMIU-Software-Engineering/Software-Engineering-Project\" target=\"_blank\" style=\"display:inline-block;margin-left:8px\"><img width=\"36px\" alt=\"Youtube\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503195931_210869/email-template-icon-youtube\"></a></div><p style=\"margin:0;margin-top:16px;color:#434343\">Copyright © 2023 Company. All rights reserved.</p></footer></div></body></html>";

        Properties props = new Properties();
        System.out.printf("💡emailConfig.getHOST_NAME: %s\n", emailConfig.getHOST_NAME());
        props.put("mail.smtp.host" , emailConfig.getHOST_NAME());
        props.put("mail.stmp.user" , emailConfig.getUSERNAME());
        //To use TLS
        props.put("mail.smtp.auth", emailConfig.getAUTH()); 
        props.put("mail.smtp.starttls.enable", emailConfig.getSTART_TLS());
        props.put("mail.smtp.password", emailConfig.getPASSWORD());
        //To use SSL
        props.put("mail.smtp.auth", emailConfig.getAUTH());
        props.put("mail.smtp.port", emailConfig.getPORT());

        Message message = new MimeMessage(Session.getDefaultInstance(props, authentication));
        
        try {
            message.setFrom(new InternetAddress(emailConfig.getUSERNAME()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(otpRequest.getUserEmail(), true));
            message.setSubject("Verify your email");
            message.setContent(body, "text/html; charset=UTF-8");
            message.saveChanges();
            
            Transport.send(message);
            
            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException e) {
           e.printStackTrace();
            return ResponseEntity.badRequest().body("Email can not be sent");
        }
    }
}