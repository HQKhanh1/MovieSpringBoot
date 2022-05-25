package com.example.demo.map;

import com.example.demo.DTO.MovieEvaluateDTO;
import com.example.demo.model.MovieEvaluate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieEvaluateMap {
    private final AccountMap accountMap;
    private final MovieDetailMap movieDetailMap;
    public MovieEvaluateDTO movieEvaluateToDTO(MovieEvaluate movieEvaluate) {
        return new MovieEvaluateDTO(movieEvaluate.getMovieDetail().getId(),
                movieEvaluate.getAccountDetail().getId(),
                movieEvaluate.getEvaluateTime(),
                movieEvaluate.getEvaluateContent(),
                movieEvaluate.getEvaluateRate());
    }
    public List<MovieEvaluateDTO> listMovieEvaluateToDTO(List<MovieEvaluate> movieEvaluates){
        List<MovieEvaluateDTO> movieEvaluateDTOList = new ArrayList<>();
        movieEvaluates.forEach(movieEvaluate -> {
            movieEvaluateDTOList.add(movieEvaluateToDTO(movieEvaluate));
        });
        return movieEvaluateDTOList;
    }
}
