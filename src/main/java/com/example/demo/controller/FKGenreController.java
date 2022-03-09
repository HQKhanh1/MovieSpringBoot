package com.example.demo.controller;

import com.example.demo.DTO.MovieDetailDTO;
import com.example.demo.DTO.MovieGenreDTO;
import com.example.demo.service.FKGenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/fkGenre")
public class FKGenreController {
    private final FKGenreService fkGenreService;

    //get all genre on a movie
    @GetMapping("/getAllGenre/{movieId}")
    public ResponseEntity<List<MovieGenreDTO>> getAllGenreByMovie(@PathVariable int movieId){
        return new ResponseEntity<>(fkGenreService.getGenreOnMovie(movieId), HttpStatus.OK);
    }

    //get all movie on genre
    @GetMapping("/getAllMovie/{genreId}")
    public ResponseEntity<List<MovieDetailDTO>> getAllMovieByGenre(@PathVariable int genreId){
        return new ResponseEntity<>(fkGenreService.getMovieOnGenre(genreId), HttpStatus.OK);
    }

}
