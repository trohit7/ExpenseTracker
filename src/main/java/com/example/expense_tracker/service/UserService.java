package com.example.expense_tracker.service;

import com.example.expense_tracker.config.MessageStrings;
import com.example.expense_tracker.dto.ResponseDto;
import com.example.expense_tracker.dto.User.*;
import com.example.expense_tracker.enums.ResponseStatus;
import com.example.expense_tracker.enums.Role;
import com.example.expense_tracker.exceptions.AuthenticationFailException;
import com.example.expense_tracker.exceptions.CustomException;
import com.example.expense_tracker.model.AuthenticationToken;
import com.example.expense_tracker.model.User;
import com.example.expense_tracker.repository.UserRepository;
import com.example.expense_tracker.utlis.Helper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.expense_tracker.config.MessageStrings.USER_CREATED;

@Slf4j
@Service
public class UserService {


    @Autowired
    AuthenticationService authenticationService;


    @Autowired
    UserRepository userRepository;


    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public ResponseDto signUp(SignUpDto signupDto){
        // check if the user is already  present
        if (Helper.notNull(userRepository.findByEmail(signupDto.getEmail()))){
            //if we have the user already present then we will handle with Exceptions
            throw new CustomException("user already present ");
        }
        // hash(encrypting) the password

        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // create a new user
        User user = new User(signupDto.getFirstName(),signupDto.getLastName(),signupDto.getEmail(),encryptedpassword);
        userRepository.save(user);
        // save the user with encrypted password
        // now create the token for new user
        String token = UUID.randomUUID().toString();
         AuthenticationToken authenticationToken =  new AuthenticationToken(

                 token,
                 LocalDateTime.now(),
                 LocalDateTime.now().plusMinutes(15),
                 user

        );
        authenticationService.saveConfirmationToken(authenticationToken);

        //sendVerficationEmail()

        ResponseDto responseDto = new ResponseDto("success","new user is signedup");
        return responseDto;

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;

    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        // first check  user email address in the db is present or not
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(!Helper.notNull(user)){
            throw new AuthenticationFailException("User is not valid");
        }
        // if User found, hash the password and match with the encrypted password which was stored in the db
        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                // if password does not match , then throw exception
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // if the password matches then get authentication token
        AuthenticationToken token= authenticationService.getToken(user);

        if(! Helper.notNull(token)){
            throw new CustomException("token is not present");
        }

        return new SignInResponseDto("success",token.getToken());

    }









    boolean canCrudUser(Role role) {
        if (role == Role.admin || role == Role.manager) {
            return true;
        }
        return false;
    }

    boolean canCrudUser(User userUpdating, Integer userIdBeingUpdated) {
        Role role = userUpdating.getRole();
        // admin and manager can crud any user
        if (role == Role.admin || role == Role.manager) {
            return true;
        }
        // user can update his own record, but not his role
        if (role == Role.user && userUpdating.getId() == userIdBeingUpdated) {
            return true;
        }
        return false;
    }


    public ResponseDto createUser(String token, UserCreateDto userCreateDto) throws  CustomException, AuthenticationFailException {
        User creatingUser = authenticationService.getUser(token);
        if(!canCrudUser(creatingUser.getRole())) {
            // user can't create new user
            throw  new AuthenticationFailException(MessageStrings.USER_NOT_PERMITTED);
        }
        String encryptedPassword = userCreateDto.getPassword();
        try {
            encryptedPassword=hashPassword(userCreateDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(userCreateDto.getFirstName(),userCreateDto.getFirstName(), userCreateDto.getEmail(), userCreateDto.getRole(),encryptedPassword);
        User createdUser;
        try {
            createdUser = userRepository.save(user);
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ResponseDto(ResponseStatus.success.toString(), USER_CREATED);

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }



    }
}
