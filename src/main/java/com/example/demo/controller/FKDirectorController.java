package com.example.demo.controller;

import com.example.demo.service.FKDirectorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/fkDirector")
public class FKDirectorController {
    private final FKDirectorService fkDirectorService;

}
