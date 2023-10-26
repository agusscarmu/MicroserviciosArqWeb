package com.example.scooterservice.Controller;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Security.SystemSecurity;
import com.example.scooterservice.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String saludo(){
        return "Hola";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addAccount(@RequestHeader("Authorization") String token, @RequestParam long id, @RequestParam float balance){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return ResponseEntity.ok(accountService.addAccount(id, balance).toString());
    }

    @RequestMapping(value = "/disable", method = RequestMethod.PUT)
    public ResponseEntity<String> disableAccount(@RequestHeader("Authorization") String token, @RequestParam("id") long account){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return ResponseEntity.ok(accountService.disableAccount(account).toString());
    }
}
