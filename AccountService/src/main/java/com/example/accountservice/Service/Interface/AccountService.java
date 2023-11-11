package com.example.accountservice.Service.Interface;

import com.example.accountservice.Model.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    ResponseEntity<String> addAccount(Account account);

    void deleteAccountById(String id);

    String discount(String id, double amount) throws Exception;

    String deleteAccount(String account);

    String activateOrDeactivateAccount(String account, boolean action);

    String disableAccount(String id, boolean action);

    List<Account> getAll();
}
