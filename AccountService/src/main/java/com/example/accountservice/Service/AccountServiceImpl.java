package com.example.accountservice.Service;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Model.MercadoPago;
import com.example.accountservice.Model.User;
import com.example.accountservice.Repository.AccountRepository;
import com.example.accountservice.Security.SystemSecurity;
import com.example.accountservice.Service.Interface.AccountService;
import com.example.accountservice.Service.Interface.MercadoPagoService;
import com.example.accountservice.Service.Interface.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MercadoPagoService mpService;

    @Autowired
    private UserService uService;

    private final WebClient webClientScooter = WebClient.create("http://localhost:8082");

    @Override
    public ResponseEntity<String> addAccount(Account account) {
        MercadoPago mp = mpService.findMercadoPagoById(account.getMercadoPago().getId());
        mp.addAccount(account);
        account.setMercadoPago(mp);
        if(mp == null){
            return ResponseEntity.badRequest().body("MercadoPago not found");
        }
        for(int i=0;i<account.getUsers().size();i++){
            User u = uService.findUserById(account.getUsers().get(i).getId());
            if(u == null){
                return ResponseEntity.badRequest().body("User not found");
            }

            account.getUsers().remove(i);
            account.addUserIndex(i, u);

        }
        account.activateAccount();
        accountRepository.save(account);
        updateAccountScooter(account);
        return ResponseEntity.ok("Account added");
    }

    private void updateAccountScooter(Account account) {
        String token = Jwts.builder()
                .setSubject("AccountService")
                .signWith(SignatureAlgorithm.HS256, SystemSecurity.getKey())
                .compact();
        webClientScooter.post()
                .uri("/account/add?id={id}&balance={balance}", account.getId(), account.getMercadoPago().getBalance())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void deleteAccountById(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<String> discount(String id, double amount) throws Exception {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            MercadoPago mp = account.getMercadoPago();
            if (mp.getBalance() >= amount) {
                mp.setBalance(mp.getBalance() - amount);
                mpService.addMp(mp);
                accountRepository.save(account);
                updateAccountScooter(account);
                return ResponseEntity.ok("Discount applied");
            } else {
                return ResponseEntity.badRequest().body("Not enough balance");
            }
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

    @Override
    public ResponseEntity<String> deleteAccount(String accountId) {
        if (!accountRepository.existsById(accountId)) {
            return ResponseEntity.badRequest().body("Account not found");
        }
        try{
            String token = Jwts.builder()
                    .setSubject("AccountService")
                    .signWith(SignatureAlgorithm.HS256, SystemSecurity.getKey())
                    .compact();
            accountRepository.deleteById(accountId);
            webClientScooter.delete()
                    .uri("/account/delete?id={id}", accountId)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return ResponseEntity.ok("Account deleted");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @Override
    public ResponseEntity<String> activateOrDeactivateAccount(String id, boolean active) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            if (active) {
                account.activateAccount();
            } else {
                account.deactivateAccount();
            }
            accountRepository.save(account);

            String token = Jwts.builder()
                    .setSubject("AccountService")
                    .signWith(SignatureAlgorithm.HS256, SystemSecurity.getKey())
                    .compact();
            webClientScooter.put()
                    .uri("/account/status?id={id}&active={active}", id, active)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return ResponseEntity.ok("Account updated");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(String id) {
        return accountRepository.findById(id).orElse(null);
    }
}
