package com.example.demo.dto;

import com.example.demo.model.Key.MovieEvaluateKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvaluateDTO {
    MovieEvaluateKey id;
    //    @ManyToOne(fetch = FetchType.LAZY)
    private MovieAccountDTO movieAccountDetail;
    //    @ManyToOne(fetch = FetchType.LAZY)
    private MovieDetailDTO movieDetail;
    private Date evaluateTime;
    private String evaluateContent;
    private int evaluateRate;
}
