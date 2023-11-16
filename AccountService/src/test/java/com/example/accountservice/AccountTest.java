package com.example.accountservice;


import com.example.accountservice.Model.Account;
import com.example.accountservice.Service.AccountServiceImpl;
import com.example.accountservice.Service.Interface.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class AccountTest {

    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("Update account test")
    public void testActivateOrDeactivateAccount() {
        Assertions.assertEquals(ResponseEntity.badRequest().body("Account not found"),
                accountService.activateOrDeactivateAccount("id invalido",true));
    }

    @Test
    @DisplayName("Add account Test")
    public void testAddAccount() {
        Assertions.assertThrows(RuntimeException.class, ()->{
            accountService.addAccount(null);
        });
    }


    @Test
    @DisplayName("Balance discount Test")
    public void testDiscount() {
        Account account = accountService.getById("2");
        double balance = account.getMercadoPago().getBalance();
        try {
            accountService.discount(account.getId(), 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(balance-10,
                accountService.getById(account.getId()).getMercadoPago().getBalance());
    }

}
