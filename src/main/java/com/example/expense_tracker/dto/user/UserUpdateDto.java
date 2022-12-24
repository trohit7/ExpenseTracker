package com.example.expense_tracker.dto.user;

import com.example.expense_tracker.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Role role;


}
