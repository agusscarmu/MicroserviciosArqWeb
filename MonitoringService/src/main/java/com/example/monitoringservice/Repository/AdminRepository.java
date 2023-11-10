package com.example.monitoringservice.Repository;

import com.example.monitoringservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<User, Long> {

    @Query("SELECT a FROM User a WHERE a.username = ?1")
    Optional<Object> findByUsername(String username);
}
