package com.example.demo.service.implement;

import com.example.demo.model.AccountRole;
import com.example.demo.model.Key.RoleForAccountKey;
import com.example.demo.model.MovieAccount;
import com.example.demo.model.RoleForAccount;
import com.example.demo.repository.RoleForAccountRepository;
import com.example.demo.service.RoleForAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleForAccountServiceImpl implements RoleForAccountService {
    private final RoleForAccountRepository roleForAccountRepository;
    @Override
    public void addRoleForAccount(MovieAccount movieAccount, AccountRole accountRole) {
        RoleForAccountKey roleForAccountKey = new RoleForAccountKey();
        roleForAccountKey.setAccountId(movieAccount.getId());
        roleForAccountKey.setRoleId(accountRole.getId());
        RoleForAccount roleForAccount = new RoleForAccount();
        roleForAccount.setId(roleForAccountKey);
        roleForAccount.setAccountRole(accountRole);
        roleForAccount.setMovieAccount(movieAccount);
        roleForAccountRepository.save(roleForAccount);
    }
}
