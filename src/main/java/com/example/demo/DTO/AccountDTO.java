package com.example.demo.DTO;

import com.example.demo.DTO.address.TownDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    private String email;
    private String avatar;
    private String firstname;
    private String lastname;
    private Date birthday;
    private TownDTO town;
    private String address;
    private String phoneNumber;
    private boolean gender;
    public String getGender() {
        if (gender) {
            return "Male";
        } else {
            return "Female";
        }
    }
}