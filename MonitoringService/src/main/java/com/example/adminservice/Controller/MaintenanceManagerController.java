package com.example.adminservice.Controller;

import com.example.adminservice.Service.Interface.MaintenanceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/maintenanceManager")
@PreAuthorize("hasAuthority('MAINTENANCE')")
public class MaintenanceManagerController {

    @Autowired
    private MaintenanceManagerService maintenanceManagerService;

    @RequestMapping(value = "/underMaintenance", method = RequestMethod.POST)
    public ResponseEntity<String> underMaintenance(@RequestParam long ScooterId){
        return ResponseEntity.ok(maintenanceManagerService.underMaintenance(ScooterId).toString());
    }

    @RequestMapping(value = "/finalizeMaintenance", method = RequestMethod.PUT)
    public ResponseEntity<String> finalizeMaintenance(@RequestParam("id") long maintenance){
        return ResponseEntity.ok(maintenanceManagerService.finalizeMaintenance(maintenance).toString());
    }

    @RequestMapping(value = "/getReport" , method = RequestMethod.GET)
    public List<Serializable> getReportByKm(@RequestParam String filter, @RequestParam(required = false) Boolean pauseTime){
        return maintenanceManagerService.getReport(filter, pauseTime);
    }
}