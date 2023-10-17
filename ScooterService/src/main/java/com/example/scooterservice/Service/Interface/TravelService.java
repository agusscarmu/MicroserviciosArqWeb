package com.example.scooterservice.Service.Interface;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;

import java.util.List;

public interface TravelService {
    String pauseTravel(long id);

    String resumeTravel(long id);

    List<TravelDTO> getAllTravels();

    String startTravel(long idScooter, long idAccount);
    String finishTravel(long id);

    String updatePrice(float price);

    String startTravelWTime(Travel travel);
}
