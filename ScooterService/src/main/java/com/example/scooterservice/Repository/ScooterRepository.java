package com.example.scooterservice.Repository;

import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Model.Scooter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    @Query(value = "SELECT new com.example.scooterservice.DTO.Scooter.ScooterDTO(s.id, s.underMaintenance, s.inUse, s.location) FROM Scooter s")
    List<ScooterDTO> findAllScooters();

    @Transactional
    @Modifying
    @Query("UPDATE Scooter s SET s.underMaintenance = :maintenance WHERE s.id = :scooterId")
    void markScooterMaintenance(@Param("scooterId") Long scooterId, @Param("maintenance") boolean maintenance);

}
