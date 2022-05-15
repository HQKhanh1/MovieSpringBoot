package com.example.demo.service;

import com.example.demo.DTO.MovieDetailDTO;
import com.example.demo.DTO.MovieDetailPage;
import com.example.demo.model.MovieDetail;

import java.util.List;

public interface MovieDetailService {
    List<MovieDetailDTO> getAllMovie();

    MovieDetailDTO getMovieById(int movieId);

    MovieDetailPage getAllMovieDetailPage(int pageNo, int pageSize, String sortBy, String sortDir);

    MovieDetailDTO addMovieDetail(MovieDetail movieDetailDTO) throws Exception;

    MovieDetail editMovieDetail(MovieDetail movieDetailDTO) throws Exception;

    MovieDetail deleteMovieDetail(int id) throws Exception;

    MovieDetail getMovieDetailByTitle(String title);
}
