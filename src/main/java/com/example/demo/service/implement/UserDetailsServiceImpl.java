package com.example.demo.service.implement;

import com.example.demo.model.MovieAccount;
import com.example.demo.repository.MovieAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MovieAccountRepository movieAccountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MovieAccount movieAccount = movieAccountRepository.findMovieAccountByUsername((username));
        if (movieAccount == null) {
            throw new UsernameNotFoundException("User not Found with username: " + username);
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            movieAccount.getAccountRoles().forEach(role -> {
                System.out.printf("\n\n\n\n Role: "+role.getAccountRole().getName());
                authorities.add(new SimpleGrantedAuthority(role.getAccountRole().getName()));
            });
            return new User(
                    movieAccount.getUsername(),
                    movieAccount.getPassword(),
                    movieAccount.isEnabled(),
                    true,
                    true,
                    true,
                    authorities);
        }
    }

}
