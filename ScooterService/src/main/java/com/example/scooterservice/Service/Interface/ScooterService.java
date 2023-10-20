package com.example.scooterservice.Service.Interface;

import com.example.scooterservice.DTO.Scooter.ScooterAvailableDTO;
import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Model.Scooter;
import com.example.scooterservice.Observer.TravelObserver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

public interface ScooterService extends TravelObserver {

    List<ScooterDTO> findAllScooters();

    String markScooterMaintenance(Long scooterId, boolean maintenance);

    ResponseEntity<String> addScooter(Scooter scooter);

    String updateStation(Long scooterId, Long stationId);

    String deleteScooter(Long scooterId);

    Serializable getStatus();

    List<ScooterAvailableDTO> getScooters(String location);

    String updateLocation(Long scooterId, String location);
}
