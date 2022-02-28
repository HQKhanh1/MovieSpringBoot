package com.example.demo.service.implement;

import com.example.demo.model.MovieEvaluate;
import com.example.demo.repository.MovieEvaluateRepository;
import com.example.demo.service.MovieEvaluateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MovieEvaluateServiceImpl implements MovieEvaluateService {
    private final MovieEvaluateRepository movieEvaluateRepository;
    @Override
    public void deleteMovieEvaluateByUserId(int id) {
        List<MovieEvaluate> movieEvaluates = movieEvaluateRepository.findAll();
        movieEvaluates.forEach(movieEvaluate -> {
            if (movieEvaluate.getId().getUserId() == id){
                movieEvaluateRepository.delete(movieEvaluate);
            }
        });
    }
}
