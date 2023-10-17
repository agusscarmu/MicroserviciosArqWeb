package com.example.scooterservice.Controller;

import com.example.scooterservice.Model.Station;
import com.example.scooterservice.Service.Interface.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addStation(@RequestBody Station station){
        return ResponseEntity.ok(stationService.addStation(station).toString());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteStation(@RequestParam("id") long station){
        return ResponseEntity.ok(stationService.deleteStation(station).toString());
    }
}
