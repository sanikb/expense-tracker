package com.expense.jforce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private int userId;

    @NotBlank(message = "username is required")
    private String userName;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "fullname is required")
    private String fullName;
}
