package com.example.demo.service;

import com.example.demo.DTO.UserHistoryDTO;

public interface UserHistoryService {
    String createHistory(UserHistoryDTO userHistoryDTO);
    void deleteUserHistoryFromAccount(int userId);
}
