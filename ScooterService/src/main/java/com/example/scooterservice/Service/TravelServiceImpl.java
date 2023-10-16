package com.example.scooterservice.Service;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import com.example.scooterservice.Repository.TravelRepository;
import com.example.scooterservice.Service.Interface.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Override
    public String pauseTravel(long id) {
        Travel travel = travelRepository.findById(id).get();
        travel.startPause();
        travelRepository.save(travel);
        return "Travel paused";
    }

    @Override
    public String resumeTravel(long id) {
        Travel travel = travelRepository.findById(id).get();
        travel.endPause();
        travelRepository.save(travel);
        return "Travel resumed";
    }

    @Override
    public List<TravelDTO> getAllTravels() {
        return travelRepository.findAllTravels();
    }
}
