package com.example.demo.service;

import com.example.demo.DTO.AuthenticationResponse;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RegisterRequest;

public interface AuthService {
    void signup(RegisterRequest registerRequest);

    void verifyAccount(String token);

    AuthenticationResponse login(LoginRequest loginRequest);
}
