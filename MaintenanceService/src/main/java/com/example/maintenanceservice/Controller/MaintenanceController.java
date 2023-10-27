package com.example.maintenanceservice.Controller;

import com.example.maintenanceservice.Model.Maintenance;
import com.example.maintenanceservice.Security.SystemSecurity;
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
    public ResponseEntity<String> underMaintenance(@RequestHeader("Authorization") String token, @RequestParam long ScooterId){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return ResponseEntity.ok(maintanceService.underMaintenance(ScooterId).toString());
    }

    @RequestMapping(value = "/finalizeMaintenance", method = RequestMethod.PUT)
    public ResponseEntity<String> finalizeMaintenance(@RequestHeader("Authorization") String token, @RequestParam("id") long maintenance){
        String request = SystemSecurity.decode(token);
        if(!SystemSecurity.isAllowed(request)){
            throw new RuntimeException(request+ " Not allowed");
        }
        return ResponseEntity.ok(maintanceService.finalizeMaintenance(maintenance).toString());
    }

}
