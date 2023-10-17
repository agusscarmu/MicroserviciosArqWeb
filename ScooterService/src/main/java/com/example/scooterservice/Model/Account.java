package com.example.scooterservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private long id;

    @OneToMany(mappedBy = "associatedAccount")
    private List<Travel> scooters;

    private float balance;


    public Account(long id, float balance) {
        this.id = id;
        this.balance = balance;
    }
}
