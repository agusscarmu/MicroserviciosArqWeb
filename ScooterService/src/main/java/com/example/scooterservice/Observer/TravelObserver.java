package com.example.scooterservice.Observer;

public interface TravelObserver {

    boolean travelStarted(long id);

    boolean travelFinished(long id);
}
