package com.example.accountservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPago {
    @Id
    @Column(name = "mercadoPago_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double balance;

    @OneToMany(mappedBy = "mercadoPago")
    private List<Account> accounts;
}
