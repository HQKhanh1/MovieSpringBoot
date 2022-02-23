package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryDTO {
    private int id;
//    @ManyToOne(fetch = FetchType.LAZY)
    private AccountDTO user;
//    @ManyToOne(fetch = FetchType.LAZY)
    private MovieDetailDTO movie;
    private Date historyDate;

}