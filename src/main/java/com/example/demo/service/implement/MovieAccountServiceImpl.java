package com.example.demo.service.implement;

import com.example.demo.model.AccountRole;
import com.example.demo.repository.MovieAccountRepository;
import com.example.demo.service.MovieAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieAccountServiceImpl implements MovieAccountService {
    private final MovieAccountRepository movieAccountRepository;
    @Override
    public List<AccountRole> getFullRoleOnAccount(int id) {
        return movieAccountRepository.getById(id).getAccountRoles();
    }
}
