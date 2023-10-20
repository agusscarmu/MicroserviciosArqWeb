package com.example.scooterservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_id")
    private long accountId;
    @Column(name = "scooter_id")
    private long scooterId;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account associatedAccount;

    @ManyToOne
    @JoinColumn(name = "scooter_id", insertable = false, updatable = false)
    private Scooter associatedScooter;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pauseStartedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pauseFinishedAt;

    private double totalPrice;

    private double currentPricePerMinute = getUpdatedPricePerMinute();


    private double kmTraveled;

    private boolean paused;

    private static Date lastPriceChange;
    private static double lastPricePerMinute;
    private static double pricePerMinute = 15;
    private static final double kmPerHour = 25;

    public Travel(long accountId, long scooterId, Date createdAt, Date finishedAt, float pricePerMinute) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.pricePerMinute = pricePerMinute;
    }
    public Travel(long accountId, long scooterId, Date createdAt) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = createdAt;
    }
    public Travel(long accountId, long scooterId, float pricePerMinute) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = new Date();
        this.pricePerMinute = pricePerMinute;
    }
    public Travel(long accountId, long scooterId) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = new Date();
    }

    public void finishTravel(){
        if(this.paused){
            this.endPause();
        }
        this.finishedAt = new Date(System.currentTimeMillis());
        this.totalPrice = this.getUsageTime() * pricePerMinute;
        this.kmTraveled = (this.getUsageTime()-this.getPauseDuration()) * (kmPerHour / 60);
    }

    public double getUsageTime(){
        return ((double) (this.finishedAt.getTime() - this.createdAt.getTime()) / (1000 * 60)); // in minutes
    }

    public void startPause(){
        this.pauseStartedAt = new Date();
        this.paused = true;
    }
    public void endPause(){
        this.pauseFinishedAt = new Date();
        this.paused = false;
    }
    public double getPauseDuration(){
        if(this.pauseStartedAt == null){
            return 0;
        }
        return ((double) (this.pauseFinishedAt.getTime() - this.pauseStartedAt.getTime()) / (1000 * 60)); // in minutes
    }

    public static void setPrice(double price){
        lastPriceChange = new Date();
        lastPricePerMinute = price;
    }

    public static void setPrice(double price, Date date){
        lastPriceChange = date;
        lastPricePerMinute = price;
    }

    private double getUpdatedPricePerMinute() {
        Date currentDate = new Date();
        if(lastPriceChange!=null && currentDate.after(lastPriceChange)){
            pricePerMinute = lastPricePerMinute;
        }
        return pricePerMinute;

    }

    public double getCurrentPricePerMinute(){
        double r = pricePerMinute;
        return r;
    }


}
