package com.example.expense_tracker.dto.email;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class OtpRequest {


    String userOtp;
    String emailId;


    public String getUserOtp() {
        return userOtp;
    }
    public void setUserOtp(String userOtp) {
        this.userOtp = userOtp;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

}
