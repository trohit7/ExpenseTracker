package com.example.expense_tracker.dto.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInResponseDto {
    private String status;
    private String token;



    public  SignInResponseDto(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
