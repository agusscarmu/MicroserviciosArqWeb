package com.example.scooterservice.Controller;

import com.example.scooterservice.DTO.Travel.TravelDTO;
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
    public ResponseEntity<String> addAccount(@RequestParam long id, @RequestParam float balance){
        return ResponseEntity.ok(accountService.addAccount(id, balance).toString());
    }

    @RequestMapping(value = "/disable", method = RequestMethod.PUT)
    public ResponseEntity<String> disableAccount(@RequestParam("id") long account){
        return ResponseEntity.ok(accountService.disableAccount(account).toString());
    }
}
