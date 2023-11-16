package com.example.dataservice.Controller;

import com.example.dataservice.Security.SystemSecurity;
import com.example.dataservice.Service.Interface.DataTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/dataTravel")
public class DataTravelController {

    @Autowired
    private DataTravelService dataTravelService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<String> addDataTravel(@RequestHeader("Authorization") String token,
                                        @RequestParam(required = false) Double price, @RequestParam(required = false) Long pauseLimit, @RequestParam(required = false) Double extraPricePerMinute, @RequestParam(required = false) Date date){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return dataTravelService.addDataTravel(price, pauseLimit, extraPricePerMinute, date);
    }

    @RequestMapping(value = "/lastUpdate", method = RequestMethod.GET)
    public Serializable getLastUpdate(){
        return dataTravelService.getLastUpdate();
    }
}
