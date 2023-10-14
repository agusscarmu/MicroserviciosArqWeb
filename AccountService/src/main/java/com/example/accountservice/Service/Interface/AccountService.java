package com.example.accountservice.Service.Interface;

import com.example.accountservice.Model.Account;

public interface AccountService {

    Account addAccount(Account account);

    void deleteAccountById(Long id);

}
