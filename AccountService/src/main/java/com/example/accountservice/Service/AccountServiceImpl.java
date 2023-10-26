package com.example.accountservice.Service;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Model.MercadoPago;
import com.example.accountservice.Repository.AccountRepository;
import com.example.accountservice.Security.SystemSecurity;
import com.example.accountservice.Service.Interface.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
        account.activateAccount();
        accountRepository.save(account);
        updateAccountScooter(account);
        return ResponseEntity.ok(account.toString());
    }

    private void updateAccountScooter(Account account){
        MercadoPago mp = entityManager.find(MercadoPago.class, account.getMercadoPagoId());
        String token = Jwts.builder()
                .setSubject("AccountService")
                .signWith(SignatureAlgorithm.HS256, SystemSecurity.getKey())
                .compact();
        webClientScooter.post()
                .uri("/account/add?id={id}&balance={balance}", account.getId(), mp.getBalance())
                .header("Authorization", "Bearer " + token)
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

    @Override
    public String deleteAccount(long account) {
        if(!accountRepository.existsById(account)){
            return "Account not found";
        }
        String token = Jwts.builder()
                .setSubject("AccountService")
                .signWith(SignatureAlgorithm.HS256, SystemSecurity.getKey())
                .compact();
        accountRepository.deleteById(account);
        webClientScooter.put()
                .uri("/account/disable?id={id}", account)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "Account deleted";
    }

    @Override
    public String activateOrDeactivateAccount(long id, boolean action) {
        if(!accountRepository.existsById(id)){
            return "Account not found";
        }
        Account account = accountRepository.findById(id).get();
        if(action){
            account.activateAccount();
        }else{
            account.deactivateAccount();
        }
        accountRepository.save(account);
        updateAccountScooter(account);
        return "Account updated";
    }

    @Transactional
    @Override
    public String disableAccount(long id, boolean action) {
        if(!accountRepository.existsById(id)){
            return "Account not found";
        }
        accountRepository.disableAccount(id, action);
        return "Account disabled";
    }
}
