package com.example.expense_tracker.dto.user;

import com.example.expense_tracker.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String email;

    private Role role;
    private String password;


}
