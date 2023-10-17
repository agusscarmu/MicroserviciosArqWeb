package com.example.scooterservice.Controller;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import com.example.scooterservice.Service.Interface.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/updatePrice", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePrice(@RequestParam float price){
        return ResponseEntity.ok(travelService.updatePrice(price));
    }
    @RequestMapping(value = "/{id}/finish", method = RequestMethod.PUT)
    public ResponseEntity<String> finishTravel(@PathVariable long id){
        return ResponseEntity.ok(travelService.finishTravel(id));
    }

    @RequestMapping(value = "/{id}/resume", method = RequestMethod.PUT)
    public ResponseEntity<String> resumeTravel(@PathVariable long id){
        return ResponseEntity.ok(travelService.resumeTravel(id));
    }

    @RequestMapping(value = "/{idScooter}/{idAccount}/start", method = RequestMethod.POST)
    public ResponseEntity<String> startTravel(@PathVariable long idScooter, @PathVariable long idAccount){
        return ResponseEntity.ok(travelService.startTravel(idScooter, idAccount));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTravel(@RequestBody Travel travel){
        return ResponseEntity.ok(travelService.startTravelWTime(travel).toString());
    }
}
