package com.example.scooterservice.Repository;

import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    @Query(value = "SELECT new com.example.scooterservice.DTO.Scooter.ScooterDTO(s.id, s.underMaintenance, s.inUse, s.location) FROM Scooter s")
    List<ScooterDTO> findAllScooters();

    @Query(value = "UPDATE Scooter s SET s.underMaintenance = ?2 WHERE s.id = ?1")
    void markScooterMaintenance(Long scooterId, boolean maintenance);
}
