package com.example.scooterservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Temporal(TemporalType.TIME)
    private Date pauseStartedAt;

    @Temporal(TemporalType.TIME)
    private Date pauseFinishedAt;

    private double totalPrice;

    private double currentPricePerMinute = pricePerMinute;

    private static double pricePerMinute = 15;

    private boolean paused;

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
        this.createdAt = new Date(System.currentTimeMillis());
        this.pricePerMinute = pricePerMinute;
    }
    public Travel(long accountId, long scooterId) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public void finishTravel(){
        if(this.paused){
            this.endPause();
        }
        this.finishedAt = new Date(System.currentTimeMillis());
        this.totalPrice = this.getUsageTime() * pricePerMinute;
    }
    public void startPause(){
        this.pauseStartedAt = new Date(System.currentTimeMillis());
        this.paused = true;
    }

    public int getUsageTime(){
        return (int) ((this.finishedAt.getTime() - this.createdAt.getTime()) / (1000 * 60)); // in minutes
    }
    public void endPause(){
        this.pauseFinishedAt = new Date(System.currentTimeMillis());
        this.paused = false;
    }

    public int getPauseDuration(){
        if(this.pauseStartedAt == null){
            return 0;
        }
        return (int) ((this.pauseFinishedAt.getTime() - this.pauseStartedAt.getTime()) / (1000 * 60)); // in minutes
    }

    public static void setPrice(double price){
        pricePerMinute = price;
    }

    public double getCurrentPricePerMinute(){
        double r = pricePerMinute;
        return r;
    }
}
