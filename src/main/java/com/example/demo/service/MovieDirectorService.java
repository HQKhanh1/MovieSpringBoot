package com.example.demo.service;

import com.example.demo.DTO.DirectorPage;
import com.example.demo.DTO.MovieDirectorDTO;

import java.util.List;

public interface MovieDirectorService {
    List<MovieDirectorDTO> getAllMovieDirector();

    MovieDirectorDTO getMovieDirectorById(int id);

    void deleteMovieDirectorById(int id);

    void createMovieDirector(MovieDirectorDTO movieDirectorDTO);

    void editMovieDirector(MovieDirectorDTO movieDirectorDTO);

    DirectorPage getAllDirectorPage(int pageNo, int pageSize, String sortBy, String sortDir);

}
