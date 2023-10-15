package com.example.scooterservice.Service.Interface;

import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ScooterService {

    List<ScooterDTO> findAllScooters();

    String markScooterMaintenance(Long scooterId, boolean maintenance);
}
