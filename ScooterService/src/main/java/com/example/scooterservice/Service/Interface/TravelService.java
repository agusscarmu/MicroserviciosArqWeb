package com.example.scooterservice.Service.Interface;

import com.example.scooterservice.DTO.Travel.TravelDTO;

import java.util.List;

public interface TravelService {
    String pauseTravel(long id);

    String resumeTravel(long id);

    List<TravelDTO> getAllTravels();
}
