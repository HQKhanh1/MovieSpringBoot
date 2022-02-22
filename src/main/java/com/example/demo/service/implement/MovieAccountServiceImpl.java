package com.example.demo.service.implement;

import com.example.demo.model.MovieAccount;
import com.example.demo.repository.MovieAccountRepository;
import com.example.demo.service.MovieAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieAccountServiceImpl implements MovieAccountService {
    private final MovieAccountRepository movieAccountRepository;
    @Override
    public MovieAccount getFullRoleOnAccount() {
        return movieAccountRepository.findMovieAccountByUsername("admin");
    }
}
