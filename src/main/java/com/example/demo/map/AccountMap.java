package com.example.demo.map;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.map.address.TownMap;
import com.example.demo.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountMap {
    private final TownMap townMap;
    public AccountDTO accountToDTO(Account account){
        return new AccountDTO(account.getId(),
                account.getUsername(),
                account.getPassword(),
                account.isEnabled(),
                account.getEmail(),
                account.getAvatar(),
                account.getFirstname(),
                account.getLastname(),
                account.getBirthday(),
                townMap.townToDTO(account.getIdTown()),
                account.getAddress(),
                account.getPhoneNumber(),
                account.isGender());
    }

    public List<AccountDTO> listAccountToListDTO(List<Account> accounts){
        List<AccountDTO> accountDTOS = new ArrayList<>();
        accounts.forEach(account -> {
            accountDTOS.add(this.accountToDTO(account));
        });
        return accountDTOS;
    }
}
