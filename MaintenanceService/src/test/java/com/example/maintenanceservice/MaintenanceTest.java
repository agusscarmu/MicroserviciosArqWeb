package com.example.maintenanceservice;

import com.example.maintenanceservice.Service.Interface.MaintenanceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class MaintenanceTest {

    @Autowired
    private MaintenanceService maintenanceService;

    @Test
    @DisplayName("Under maintenance invalid Scooter id test")
    public void testUnderMaintenance() {
        Assertions.assertEquals(ResponseEntity.badRequest().body("Scooter not found"),
                maintenanceService.underMaintenance(9999999));
    }
}
