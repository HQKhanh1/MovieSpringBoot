package com.example.demo.controller;

import com.example.demo.DTO.DirectorPage;
import com.example.demo.DTO.MovieDirectorDTO;
import com.example.demo.service.MovieDirectorService;
import com.example.demo.util.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("//api/admin/director")
public class DirectorController {
    private final MovieDirectorService movieDirectorService;

    //get all director
    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDirectorDTO>> getAllDirectors() {
        return new ResponseEntity<>(movieDirectorService.getAllMovieDirector(), HttpStatus.OK);
    }
    //get page directors

    @GetMapping("/page")
    public DirectorPage getAllUsers(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return movieDirectorService.getAllDirectorPage(pageNo, pageSize, sortBy, sortDir);
    }
}
