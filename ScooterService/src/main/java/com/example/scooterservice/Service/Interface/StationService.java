package com.example.scooterservice.Service.Interface;

import com.example.scooterservice.Model.Station;
import org.springframework.http.ResponseEntity;

public interface StationService {
    ResponseEntity<String> addStation(Station station);

    String deleteStation(long station);
}
