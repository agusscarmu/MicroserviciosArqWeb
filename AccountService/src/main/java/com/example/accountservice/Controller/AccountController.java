package com.example.accountservice.Controller;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public Account addAccount(@RequestBody Account acc){
        return accountService.addAccount(acc);
    }

}
