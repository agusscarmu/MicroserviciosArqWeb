package com.example.scooterservice.Service;

import com.example.scooterservice.Model.Account;
import com.example.scooterservice.Repository.AccountRepository;
import com.example.scooterservice.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<String> addAccount(long id, float balance) {
        Account account = new Account(id, balance);
        return ResponseEntity.ok(accountRepository.save(account).toString());
    }
}
