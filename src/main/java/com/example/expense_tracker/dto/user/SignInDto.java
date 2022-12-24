package com.example.expense_tracker.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInDto {

    private String email;
    private String password;



    public SignInDto(String email, String password){
        this.email= email;
        this.password = password;

    }
}
