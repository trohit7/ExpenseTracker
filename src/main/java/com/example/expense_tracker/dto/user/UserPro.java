package com.example.expense_tracker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserPro {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
