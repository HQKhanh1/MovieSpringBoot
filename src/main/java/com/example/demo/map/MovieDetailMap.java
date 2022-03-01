package com.example.demo.map;

import com.example.demo.DTO.MovieDetailDTO;
import com.example.demo.model.MovieDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieDetailMap {
    public MovieDetailDTO movieDetailToDTO(MovieDetail movieDetail) {
        MovieDetailDTO movieDetailDTO = new MovieDetailDTO();
        movieDetailDTO.setId(movieDetail.getId());
        movieDetailDTO.setTitle(movieDetail.getTitle());
        movieDetailDTO.setPoster(movieDetail.getPoster());
        movieDetailDTO.setDetail(movieDetail.getDetail());
        movieDetailDTO.setMovieStatus(movieDetail.getMovieStatus());
        movieDetailDTO.setLinkTrailer(movieDetail.getLinkTrailer());
        movieDetailDTO.setLinkMovie(movieDetail.getLinkMovie());
        movieDetailDTO.setReleaseDate(movieDetail.getReleaseDate());
        movieDetailDTO.setMovieDuration(movieDetail.getMovieDuration());
        movieDetailDTO.setViewNumber(movieDetail.getViewNumber());
        return movieDetailDTO;
    }

    public List<MovieDetailDTO> listMovieDetailToDTO(List<MovieDetail> movieDetails) {
        List<MovieDetailDTO> movieDetailDTOS = new ArrayList<>();
        movieDetails.forEach(movieDetail -> {
            movieDetailDTOS.add(movieDetailToDTO(movieDetail));
        });
        return movieDetailDTOS;
    }

}
