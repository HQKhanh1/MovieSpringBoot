package com.example.demo.service.IMPL;

import com.example.demo.DTO.AccountRoleDTO;
import com.example.demo.model.AccountRole;
import com.example.demo.repository.AccountRoleRepository;
import com.example.demo.service.AccountRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AccountRoleServiceImpl implements AccountRoleService {
    private final AccountRoleRepository accountRoleRepository;
    @Override
    public List<AccountRole> getAllAccountRole() {
        return accountRoleRepository.findAll();
    }

    @Override
    public List<AccountRoleDTO> getAccountRolesForUsername(String username) {
        return null;
    }
}
