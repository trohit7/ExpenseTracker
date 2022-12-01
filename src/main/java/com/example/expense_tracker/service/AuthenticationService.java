package com.example.expense_tracker.service;


import com.example.expense_tracker.exceptions.AuthenticationFailException;
import com.example.expense_tracker.model.AuthenticationToken;
import com.example.expense_tracker.model.User;
import com.example.expense_tracker.repository.TokenRepository;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    @Autowired
    TokenRepository repository;





    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return repository.findByUser(user);
    }

    public Optional<AuthenticationToken> getToken(String token) {
        return Optional.ofNullable(TokenRepository.findByToken(token));
    }

    public int setConfirmedAt(String token) {
        return TokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }


    public User getUser(String token){
        final AuthenticationToken authenticationToken =  TokenRepository.findByToken(token);
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

//    public User getUser(String token) {
//        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
//        if (Helper.notNull(authenticationToken)) {
//            if (Helper.notNull(authenticationToken.getUser())) {
//                return authenticationToken.getUser();
//            }
//        }
//        return null;
//    }
//
//    public void authenticate(String token) throws AuthenticationFailException {
//        if (!Helper.notNull(token)) {
//            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
//        }
//        if (!Helper.notNull(getUser(token))) {
//            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
//        }
//
//    }
}
