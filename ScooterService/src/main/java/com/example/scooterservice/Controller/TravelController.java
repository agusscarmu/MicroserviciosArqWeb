package com.example.scooterservice.Controller;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Service.Interface.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( value = "/travels")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<TravelDTO> getTravels(){
        return travelService.getAllTravels();
    }

    @RequestMapping(value = "/{id}/pause", method = RequestMethod.PUT)
    public ResponseEntity<String> pauseTravel(@PathVariable long id){
        return ResponseEntity.ok(travelService.pauseTravel(id));

    }

    @RequestMapping(value = "/{id}/resume", method = RequestMethod.PUT)
    public ResponseEntity<String> resumeTravel(@PathVariable long id){
        return ResponseEntity.ok(travelService.resumeTravel(id));
    }
}
