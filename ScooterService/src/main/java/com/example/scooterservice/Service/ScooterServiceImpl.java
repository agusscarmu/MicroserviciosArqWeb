package com.example.scooterservice.Service;

import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Model.Scooter;
import com.example.scooterservice.Model.Station;
import com.example.scooterservice.Repository.ScooterRepository;
import com.example.scooterservice.Service.Interface.ScooterService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterServiceImpl implements ScooterService {

    private EntityManager entityManager;
    @Autowired
    private ScooterRepository scooterRepository;
    @Override
    public List<ScooterDTO> findAllScooters() {
        return scooterRepository.findAllScooters();
    }

    @Transactional
    @Override
    public String markScooterMaintenance(Long scooterId, boolean maintenance) {
        if(scooterRepository.existsById(scooterId)){
            scooterRepository.markScooterMaintenance(scooterId, maintenance);
            return "Scooter maintenance status updated";
        }else{
            return "Scooter not found";
        }
    }

    @Override
    public ResponseEntity<String> addScooter(Scooter scooter) {
        return ResponseEntity.ok(scooterRepository.save(scooter).toString());
    }

    @Override
    public String updateStation(Long scooterId, Long stationId) {
        if(scooterRepository.existsById(scooterId)){
            Scooter scooter = scooterRepository.findById(scooterId).get();
            Station station = entityManager.getReference(Station.class, stationId);
            scooter.setLocation(station.getLocation());
            scooter.setStationId(stationId);
            scooterRepository.save(scooter);
            return "Scooter station updated";
        }else{
            return "Scooter not found";
        }
    }

    @Override
    public String deleteScooter(Long scooterId) {
        if(scooterRepository.existsById(scooterId)){
            scooterRepository.deleteById(scooterId);
            return "Scooter deleted";
        }else{
            return "Scooter not found";
        }
    }
}
