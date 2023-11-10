package com.example.adminservice.Repository;

import com.example.adminservice.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.username = ?1")
    Optional<Object> findByUsername(String username);
}
