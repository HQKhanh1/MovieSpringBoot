package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRoleDTO {
    private int id;
    private String name;
    private List<MovieAccountDTO> rolesForAccount;

    AccountRoleDTO(int id, String name){
        this.id = id;
        this.name = name;
    }
}
