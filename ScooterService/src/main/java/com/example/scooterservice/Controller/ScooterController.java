package com.example.scooterservice.Controller;

import com.example.scooterservice.DTO.Scooter.ScooterDTO;
import com.example.scooterservice.Service.Interface.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

    @Autowired
    private ScooterService scooterService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ScooterDTO> getScooters(){
        return scooterService.findAllScooters();
    }

    @RequestMapping(value = "/{scooterId}/maintenance", method = RequestMethod.PUT)
    public ResponseEntity<String> markScooterMaintenance(
            @PathVariable Long scooterId,
            @RequestParam("maintenance") boolean maintenance
    ){
        return ResponseEntity.ok(scooterService.markScooterMaintenance(scooterId, maintenance));
    }

}
