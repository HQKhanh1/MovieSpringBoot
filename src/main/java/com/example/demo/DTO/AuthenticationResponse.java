package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
//    private String refreshToken;
//    private Instant expiresAt;
    private String username;
//    private List<AccountRoleDTO> accountRoleDTOS;
}
