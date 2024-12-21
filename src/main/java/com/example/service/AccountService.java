package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.AccountAlreadyExistsException;
import com.example.exception.AuthenticationException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account register(Account account) {
        String username = account.getUsername();
        if (username.length() == 0) {
            throw new IllegalArgumentException("Username must be at least 1 character long");
        }
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new AccountAlreadyExistsException("Username has already been taken");
        }
        String password = account.getPassword();
        if (password.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
        return accountRepository.save(account);
    }

    public Account login(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword())
                                .orElseThrow(() -> new AuthenticationException("Invalid login credentials"));
    }
}
