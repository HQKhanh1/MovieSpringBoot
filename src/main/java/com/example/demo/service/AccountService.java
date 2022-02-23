package com.example.demo.service;


import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.AccountResponse;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.exception.MailException;
import com.example.demo.exception.UsernameExitException;
import com.example.demo.model.AccountRole;
import com.example.demo.model.Account;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    List<AccountDTO> getAccountByEnabled(boolean check);
    List<AccountRole> getFullRoleOnAccount(Account account);
    AccountDTO getAccountById(int accountId);
    AccountDTO getAccountByUsernameDTO(String accountUsername);
    Account getAccountByUsername(String accountUsername);
    boolean checkPasswordForAccount(Account account, String currentPassword);
    String changePasswordForAccount(Account account, String passwordChange);
    String createAccount(RegisterRequest registerRequest, int roleId) throws MailException, UsernameExitException;
    AccountResponse getAllUsersPage(int pageNo, int pageSize, String sortBy, String sortDir);

}
