package com.example.maintenanceservice.Controller;


import com.example.maintenanceservice.Security.SystemSecurity;
import com.example.maintenanceservice.Service.Interface.ScooterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/scooterReport")
public class ScooterReportController {

    @Autowired
    private ScooterReportService scooterReportService;

    @RequestMapping(value = "/updateReport/{id}", method = RequestMethod.PUT)
    public String updateReport(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestParam("usageTime") double usageTime, @RequestParam("pauseTime") double pauseTime,
                               @RequestParam("km") double km){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        scooterReportService.saveReport(id, usageTime, pauseTime, km);
        return "updateReport";
    }

    @RequestMapping(value = "/getReport" , method = RequestMethod.GET)
    public List<Serializable> getReportByKm(@RequestHeader("Authorization") String token, @RequestParam String filter, @RequestParam(required = false) boolean pauseTime){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }else{
            if(filter.equals("km") && pauseTime == true) {
                return scooterReportService.getReportWithPauseTime();
            }
            return scooterReportService.getReport(filter);
        }
    }

    @RequestMapping(value = "/byTravels" , method = RequestMethod.GET)
    public List<Serializable> getReportByTravels(@RequestHeader("Authorization") String token, @RequestParam("moreThan") long x){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return scooterReportService.getReportByTravels(x);
    }




}
