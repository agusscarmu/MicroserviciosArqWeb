package com.example.scooterservice.Service;

import com.example.scooterservice.Model.Station;
import com.example.scooterservice.Repository.StationRepository;
import com.example.scooterservice.Service.Interface.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public ResponseEntity<String> addStation(Station station) {
        return ResponseEntity.ok(stationRepository.save(station).toString());
    }

    @Override
    public ResponseEntity<String> deleteStation(long station) {
        if (stationRepository.existsById(station)){
            stationRepository.deleteById(station);
            return ResponseEntity.ok("Station deleted");
        }
        return ResponseEntity.badRequest().body("Station not found");
    }

}
