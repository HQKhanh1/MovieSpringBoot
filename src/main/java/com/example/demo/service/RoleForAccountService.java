package com.example.demo.service;

import com.example.demo.model.AccountRole;
import com.example.demo.model.Account;

public interface RoleForAccountService {
    public void addRoleForAccount(Account account, AccountRole accountRole);
}
