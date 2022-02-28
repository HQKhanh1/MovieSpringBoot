package com.example.demo.service;

import com.example.demo.model.AccountRole;
import com.example.demo.model.Account;

public interface RoleForAccountService {
    void deleteRole(int userId);

    void addRoleForAccount(Account account, AccountRole accountRole);
}
