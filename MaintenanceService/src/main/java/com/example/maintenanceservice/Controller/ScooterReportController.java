package com.example.maintenanceservice.Controller;

import com.example.maintenanceservice.Service.Interface.ScooterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scooterReport")
public class ScooterReportController {

    @Autowired
    private ScooterReportService scooterReportService;

    @RequestMapping(value = "/updateReport/{id}", method = RequestMethod.PUT)
    public String updateReport(@PathVariable Long id, @RequestParam("usageTime") int usageTime, @RequestParam("pauseTime") int pauseTime) {
        scooterReportService.saveReport(id, usageTime, pauseTime);
        return "updateReport";
    }
}
