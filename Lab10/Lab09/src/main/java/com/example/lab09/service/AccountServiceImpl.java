package com.example.lab09.service;

import com.example.lab09.dto.AccountDTO;
import com.example.lab09.dto.AccountDetails;
import com.example.lab09.entity.Account;
import com.example.lab09.exception.NotFoundException;
import com.example.lab09.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@Component
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Account get(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Override
    public void registerAccount(AccountDTO accountDTO) {
        // Check if the username already exists
        if (accountRepository.findByEmail(accountDTO.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // Create a new account
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());

        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(accountDTO.getPassword());
        account.setPassword(encryptedPassword);

        // Save the account to the repository
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem account có tồn tại trong database không?
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found with email: " + username);
        }
        return new AccountDetails(account);
    }
}
