package com.example.scooterservice.Service;

import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Repository.ScooterRepository;
import com.example.scooterservice.Service.Interface.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterServiceImpl implements ScooterService {

    @Autowired
    private ScooterRepository scooterRepository;
    @Override
    public List<ScooterDTO> findAllScooters() {
        return scooterRepository.findAllScooters();
    }

    @Override
    public String markScooterMaintenance(Long scooterId, boolean maintenance) {
        if(scooterRepository.existsById(scooterId)){
            scooterRepository.markScooterMaintenance(scooterId, maintenance);
            return "Scooter maintenance status updated";
        }else{
            return "Scooter not found";
        }
    }
}
