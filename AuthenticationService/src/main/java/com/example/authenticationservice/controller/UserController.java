package com.example.authenticationservice.controller;

import com.example.authenticationservice.dto.BaseResponseDto;
import com.example.authenticationservice.dto.UserDTO;
import com.example.authenticationservice.model.User;
import com.example.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDto<String>> registerUser(@RequestBody User user) {
        boolean isCreated = userService.createUser(user);
        String message = isCreated ? "User registered successfully" : "User registration failed";
        return ResponseEntity.ok(new BaseResponseDto<>(message));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDto<String>> loginUser(@RequestBody UserDTO userDto) {
        if (!userService.checkUserNameExists(userDto.getEmail())) {
            return ResponseEntity.status(404).body(new BaseResponseDto<>("User does not exist"));
        }

        boolean isVerified = userService.verifyUser(userDto.getEmail(), userDto.getPassword());
        if (!isVerified) {
            return ResponseEntity.status(401).body(new BaseResponseDto<>("Wrong password"));
        }

        String token = userService.generateToken(userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok(new BaseResponseDto<>("Success", token));
    }
}
