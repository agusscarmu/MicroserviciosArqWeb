package com.example.scooterservice.Repository;

import com.example.scooterservice.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
