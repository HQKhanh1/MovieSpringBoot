package com.example.demo.dto;

import com.example.demo.model.address.Town;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;

    private String password;

    private String email;

    private String avatar;

    private String firstname;

    private String lastname;

    private Town idTown;

    private String address;

    private Date birthday;

    private boolean gender;

}
