package com.example.demo.service;


import com.example.demo.model.MovieAccount;

public interface SendMailService {
    Boolean signup(MovieAccount sdi, String token);
    Boolean create(MovieAccount sdi, String password);
    void forgotPassword(MovieAccount sdi, String password);
}
