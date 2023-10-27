package com.example.accountservice.Controller;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Security.SystemSecurity;
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
    public ResponseEntity<String> discount(@RequestHeader("Authorization") String token, @PathVariable("id") long id, @RequestParam("amount") double amount){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return ResponseEntity.ok(accountService.discount(id, amount).toString());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@RequestParam("id") long account){
        return ResponseEntity.ok(accountService.deleteAccount(account).toString());
    }

    @RequestMapping(value = "/status",method = RequestMethod.PUT)
    public ResponseEntity<String> activateOrDeactivateAccount(@RequestHeader("Authorization") String token ,@RequestParam("id") long account, @RequestParam("active") boolean action){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return ResponseEntity.ok(accountService.activateOrDeactivateAccount(account,action).toString());
    }


}
