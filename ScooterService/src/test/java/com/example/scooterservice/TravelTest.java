package com.example.scooterservice;

import com.example.scooterservice.Service.Interface.TravelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class TravelTest {

    @Autowired
    private TravelService travelService;

    @Test
    void testAddTravelWithInvalidScooterId() {
        Assertions.assertEquals(ResponseEntity.badRequest().body("Scooter is not available"), travelService.startTravel(99999, "1"));
    }
}
