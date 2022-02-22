package com.example.demo.repository;

import com.example.demo.model.MovieAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieAccountRepository extends JpaRepository<MovieAccount, Integer> {
    MovieAccount findMovieAccountByUsername(String username);
    MovieAccount findMovieAccountByEmail(String email);
}
