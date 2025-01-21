package com.ImageProcessing.User.Dtos;

import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
