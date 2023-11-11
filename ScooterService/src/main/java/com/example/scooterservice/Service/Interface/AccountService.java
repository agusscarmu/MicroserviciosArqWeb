package com.example.scooterservice.Service.Interface;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<String> addAccount(String id, float balance);
    Object deleteAccount(String account);

    Object accountStatus(String account, boolean active);
}
