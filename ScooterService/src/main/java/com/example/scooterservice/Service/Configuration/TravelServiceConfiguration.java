package com.example.scooterservice.Service.Configuration;

import com.example.scooterservice.Service.Interface.ScooterService;
import com.example.scooterservice.Service.Interface.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelServiceConfiguration {

    @Autowired
    public TravelServiceConfiguration(TravelService travelService, ScooterService scooterService) {
        travelService.registerObserver(scooterService);
    }
}
