package com.example.expense_tracker.dto.User;

import com.example.expense_tracker.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String email;

    private Role role;
    private String password;


}
