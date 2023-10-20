package com.example.scooterservice.Repository;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long>{

    @Query("SELECT new com.example.scooterservice.DTO.Travel.TravelDTO(t.associatedAccount.id, t.associatedScooter.id, t.createdAt, t.finishedAt, t.paused, t.pauseStartedAt, t.pauseFinishedAt) FROM Travel t")
    List<TravelDTO> findAllTravels();

    @Query("SELECT 1 FROM Travel t JOIN Scooter s ON t.scooterId = s.id JOIN Station st ON st.location = s.location WHERE s.id = ?1 AND st.location = s.location")
    Integer scooterInStation(long scooterId);

    @Query("SELECT SUM(t.totalPrice) FROM Travel t WHERE MONTH(t.createdAt) BETWEEN ?1 AND ?2 AND YEAR(t.createdAt) = ?3")
    String getTotalFacturedBetween(int month1, int month2, int year);
}
