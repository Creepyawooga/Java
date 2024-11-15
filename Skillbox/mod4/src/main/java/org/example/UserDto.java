package org.example;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

}