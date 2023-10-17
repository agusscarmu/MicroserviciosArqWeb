package com.example.accountservice.Service;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Model.MercadoPago;
import com.example.accountservice.Repository.AccountRepository;
import com.example.accountservice.Service.Interface.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final WebClient webClientScooter = WebClient.create("http://localhost:8082");

    @Override
    public ResponseEntity<String> addAccount(Account account) {
        accountRepository.save(account);
        updateAccountScooter(account);
        return ResponseEntity.ok(account.toString());
    }

    private void updateAccountScooter(Account account){
        MercadoPago mp = entityManager.find(MercadoPago.class, account.getMercadoPagoId());
        webClientScooter.post()
                .uri("/account/add?id={id}&balance={balance}", account.getId(), mp.getBalance())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public String discount(long id, double amount) {
        Account account = accountRepository.findById(id).get();
        MercadoPago mp = entityManager.find(MercadoPago.class, account.getMercadoPagoId());
        if(mp.getBalance()>=amount){
            mp.setBalance(mp.getBalance()-amount);
            accountRepository.save(account);
            updateAccountScooter(account);
            return "Discounted";
        }else{
            return "Not enough balance";
        }
    }
}
