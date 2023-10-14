package com.example.accountservice.Service;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Repository.AccountRepository;
import com.example.accountservice.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
}
