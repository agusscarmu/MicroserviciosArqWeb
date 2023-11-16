package com.example.accountservice.Service.Interface;

import com.example.accountservice.Model.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    ResponseEntity<String> addAccount(Account account);

    void deleteAccountById(String id);

    ResponseEntity<String> discount(String id, double amount) throws Exception;

    ResponseEntity<String> deleteAccount(String account);

    ResponseEntity<String> activateOrDeactivateAccount(String account, boolean action);

    List<Account> getAll();

    Account getById(String id);
}
