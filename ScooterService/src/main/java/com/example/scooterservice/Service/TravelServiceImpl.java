package com.example.scooterservice.Service;

import com.example.scooterservice.DTO.Travel.DataTravelDTO;
import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import com.example.scooterservice.Observer.TravelObserver;
import com.example.scooterservice.Repository.TravelRepository;
import com.example.scooterservice.Security.SystemSecurity;
import com.example.scooterservice.Service.Interface.TravelService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRepository travelRepository;

    private List<TravelObserver> observers = new LinkedList<>();
    private final WebClient webClientMaintenance = WebClient.builder().baseUrl("http://localhost:8083").build();
    private final WebClient webClientAccount = WebClient.builder().baseUrl("http://localhost:8081").build();

    private final WebClient webClientDataServie = WebClient.builder().baseUrl("http://localhost:8086").build();


    @Override
    public void registerObserver(TravelObserver observer) {
        observers.add(observer);
    }

    private DataTravelDTO getLastUpdate() {
        return webClientDataServie.get()
                .uri("/dataTravel/lastUpdate")
                .exchange()
                .flatMap(response -> response.toEntity(DataTravelDTO.class))
                .block().getBody();
    }
    @Override
    public String startTravel(long idScooter, String idAccount) {
        DataTravelDTO dataTravelDTO = getLastUpdate();
        Travel travel = new Travel(idAccount, idScooter, dataTravelDTO.getPricePerMinute(), dataTravelDTO.getPauseLimit(), dataTravelDTO.getExtraPricePerMinute(), dataTravelDTO.getAppliedDate());
        for(TravelObserver observer : observers){
            if(!observer.travelStarted(idScooter)){
                return "Scooter not available";
            }
        }

        travelRepository.save(travel);


        return "Travel started";
    }

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
    public String finishTravel(long id) {
        Travel travel = travelRepository.findById(id).get();
        for(TravelObserver observer : observers){
            if(!observer.travelFinished(travel.getScooterId())){
                return "Scooter is not at a station";
            };
        }

        travel.finishTravel();
        String token = Jwts.builder()
                .setSubject("ScooterService")
                .signWith(SignatureAlgorithm.HS256, SystemSecurity.getKey())
                .compact();
        travelRepository.save(travel);
        webClientMaintenance.put()
                .uri("/scooterReport/updateReport/{id}?usageTime={usageTime}&pauseTime={pauseTime}&km={km}",
                        travel.getScooterId(), travel.getUsageTime(), travel.getPauseDuration(), travel.getKmTraveled())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        webClientAccount.put()
                .uri("/account/{id}/discount?amount={amount}",
                        travel.getAccountId(), travel.getTotalPrice())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "Travel finished";

    }

    @Override
    public String startTravelWTime(Travel travel) {
        travelRepository.save(travel);
        return "Travel started";
    }

    @Override
    public Double getTotalFactured(Integer month1, Integer month2, Integer year) {
        if(month1 == null || month2 == null || year == null)
            return travelRepository.getTotalFactured();
        else
            return travelRepository.getTotalFacturedBetween(month1, month2, year);
    }
}
