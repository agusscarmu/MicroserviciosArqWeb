package com.example.scooterservice.Service.Interface;

import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<String> addAccount(long id, float balance);
}
