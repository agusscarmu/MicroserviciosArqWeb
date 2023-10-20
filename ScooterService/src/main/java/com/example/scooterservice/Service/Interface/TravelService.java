package com.example.scooterservice.Service.Interface;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import com.example.scooterservice.Observer.TravelObserver;

import java.util.Date;
import java.util.List;

public interface TravelService {
    String pauseTravel(long id);

    String resumeTravel(long id);

    List<TravelDTO> getAllTravels();

    String startTravel(long idScooter, long idAccount);
    String finishTravel(long id);
    void registerObserver(TravelObserver observer);
    String updatePrice(float price);

    String updatePrice(float price, Date date);

    String startTravelWTime(Travel travel);

    String getTotalFactured();

    String getTotalFactured(int month1, int month2, int year);
}
