package com.example.accountservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mercadoPago_id")
    private long mercadoPagoId;

    @ManyToOne
    @JoinColumn(name = "mercadoPago_id", insertable = false, updatable = false)
    private MercadoPago mercadoPago;

    @ManyToMany
    @JoinTable(
            name = "user_account",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
