package com.example.scooterservice.Controller;

import com.example.scooterservice.DTO.Travel.TravelDTO;
import com.example.scooterservice.Model.Travel;
import com.example.scooterservice.Service.Interface.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ResponseEntity<String> updatePrice(@RequestParam float price, @RequestParam(name="date",required = false)String dateParam){
        if(dateParam != null){
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateParam);
                return ResponseEntity.ok(travelService.updatePrice(price, date));
            }catch (Exception e){
                return ResponseEntity.badRequest().body("Invalid date format");
            }
        }
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

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseEntity<String> startTravel(@RequestParam long idScooter, @RequestParam long idAccount){
        return ResponseEntity.ok(travelService.startTravel(idScooter, idAccount));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTravel(@RequestBody Travel travel){
        return ResponseEntity.ok(travelService.startTravelWTime(travel).toString());
    }

    @RequestMapping(value = "/totalFactured/between", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalFactured(@RequestParam(required = false) int month1, @RequestParam(required = false) int month2, @RequestParam(required = false) int year){
        if(month1 == 0 || month2 == 0 || year == 0)
            return ResponseEntity.ok(travelService.getTotalFactured().toString());
        return ResponseEntity.ok(travelService.getTotalFactured(month1,month2,year).toString());
    }

}
