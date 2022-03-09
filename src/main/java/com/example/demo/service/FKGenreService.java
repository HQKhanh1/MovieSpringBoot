package com.example.demo.service;

import com.example.demo.DTO.MovieDetailDTO;
import com.example.demo.DTO.MovieGenreDTO;

import java.util.List;

public interface FKGenreService {
    //get all genre on a movie
    List<MovieGenreDTO> getGenreOnMovie(int movieId);

    //get all movie on a genre
    List<MovieDetailDTO> getMovieOnGenre(int genreId);

    void deleteByGenreId(int genreId);

    void deleteByMovieId(int movieId);
}
