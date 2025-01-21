package com.ImageProcessing.User.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ImageProcessing.User.Dtos.LoginRequest;
import com.ImageProcessing.User.Dtos.UserRequest;
import com.ImageProcessing.User.Dtos.UserResponse;
import com.ImageProcessing.User.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.register(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(
                loginRequest.getEmail(),
                loginRequest.getPassword()));
    }
}
