package com.example.scooterservice.Service.Interface;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import com.example.scooterservice.Observer.TravelObserver;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface TravelService {
    ResponseEntity<String> pauseTravel(long id);

    ResponseEntity<String> resumeTravel(long id);

    List<TravelDTO> getAllTravels();

    ResponseEntity<String> startTravel(long idScooter, String idAccount);
    ResponseEntity<String> finishTravel(long id);
    void registerObserver(TravelObserver observer);

    ResponseEntity<String> startTravelWTime(Travel travel);

    Double getTotalFactured(Integer month1, Integer month2, Integer year);
}
