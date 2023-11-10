package com.monopatin.authservice.repository.mysql;

import com.monopatin.authservice.entity.mysql.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
