package com.example.lab09.controller;

import com.example.lab09.config.jwt.JwtTokenUtil;
import com.example.lab09.dto.AccountDTO;
import com.example.lab09.dto.JwtRequest;
import com.example.lab09.dto.JwtResponse;
import com.example.lab09.service.AccountService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@Transactional
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // The function takes in a AccountDTO object, validates the password length, and then registers the account
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody AccountDTO accountDTO) {
        if (isPasswordLengthInvalid(accountDTO.getPassword())) {
            return;
        }
        accountService.registerAccount(accountDTO);
    }

    // Hàm lấy email and password, authenticates(Xác thực) account, and returns a token
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );

            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = accountService
                    .loadUserByUsername(authenticationRequest.getEmail());

            // Trả về jwt cho người dùng
            String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return password.length() < 6;
    }

}
