package com.example.expense_tracker.service;




import com.example.expense_tracker.exceptions.AuthenticationFailException;
import com.example.expense_tracker.model.AuthenticationToken;
import com.example.expense_tracker.model.User;
import com.example.expense_tracker.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken );
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }


    public User getUser(String token){
        final AuthenticationToken authenticationToken =  tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)) {
            return null;
        }
        //authentication is not null
        return authenticationToken.getUser();
    }

    public void authenticate(String token ){
        // check whether it is present or not (null check)
        if(Objects.isNull(token)){
            throw new AuthenticationFailException("TOKEN not present");
        }
        if (Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("Token not valid");
        }

    }
}
