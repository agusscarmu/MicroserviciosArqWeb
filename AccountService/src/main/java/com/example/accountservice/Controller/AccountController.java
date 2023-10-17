package com.example.accountservice.Controller;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity<String> addAccount(@RequestBody Account account){
        return ResponseEntity.ok(accountService.addAccount(account).toString());
    }

    @RequestMapping(value = "/{id}/discount", method = RequestMethod.PUT)
    public ResponseEntity<String> discount(@PathVariable("id") long id, @RequestParam("amount") double amount){
        return ResponseEntity.ok(accountService.discount(id, amount).toString());
    }


}
