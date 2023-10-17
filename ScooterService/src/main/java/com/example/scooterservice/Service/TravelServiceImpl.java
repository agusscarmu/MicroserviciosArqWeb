package com.example.scooterservice.Service;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import com.example.scooterservice.Repository.TravelRepository;
import com.example.scooterservice.Service.Interface.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRepository travelRepository;

    private final WebClient webClientMaintenance = WebClient.builder().baseUrl("http://localhost:8083").build();
    private final WebClient webClientAccount = WebClient.builder().baseUrl("http://localhost:8081").build();

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

    @Override
    public String startTravel(long idScooter, long idAccount) {
        Travel travel = new Travel(idAccount, idScooter);
        travelRepository.save(travel);
        return "Travel started";
    }

    @Override
    public String finishTravel(long id) {
        Travel travel = travelRepository.findById(id).get();
        if(travelRepository.scooterInStation(travel.getScooterId())!=1){
            return "Scooter not in station";
        }else{
            travel.finishTravel();
            travelRepository.save(travel);
            webClientMaintenance.put()
                    .uri("/scooterReport/updateReport/{id}?usageTime={usageTime}&pauseTime={pauseTime}",
                            travel.getScooterId(), travel.getUsageTime(), travel.getPauseDuration())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            webClientAccount.put()
                    .uri("/account/{id}/discount?amount={amount}",
                            travel.getAccountId(), travel.getTotalPrice())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return "Travel finished";
        }
    }

    @Override
    public String updatePrice(float price) {
        Travel.setPrice(price);
        return "Price updated";
    }

    @Override
    public String startTravelWTime(Travel travel) {
        travelRepository.save(travel);
        return "Travel started";
    }
}
