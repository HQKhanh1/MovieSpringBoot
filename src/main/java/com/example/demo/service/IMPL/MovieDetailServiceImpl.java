package com.example.demo.service.IMPL;

import com.example.demo.DTO.MovieDetailDTO;
import com.example.demo.DTO.MovieDetailPage;
import com.example.demo.map.MovieDetailMap;
import com.example.demo.model.MovieDetail;
import com.example.demo.repository.MovieDetailRepository;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieDetailServiceImpl implements MovieDetailService {
    private final MovieDetailRepository movieDetailRepository;
    private final FKGenreService fkGenreService;
    private final FKDirectorService fkDirectorService;
    private final FKCastService fkCastService;
    private final MovieEvaluateService movieEvaluateService;
    private final MovieDetailMap movieDetailMap;

    @Override
    public List<MovieDetailDTO> getAllMovie() {
        return movieDetailMap.listMovieDetailToDTO(movieDetailRepository.findAll());
    }

    @Override
    public MovieDetailDTO getMovieById(int movieId) {
        return movieDetailMap.movieDetailToDTO(movieDetailRepository.getById(movieId));
    }

    @Override
    public MovieDetailPage getAllMovieDetailPage(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create Pageable instance
//        Pageable pageable = ;
        Page<MovieDetail> movieDetails = movieDetailRepository.findAll(PageRequest.of(pageNo, pageSize, sort));
        // get content for page object
        List<MovieDetail> listOfPosts = movieDetails.getContent();

        List<MovieDetailDTO> content = movieDetailMap.listMovieDetailToDTO(listOfPosts);
        MovieDetailPage MovieDetailPage = new MovieDetailPage();
        MovieDetailPage.setMovieDetailDTOS(content);
        MovieDetailPage.setPageNo(movieDetails.getNumber());
        MovieDetailPage.setPageSize(movieDetails.getSize());
        MovieDetailPage.setTotalElements(movieDetails.getTotalElements());
        MovieDetailPage.setTotalPages(movieDetails.getTotalPages());
        MovieDetailPage.setFirst(movieDetails.isFirst());
        MovieDetailPage.setLast(movieDetails.isLast());

        return MovieDetailPage;
    }

    @Override
    public MovieDetailDTO addMovieDetail(MovieDetail movieDetail) throws Exception {
        if (checkExitTitle(movieDetail.getTitle())) {
            throw new Exception("Movie not found!");
        } else {
            movieDetailRepository.save(movieDetail);
            return movieDetailMap.movieDetailToDTO(movieDetail);
        }
    }

    @Override
    public MovieDetail editMovieDetail(MovieDetail movieDetailDTO) throws Exception {
        MovieDetail movieDetail = movieDetailRepository.findById(movieDetailDTO.getId()).orElse(null);
        if (movieDetail == null) {
            throw new Exception("Move not found");
        } else if (checkExitTitle(movieDetailDTO.getTitle())) {
            throw new Exception("Movie not found!");
        } else {
            movieDetail.setTitle(movieDetailDTO.getTitle());
            movieDetail.setPoster(movieDetailDTO.getPoster());
            movieDetail.setDetail(movieDetailDTO.getDetail());
            movieDetail.setMovieStatus(movieDetailDTO.getMovieStatus());
            movieDetail.setLinkTrailer(movieDetailDTO.getLinkTrailer());
            movieDetail.setLinkMovie(movieDetailDTO.getLinkMovie());
            movieDetail.setReleaseDate(movieDetailDTO.getReleaseDate());
            movieDetail.setMovieDuration(movieDetailDTO.getMovieDuration());
            movieDetail.setViewNumber(movieDetailDTO.getViewNumber());
            movieDetail.setMovieEvaluates(movieDetailDTO.getMovieEvaluates());
            movieDetail.setFkCasts(movieDetailDTO.getFkCasts());
            movieDetail.setFkGenres(movieDetailDTO.getFkGenres());
            movieDetail.setFkDirectors(movieDetailDTO.getFkDirectors());
            movieDetailRepository.save(movieDetail);
            return movieDetailDTO;
        }

    }

    @Override
    public String deleteMovieDetail(int id) throws Exception {
        MovieDetail movieDetail = movieDetailRepository.findById(id).orElse(null);
        if (movieDetail == null) {
            throw new Exception("Move not found");
        } else {
            fkCastService.deleteFkCastByMovieId(id);
            fkDirectorService.deleteFkDirectorByMovieId(id);
            fkGenreService.deleteByMovieId(id);
            movieEvaluateService.deleteMovieEvaluateByMovieId(id);
            movieDetailRepository.delete(movieDetail);
            return "Delete movie detail by movie id: " + id + " successfully";
        }
    }

    @Override
    public MovieDetail getMovieDetailByTitle(String title) {
        return movieDetailRepository.findMovieDetailByTitle(title);
    }

    public boolean checkExitTitle(String title) {
        int check = 0;
        List<MovieDetail> movieDetails = movieDetailRepository.findAll();
        for (MovieDetail movieDetail : movieDetails) {
            if (movieDetail.getTitle().equals(title)) {
                check++;
            }
        }
        if (check > 0) {
            return true;
        } else if (check == 0) {
            return false;
        } else {
            return false;
        }
    }
}
