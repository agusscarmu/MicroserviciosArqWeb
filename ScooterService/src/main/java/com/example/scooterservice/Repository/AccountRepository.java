package com.example.scooterservice.Repository;

import com.example.scooterservice.Model.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.active = ?2 WHERE a.id = ?1")
    void desactivateAccount(long id, boolean active);
}
