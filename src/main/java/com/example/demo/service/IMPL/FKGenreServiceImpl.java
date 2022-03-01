package com.example.demo.service.IMPL;

import com.example.demo.model.FKGenre;
import com.example.demo.repository.FKGenreRepository;
import com.example.demo.service.FKGenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FKGenreServiceImpl implements FKGenreService {
    private final FKGenreRepository fkGenreRepository;

    @Override
    public void deleteByGenreId(int genreId) {
        List<FKGenre> fkGenres = fkGenreRepository.findAll();
        fkGenres.forEach(fkGenre -> {
            if (fkGenre.getMovieGenre().getId() == genreId) {
                fkGenreRepository.delete(fkGenre);
            }
        });
    }
}
