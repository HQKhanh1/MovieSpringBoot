package com.example.demo.controller;

import com.example.demo.model.AccountRole;
import com.example.demo.service.AccountRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class testController {
    private final AccountRoleService accountRoleService;
    @GetMapping("/role")
    private ResponseEntity<List<AccountRole>> getRole(){
        return new ResponseEntity<List<AccountRole>>(accountRoleService.getAllAccountRole(), HttpStatus.OK);
    }
}
