package com.example.demo.service;

import com.example.demo.DTO.MovieEvaluateDTO;

public interface MovieEvaluateService {
    //add EvaluateMovie
    String addEvaluateMovie(MovieEvaluateDTO movieEvaluateDTO, int accountId, int movieId);

    void deleteMovieEvaluateByMovieId(int movieId);

    void deleteMovieEvaluateByUserId(int userId);

}
