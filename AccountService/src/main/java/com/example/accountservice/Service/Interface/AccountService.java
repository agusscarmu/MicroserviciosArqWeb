package com.example.accountservice.Service.Interface;

import com.example.accountservice.Model.Account;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<String> addAccount(Account account);

    void deleteAccountById(Long id);

    String discount(long id, double amount);

    String deleteAccount(long account);

    String activateOrDeactivateAccount(long account, boolean action);

    String disableAccount(long id, boolean action);
}
