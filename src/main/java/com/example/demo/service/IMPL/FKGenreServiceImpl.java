package com.example.demo.service.IMPL;

import com.example.demo.DTO.MovieDetailDTO;
import com.example.demo.DTO.MovieGenreDTO;
import com.example.demo.map.MovieDetailMap;
import com.example.demo.map.MovieGenreMap;
import com.example.demo.model.FKGenre;
import com.example.demo.repository.FKGenreRepository;
import com.example.demo.service.FKGenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FKGenreServiceImpl implements FKGenreService {
    private final FKGenreRepository fkGenreRepository;
    private final MovieDetailMap movieDetailMap;
    private final MovieGenreMap movieGenreMap;

    @Override
    public List<MovieGenreDTO> getGenreOnMovie(int movieId) {
        List<MovieGenreDTO> movieGenreDTOS = new ArrayList<>();
        List<FKGenre> fkGenres = fkGenreRepository.findAll();
        fkGenres.forEach(fkGenre -> {
            if (fkGenre.getMovieDetail().getId() == movieId) {
                movieGenreDTOS.add(movieGenreMap.movieGenreToDTO(fkGenre.getMovieGenre()));
            }
        });
        return movieGenreDTOS;
    }

    @Override
    public List<MovieDetailDTO> getMovieOnGenre(int genreId) {
        List<MovieDetailDTO> movieDetailDTOS = new ArrayList<>();
        List<FKGenre> fkGenres = fkGenreRepository.findAll();
        fkGenres.forEach(fkGenre -> {
            if (fkGenre.getMovieGenre().getId() == genreId){
                movieDetailDTOS.add(movieDetailMap.movieDetailToDTO(fkGenre.getMovieDetail()));
            }
        });
        return movieDetailDTOS;
    }

    @Override
    public void deleteByGenreId(int genreId) {
        List<FKGenre> fkGenres = fkGenreRepository.findAll();
        fkGenres.forEach(fkGenre -> {
            if (fkGenre.getMovieGenre().getId() == genreId) {
                fkGenreRepository.delete(fkGenre);
            }
        });
    }

    @Override
    public void deleteByMovieId(int movieId) {
        List<FKGenre> fkGenres = fkGenreRepository.findAll();
        fkGenres.forEach(fkGenre -> {
            if (fkGenre.getMovieDetail().getId() == movieId) {
                fkGenreRepository.delete(fkGenre);
            }
        });
    }
}
