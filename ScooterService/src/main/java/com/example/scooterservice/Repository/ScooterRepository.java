package com.example.scooterservice.Repository;

import com.example.scooterservice.DTO.Scooter.ScooterAvailableDTO;
import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Model.Scooter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    @Query(value = "SELECT new com.example.scooterservice.DTO.Scooter.ScooterDTO(s.id, s.underMaintenance, s.inUse, s.location) FROM Scooter s")
    List<ScooterDTO> findAllScooters();

    @Transactional
    @Modifying
    @Query("UPDATE Scooter s SET s.underMaintenance = :maintenance WHERE s.id = :scooterId")
    void markScooterMaintenance(@Param("scooterId") Long scooterId, @Param("maintenance") boolean maintenance);


    @Query("SELECT new com.example.scooterservice.DTO.Scooter.ScooterQuantityMaintenanceVsInUseDTO(SUM(CASE WHEN s.underMaintenance = true THEN 1 ELSE 0 END), SUM(CASE WHEN s.inUse = true THEN 1 ELSE 0 END)) FROM Scooter s")
    Serializable getStatus();

    @Modifying
    @Transactional
    @Query("UPDATE Scooter s SET s.inUse = true WHERE s.id = :id")
    void travelStarted(long id);

    @Query("SELECT new com.example.scooterservice.DTO.Scooter.ScooterAvailableDTO(s.id, s.location) FROM Scooter s WHERE s.inUse = false AND s.underMaintenance = false AND s.location = :location")
    List<ScooterAvailableDTO> getAvailableScooters(String location);

    @Query("SELECT 1 FROM Scooter s WHERE s.id = :id AND s.inUse = false AND s.underMaintenance = false")
    Integer available(long id);

    @Modifying
    @Transactional
    @Query("UPDATE Scooter s SET s.inUse = false WHERE s.id = :id")
    void travelFinished(long id);

    @Query("SELECT 1 FROM Scooter s JOIN Station st ON s.location = st.location WHERE s.id = :id AND st.location = s.location")
    Integer inStation(long id);

    @Modifying
    @Transactional
    @Query("UPDATE Scooter s SET s.location = :location WHERE s.id = :id")
    void updateLocation(long id, String location);
}
