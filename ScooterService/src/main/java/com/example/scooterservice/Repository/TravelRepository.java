package com.example.scooterservice.Repository;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long>{

    @Query("SELECT new com.example.scooterservice.DTO.Travel.TravelDTO(t.associatedAccount.id, t.associatedScooter.id, t.createdAt, t.finishedAt, t.paused, t.pauseStartedAt, t.pauseFinishedAt) FROM Travel t")
    List<TravelDTO> findAllTravels();
}
