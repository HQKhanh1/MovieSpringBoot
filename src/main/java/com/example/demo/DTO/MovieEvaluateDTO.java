package com.example.demo.DTO;

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
    private Date evaluateTime;
    private String evaluateContent;
    private int evaluateRate;
}
