package com.example.accountservice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String firstName;
    @NotEmpty(message = "El apellido no puede estar vacio")
    private String lastName;
    @NotEmpty(message = "El telefono no puede estar vacio")
    private String phone;
    @NotEmpty(message = "El email no puede estar vacio")
    @Email(message = "El email debe ser valido")
    private String email;
    @ManyToMany
    @JoinTable(
            name = "user_account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accounts;
}
