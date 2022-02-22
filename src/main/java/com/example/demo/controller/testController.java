package com.example.demo.controller;

import com.example.demo.model.MovieAccount;
import com.example.demo.service.MovieAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class testController {
    private final MovieAccountService movieAccountService;
    @GetMapping("/role")
    private ResponseEntity<MovieAccount> getRole(){
        return new ResponseEntity<>(movieAccountService.getFullRoleOnAccount(), HttpStatus.OK);
    }
}
