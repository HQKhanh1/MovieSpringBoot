package com.example.demo.service;


import com.example.demo.model.AccountRole;

import java.util.List;

public interface MovieAccountService {
    public List<AccountRole> getFullRoleOnAccount(int id);
}
