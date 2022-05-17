package com.example.demo.service.IMPL;

import com.example.demo.DTO.*;
import com.example.demo.map.MovieCastMap;
import com.example.demo.map.MovieDetailMap;
import com.example.demo.map.MovieEvaluateMap;
import com.example.demo.map.MovieGenreMap;
import com.example.demo.model.*;
import com.example.demo.repository.MovieDetailRepository;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieDetailServiceImpl implements MovieDetailService {
    private final MovieDetailRepository movieDetailRepository;
    private final FKGenreService fkGenreService;
    private final FKDirectorService fkDirectorService;
    private final FKCastService fkCastService;
    private final MovieEvaluateService movieEvaluateService;
    private final MovieDetailMap movieDetailMap;
    private final MovieGenreMap movieGenreMap;
    private final MovieCastMap movieCastMap;
    private final MovieEvaluateMap movieEvaluateMap;

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
    public MovieDetail deleteMovieDetail(int id) throws Exception {
        MovieDetail movieDetail = movieDetailRepository.findById(id).orElse(null);
        if (movieDetail == null) {
            throw new Exception("Move not found");
        } else {
            fkCastService.deleteFkCastByMovieId(id);
            fkDirectorService.deleteFkDirectorByMovieId(id);
            fkGenreService.deleteByMovieId(id);
            movieEvaluateService.deleteMovieEvaluateByMovieId(id);
            movieDetailRepository.delete(movieDetail);
            return movieDetail;
        }
    }

    @Override
    public MovieDetail getMovieDetailByTitle(String title) {
        return movieDetailRepository.findMovieDetailByTitle(title);
    }

    @Override
    public List<MovieRate> getListMovieRate() {
        List<MovieRate> movieRates = new ArrayList<>();
        List<MovieDetail> movieDetails = movieDetailRepository.findAll();
        for (MovieDetail movieDetail : movieDetails) {
            if (movieDetail.getMovieEvaluates().isEmpty()) {
                movieRates.add(new MovieRate(movieDetailMap.movieDetailToDTO(movieDetail), 0.0));
            } else {
                movieRates.add(getRateMovie(movieDetail.getId()));
            }
        }
        return movieRates.stream().sorted(Comparator.comparing(MovieRate::getRate).reversed()).collect(Collectors.toList());
    }

    @Override
    public MovieRate getRateMovie(int id) {
        List<MovieEvaluate> movieEvaluates = movieEvaluateService.getMovieEvaluates();
        int sumRate = 0;
        int countMovie = 0;
        MovieDetailDTO movieDetailDTO = new MovieDetailDTO();
        assert movieEvaluates != null;
        for (MovieEvaluate movieEvaluate : movieEvaluates) {
            if (movieEvaluate.getId().getMovieId() == id) {
                movieDetailDTO = movieDetailMap.movieDetailToDTO(movieEvaluate.getMovieDetail());
                sumRate += movieEvaluate.getEvaluateRate();
                countMovie = countMovie + 1;
            }
        }
        if (countMovie != 0) {
            return new MovieRate(movieDetailDTO, ((double) sumRate / (double) countMovie));
        } else {
            return new MovieRate(movieDetailDTO, 0.0);
        }
    }

    @Override
    public List<MovieGenreDTO> getMovieGenres(int id) {
        MovieDetail movieDetail = movieDetailRepository.getById(id);
        List<FKGenre> fkGenres = new ArrayList<>();
        List<MovieGenre> genreList = new ArrayList<>();
        if (movieDetail.getFkGenres() != null || movieDetail.getFkGenres().size() != 0) {
            fkGenres = movieDetail.getFkGenres();
        }
        for (FKGenre fkGenre : fkGenres) {
            genreList.add(fkGenre.getMovieGenre());
        }
        return movieGenreMap.listMovieGenreToDTO(genreList);
    }

    @Override
    public List<MovieCastDTO> getMovieCasts(int id) {
        MovieDetail movieDetailCast = movieDetailRepository.getById(id);
        List<FKCast> fkCasts = new ArrayList<>();
        List<MovieCast> castList = new ArrayList<>();
        if (movieDetailCast.getFkCasts() != null || movieDetailCast.getFkCasts().size() != 0) {
            fkCasts = movieDetailCast.getFkCasts();
        }
        for (FKCast fkCast : fkCasts) {
            castList.add(fkCast.getMovieCast());
        }
        return movieCastMap.listMovieCastToDTO(castList);
    }

    @Override
    public List<MovieDetailDTO> search(String searchQuery) {
        return movieDetailMap.listMovieDetailToDTO(movieDetailRepository.findByTitleLike(searchQuery));
    }

    @Override
    public List<MovieEvaluateDTO> loadEvaluate(int movieId, int accId) {
        List<MovieEvaluate> movieEvaluates = movieEvaluateService.getMovieEvaluates();
        List<MovieEvaluateDTO> movieEvaluateDTOS = new ArrayList<>();
        for (MovieEvaluate movieEvaluate: movieEvaluates) {
            if (movieEvaluate.getId().getMovieId() == movieId) {
                movieEvaluateDTOS.add(movieEvaluateMap.movieEvaluateToDTO(movieEvaluate));
            }
        }
        return movieEvaluateDTOS;
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
