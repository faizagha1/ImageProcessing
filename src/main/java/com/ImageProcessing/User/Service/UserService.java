package com.ImageProcessing.User.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ImageProcessing.User.Dtos.UserRequest;
import com.ImageProcessing.User.Dtos.UserResponse;
import com.ImageProcessing.User.Model.User;
import com.ImageProcessing.User.Repository.UserRepository;
import com.ImageProcessing.Utils.JwtGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    public UserResponse register(UserRequest userRequest) {
        User existingUser = userRepository.findByEmail(userRequest.getEmail())
                .orElse(null);
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = User
                .builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .userName(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(encodedPassword)
                .build();
        String token = jwtGenerator.generateToken(user.getUserName());
        userRepository.save(user);
        return new UserResponse(user.getUserName(), token);
    }

    public UserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password, try again");
        }
        String token = jwtGenerator.generateToken(user.getUserName());
        return new UserResponse(user.getUserName(), token);
    }
}
