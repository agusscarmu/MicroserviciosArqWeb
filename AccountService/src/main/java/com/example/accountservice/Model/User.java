package com.example.accountservice.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String firstName;

    @NotEmpty(message = "El apellido no puede estar vacío")
    private String lastName;

    @NotEmpty(message = "El teléfono no puede estar vacío")
    private String phone;

    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @DBRef
    private List<Account> accounts;

    public User(String id, String firstName, String lastName, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.accounts = new LinkedList<>();
    }
    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
