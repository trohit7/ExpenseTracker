package com.example.expense_tracker.controller;




import com.example.expense_tracker.dto.email.EmailDetails;
import com.example.expense_tracker.dto.email.EmailRequest;
import com.example.expense_tracker.dto.email.MessageResponse;
import com.example.expense_tracker.dto.email.OtpRequest;
import com.example.expense_tracker.dto.ResponseDto;
import com.example.expense_tracker.dto.user.*;
import com.example.expense_tracker.exceptions.AuthenticationFailException;
import com.example.expense_tracker.exceptions.CustomException;
import com.example.expense_tracker.model.User;
import com.example.expense_tracker.repository.UserRepository;
import com.example.expense_tracker.service.AuthenticationService;
import com.example.expense_tracker.service.EmailService;
import com.example.expense_tracker.service.UserService;
import com.example.expense_tracker.utlis.Helper;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Random;




@CrossOrigin(origins = "**  ",maxAge = 3600)
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;


    @Autowired
    EmailService emailService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }


    @PostMapping("/signUp")
    public ResponseDto signup(@RequestBody SignupDto signupDto) throws NoSuchAlgorithmException {
        return userService.signUp(signupDto);
    }


    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
        logger.debug("api is hitting");
        logger.error("function is not working");


        return userService.signIn(signInDto);
    }

    @PostMapping("/createUser")
    public ResponseDto createUser(@RequestParam("token") String token, @RequestBody UserCreateDto userCreateDto)
            throws IllegalArgumentException {
        authenticationService.authenticate(token);
        return userService.createUser(token, userCreateDto);
    }

    @PostMapping("/api/auth/forgotpassword")
    public MessageResponse forgotUserPassword(@RequestBody EmailRequest emailRequest) {

        if (!Helper.notNull(userRepository.findByEmail(emailRequest.getEmailId()))){
            //if we have the user already present then we will handle with Exceptions
            throw new CustomException("user is not present ");
        }

        logger.info("Email is",emailRequest.getEmailId());



        Random r = new Random( System.currentTimeMillis() );
        int otp=10000 + r.nextInt(20000);

        User updateUserOtp=userRepository.findByEmail(emailRequest.getEmailId()) ;

        if(updateUserOtp==null)
            logger.info("null ob");

        String mailId = emailRequest.getEmailId();

        Objects.requireNonNull(updateUserOtp).setUserOtp(String.valueOf(otp)) ;
        logger.info("userotp updated");


        userRepository.save(updateUserOtp);

       EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient(mailId);
        emailDetails.setMsgBody(String.valueOf(otp));
        emailDetails.setSubject("OTP");




        String status = emailService.sendSimpleMail(emailDetails);

        if(status==null)
        {
            logger.info("email is not sent");
        }


        return new MessageResponse(status);
    }






    @PostMapping("/api/auth/verifyOtp")
    public MessageResponse verifyUserOtp(@RequestBody OtpRequest otpRequest) {


        String dbOtp=userRepository.findByEmail(otpRequest.getEmailId()).getUserOtp();

        if(dbOtp.equals(otpRequest.getUserOtp()))
        {
            return new MessageResponse("OTP verified successfully");
        }

        return new MessageResponse("OTP mismatch");
    }


    @PostMapping("/api/auth/updatePassword")
    public MessageResponse updatePassword(@RequestBody UpdatePassword updatePassword) throws NoSuchAlgorithmException {
        return userService.updatePassword(updatePassword);
    }


    @PostMapping("/api/auth/signout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE)
                .body(new MessageResponse("You've been signed out!"));
    }

}
