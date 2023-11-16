package com.example.scooterservice.Service;

import com.example.scooterservice.DTO.Scooter.ScooterAvailableDTO;
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

import java.io.Serializable;
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
    public ResponseEntity<String> markScooterMaintenance(Long scooterId, boolean maintenance) {
        if(scooterRepository.existsById(scooterId)){
            scooterRepository.markScooterMaintenance(scooterId, maintenance);
            return ResponseEntity.ok("Scooter maintenance updated");
        }else{
            return ResponseEntity.badRequest().body("Scooter not found");
        }
    }

    @Override
    public ResponseEntity<String> addScooter(Scooter scooter) {
        return ResponseEntity.ok(scooterRepository.save(scooter).toString());
    }

    @Override
    public ResponseEntity<String> updateStation(Long scooterId, Long stationId) {
        if(scooterRepository.existsById(scooterId)){
            Scooter scooter = scooterRepository.findById(scooterId).get();
            Station station = entityManager.getReference(Station.class, stationId);
            scooter.setLocation(station.getLocation());
            scooter.setStationId(stationId);
            scooterRepository.save(scooter);
            return ResponseEntity.ok("Scooter station updated");
        }else{
            return ResponseEntity.badRequest().body("Scooter not found");
        }
    }

    @Override
    public ResponseEntity<String> deleteScooter(Long scooterId) {
        if(scooterRepository.existsById(scooterId)){
            scooterRepository.deleteById(scooterId);
            return ResponseEntity.ok("Scooter deleted");
        }else{
            return ResponseEntity.badRequest().body("Scooter not found");
        }
    }

    @Override
    public Serializable getStatus() {
        return scooterRepository.getStatus();
    }

    @Override
    public List<ScooterAvailableDTO> getScooters(String location) {
        return scooterRepository.getAvailableScooters(location);
    }

    @Transactional
    @Override
    public ResponseEntity<String> updateLocation(Long scooterId, String location) {
        if(scooterRepository.existsById(scooterId)){
            scooterRepository.updateLocation(scooterId, location);
            return ResponseEntity.ok("Scooter location updated");
        }else{
            return ResponseEntity.badRequest().body("Scooter not found");
        }
    }

    @Transactional
    @Override
    public boolean travelStarted(long id) {
        if(scooterRepository.existsById(id)){
            if(scooterRepository.available(id)==null){
                return false;
            }
            scooterRepository.travelStarted(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean travelFinished(long id) {
        Long station = scooterRepository.inStation(id);
        if(station==null){
            return false;
        }
        scooterRepository.travelFinished(id,station);
        return true;
    }
}
