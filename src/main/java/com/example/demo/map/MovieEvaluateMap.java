package com.example.demo.map;

import com.example.demo.DTO.MovieEvaluateDTO;
import com.example.demo.model.Key.MovieEvaluateKey;
import com.example.demo.model.MovieEvaluate;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.MovieDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieEvaluateMap {
    private final MovieDetailMap movieDetailMap;
    private final AccountMap accountMap;
    private final MovieDetailRepository movieDetailRepository;
    private final AccountRepository accountRepository;

    public MovieEvaluateDTO movieEvaluateToDTO(MovieEvaluate movieEvaluate) {
        return new MovieEvaluateDTO(movieEvaluate.getMovieDetail().getId(),
                movieEvaluate.getAccountDetail().getId(),
                movieEvaluate.getEvaluateTime(),
                movieEvaluate.getEvaluateContent(),
                movieEvaluate.getEvaluateRate());
    }

    public MovieEvaluate dTOToMovieEvaluate(MovieEvaluateDTO movieEvaluateDTO) {
        return new MovieEvaluate(
                new MovieEvaluateKey(movieEvaluateDTO.getAccId(),
                        movieEvaluateDTO.getMovieId()),
                accountRepository.getById(movieEvaluateDTO.getAccId()),
                movieDetailRepository.getById(movieEvaluateDTO.getMovieId()),
                movieEvaluateDTO.getEvaluateTime(),
                movieEvaluateDTO.getEvaluateContent(),
                movieEvaluateDTO.getEvaluateRate());
    }

    public List<MovieEvaluateDTO> listMovieEvaluateToDTO(List<MovieEvaluate> movieEvaluates) {
        List<MovieEvaluateDTO> movieEvaluateDTOList = new ArrayList<>();
        movieEvaluates.forEach(movieEvaluate -> {
            movieEvaluateDTOList.add(movieEvaluateToDTO(movieEvaluate));
        });
        return movieEvaluateDTOList;
    }
}
