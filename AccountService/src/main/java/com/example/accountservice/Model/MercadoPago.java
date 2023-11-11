package com.example.accountservice.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Document(collection = "mercadoPago")
@Data
public class MercadoPago {

    @Id
    private String id;

    private double balance;

    @DBRef
    private List<Account> accounts;

    public MercadoPago(String id,double balance) {
        this.id=id;
        this.balance = balance;
        this.accounts = new LinkedList<>();
    }
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public String toString() {
        return "MercadoPago{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}