package com.biologix.v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biologix.v1.entities.ApiResponse;
import com.biologix.v1.entities.User;
import com.biologix.v1.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController 
{


    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User u)
    {
        return ResponseEntity.ok(userService.register(u));
    }

    @GetMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestParam("email") String email, @RequestParam("password") String password)
    {

        return ResponseEntity.ok(
            ApiResponse.builder()
            .message("success")
            .status(userService.login(email, password))
            .build()
            );
    }

    @GetMapping("info")
    public ResponseEntity<User> userInfo(@RequestParam("email") String email)
    {
        return ResponseEntity.ok(userService.userInfo(email));
    }
}
