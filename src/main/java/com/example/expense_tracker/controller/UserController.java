package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.ResponseDto;
import com.example.expense_tracker.dto.User.*;
import com.example.expense_tracker.exceptions.AuthenticationFailException;
import com.example.expense_tracker.exceptions.CustomException;
import com.example.expense_tracker.model.User;
import com.example.expense_tracker.repository.UserRepository;
import com.example.expense_tracker.service.AuthenticationService;
import com.example.expense_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }

    @PostMapping("/signUp")
    public ResponseDto signup(@RequestBody SignUpDto signupDto){
        return userService.signUp(signupDto);
    }


    @PostMapping("/signIn")
    public SignInResponseDto signup(@RequestBody SignInDto signInDto){
        return userService.signIn(signInDto);
    }

    @PostMapping("/createUser")
    public ResponseDto createUser(@RequestParam("token") String token, @RequestBody UserCreateDto userCreateDto)
            throws CustomException, AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.createUser(token, userCreateDto);
    }

}
//    @PostMapping("/updateUser")
//    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserUpdateDto userUpdateDto) {
//        authenticationService.authenticate(token);
//        return userService.updateUser(token, userUpdateDto);
//    }


//    @PostMapping("/createUser")
//    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserCreateDto userCreateDto)
//            throws CustomException, AuthenticationFailException {
//        authenticationService.authenticate(token);
//        return userService.createUser(token, userCreateDto);
//    }



