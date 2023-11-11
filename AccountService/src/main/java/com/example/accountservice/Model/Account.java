package com.example.accountservice.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Document(collection = "accounts")
@Data
public class Account {

    @Id
    private String id;

    @DBRef
    private MercadoPago mercadoPago;

    @DBRef
    private List<User> users;

    @CreatedDate
    @Field("created_at")
    private Date createdAt;

    private boolean active;

    public Account(String id, MercadoPago mercadoPago, List<User> users, Date createdAt, boolean active) {
        this.id = id;
        this.mercadoPago = mercadoPago;
        this.users = users;
        this.createdAt = createdAt;
        this.active = active;
    }

    public void activateAccount() {
        this.active = true;
    }

    public void deactivateAccount() {
        this.active = false;
    }

    public void addUser(User u) {
        this.users.add(u);
    }

    public void addUserIndex(int i, User u) {
        this.users.add(i,u);
    }


}
