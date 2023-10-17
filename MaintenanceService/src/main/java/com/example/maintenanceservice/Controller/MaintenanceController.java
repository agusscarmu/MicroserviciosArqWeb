package com.example.maintenanceservice.Controller;

import com.example.maintenanceservice.Model.Maintenance;
import com.example.maintenanceservice.Service.Interface.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintanceService;

    @RequestMapping(value = "/underMaintenance", method = RequestMethod.POST)
    public ResponseEntity<String> underMaintenance(@RequestBody Maintenance maintenance){
        return ResponseEntity.ok(maintanceService.underMaintenance(maintenance).toString());
    }

    @RequestMapping(value = "/finalizeMaintenance", method = RequestMethod.PUT)
    public ResponseEntity<String> finalizeMaintenance(@RequestParam("id") long maintenance){
        return ResponseEntity.ok(maintanceService.finalizeMaintenance(maintenance).toString());
    }

}
