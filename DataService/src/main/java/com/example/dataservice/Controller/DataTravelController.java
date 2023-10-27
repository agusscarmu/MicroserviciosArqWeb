package com.example.dataservice.Controller;

import com.example.dataservice.Service.Interface.DataTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String addDataTravel(@RequestParam(required = false) Double price, @RequestParam(required = false) Long pauseLimit, @RequestParam(required = false) Double extraPricePerMinute, @RequestParam(required = false) String date) throws ParseException {
        Date dateParsed = null;
        if(date!=null){
            dateParsed = new SimpleDateFormat("dd/MM/yyyy").parse(date);;
        }
        dataTravelService.addDataTravel(price, pauseLimit, extraPricePerMinute, dateParsed);
        return "DataTravel updated";
    }

    @RequestMapping(value = "/lastUpdate", method = RequestMethod.GET)
    public Serializable getLastUpdate(){
        return dataTravelService.getLastUpdate();
    }
}
