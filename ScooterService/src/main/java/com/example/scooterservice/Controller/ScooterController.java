package com.example.scooterservice.Controller;

import com.example.scooterservice.DTO.Scooter.ScooterAvailableDTO;
import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Model.Scooter;
import com.example.scooterservice.Security.SystemSecurity;
import com.example.scooterservice.Service.Interface.ScooterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.LinkedList;
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
            @RequestHeader("Authorization") String token,
            @RequestParam("scooterId")Long scooterId,
            @RequestParam("maintenance") boolean maintenance
    ){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return ResponseEntity.ok(scooterService.markScooterMaintenance(scooterId, maintenance));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteScooter(@RequestParam("id") Long scooterId){
        return ResponseEntity.ok(scooterService.deleteScooter(scooterId));
    }

    @RequestMapping(value = "/status", method = RequestMethod.PUT)
    public Serializable getStatus(){
        return scooterService.getStatus();
    }

    @RequestMapping(value = "/getBy", method = RequestMethod.GET)
    public List<ScooterAvailableDTO> getScooters(@RequestParam("location") String location){
        return scooterService.getScooters(location);
    }

    @RequestMapping(value = "/updateLocation", method = RequestMethod.PUT)
    public ResponseEntity<String> updateStation(
            @RequestParam("scooter")Long scooterId,
            @RequestParam("location")String location
    ){
        return ResponseEntity.ok(scooterService.updateLocation(scooterId, location));
    }
}
