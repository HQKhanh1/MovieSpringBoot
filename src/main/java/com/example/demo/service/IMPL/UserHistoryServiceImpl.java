package com.example.demo.service.IMPL;

import com.example.demo.DTO.UserHistoryDTO;
import com.example.demo.map.UserHistoryMap;
import com.example.demo.model.UserHistory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.MovieDetailRepository;
import com.example.demo.repository.UserHistoryRepository;
import com.example.demo.service.UserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserHistoryServiceImpl implements UserHistoryService {
    private final UserHistoryRepository userHistoryRepository;
    private final UserHistoryMap userHistoryMap;
    private final AccountRepository accountRepository;
    private final MovieDetailRepository movieDetailRepository;

    @Override
    public String createHistory(UserHistoryDTO userHistoryDTO) {
        userHistoryRepository.save(userHistoryMap.DTOToUserHistory(userHistoryDTO));
        return "add user's history successfully";
    }

    @Override
    public void deleteUserHistoryFromAccount(int userId) {
        List<UserHistory> userHistories = userHistoryRepository.findAll();
        userHistories.forEach(userHistory -> {
            if (userHistory.getUser().getId() == userId) {
                userHistoryRepository.delete(userHistory);
            }
        });
    }

    @Override
    public UserHistoryDTO addHistory(Integer idAcc, Integer idMovie) {
        List<UserHistory> userHistories = userHistoryRepository.findAll();
        UserHistory userHistory = null;
        Date date = new Date();
        for (UserHistory userHistoryCheck : userHistories) {
            if (Objects.equals(userHistoryCheck.getUser().getId(), idAcc) && Objects.equals(userHistoryCheck.getMovie().getId(), idMovie)) {
                userHistory = userHistoryCheck;
                userHistory.setUser(accountRepository.getById(idAcc));
                userHistory.setMovie(movieDetailRepository.getById(idMovie));
                userHistory.setHistoryDate(date);
                userHistoryRepository.save(userHistory);
            }
        }
        if (userHistory != null) {
            userHistory.setUser(accountRepository.getById(idAcc));
            userHistory.setMovie(movieDetailRepository.getById(idMovie));
            userHistory.setHistoryDate(date);
            userHistoryRepository.save(userHistory);
        }
        assert userHistory != null;
        return userHistoryMap.userHistoryToDTO(userHistory);
    }

    @Override
    public List<UserHistoryDTO> getAllByAccount(int id) {
        List<UserHistoryDTO> userHistoryDTOS = new ArrayList<>();
        for (UserHistory userHistory : userHistoryRepository.findAll()) {
            if (userHistory.getUser().getId() == id) {
                userHistoryDTOS.add(userHistoryMap.userHistoryToDTO(userHistory));
            }
        }
        return userHistoryDTOS;
    }
}
