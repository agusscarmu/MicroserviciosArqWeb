package com.example.scooterservice.Repository;

import com.example.scooterservice.Model.Station;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StationRepository extends JpaRepository<Station, Long> {

}
