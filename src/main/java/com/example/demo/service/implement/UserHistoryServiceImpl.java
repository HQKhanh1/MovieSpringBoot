package com.example.demo.service.implement;

import com.example.demo.model.UserHistory;
import com.example.demo.repository.UserHistoryRepository;
import com.example.demo.service.UserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserHistoryServiceImpl implements UserHistoryService {
    private final UserHistoryRepository userHistoryRepository;
    @Override
    public void deleteUserHistoryFromAccount(int userId) {
        List<UserHistory> userHistories = userHistoryRepository.findAll();
        userHistories.forEach(userHistory -> {
            if (userHistory.getUser().getId() == userId){
                userHistoryRepository.delete(userHistory);
            }
        });
    }
}
