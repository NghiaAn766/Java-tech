package com.example.lab09.service;

import com.example.lab09.dto.AccountDTO;
import com.example.lab09.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    Account get(Long id);
    void registerAccount(AccountDTO accountDTO);
}
