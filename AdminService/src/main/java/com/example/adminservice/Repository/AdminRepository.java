package com.example.adminservice.Repository;

import com.example.adminservice.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
