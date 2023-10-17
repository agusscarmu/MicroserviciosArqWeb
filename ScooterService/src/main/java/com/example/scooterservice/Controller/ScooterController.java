package com.example.scooterservice.Controller;

import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Model.Scooter;
import com.example.scooterservice.Service.Interface.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scooter")
public class ScooterController {

    @Autowired
    private ScooterService scooterService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ScooterDTO> getScooters(){
        return scooterService.findAllScooters();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addScooter(@RequestBody Scooter scooter){
        return ResponseEntity.ok(scooterService.addScooter(scooter).toString());
    }

    @RequestMapping(value = "/updateStation", method = RequestMethod.PUT)
    public ResponseEntity<String> updateStation(
            @RequestParam("scooter")Long scooterId,
            @RequestParam("station")Long stationId
    ){
        return ResponseEntity.ok(scooterService.updateStation(scooterId, stationId));
    }


    @RequestMapping(value = "/maintenance", method = RequestMethod.PUT)
    public ResponseEntity<String> markScooterMaintenance(
            @RequestParam("scooterId")Long scooterId,
            @RequestParam("maintenance") boolean maintenance
    ){
        return ResponseEntity.ok(scooterService.markScooterMaintenance(scooterId, maintenance));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteScooter(@RequestParam("id") Long scooterId){
        return ResponseEntity.ok(scooterService.deleteScooter(scooterId));
    }

}
