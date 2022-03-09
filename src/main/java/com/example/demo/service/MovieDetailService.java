package com.example.demo.service;

import com.example.demo.DTO.MovieDetailDTO;
import com.example.demo.DTO.MovieDetailPage;

import java.util.List;

public interface MovieDetailService {
    List<MovieDetailDTO> getAllMovie();

    MovieDetailDTO getMovieById(int movieId);

    MovieDetailPage getAllMovieDetailPage(int pageNo, int pageSize, String sortBy, String sortDir);

    String addMovieDetail(MovieDetailDTO movieDetailDTO) throws Exception;

    String editMovieDetail(MovieDetailDTO movieDetailDTO) throws Exception;

    String deleteMovieDetail(int id) throws Exception;
}
