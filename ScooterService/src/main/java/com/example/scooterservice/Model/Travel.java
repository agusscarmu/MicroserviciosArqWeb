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
    private String accountId;
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


    private double kmTraveled;

    private boolean paused;

    @Transient
    private Date lastPriceChange;

    @Transient
    private double lastPricePerMinute;
    @Transient
    private double pricePerMinute = 15;

    private double currentPricePerMinute;


    private static final double kmPerHour = 25;
    @Transient
    private double extraPricePerMinute ;

    private long pauseLimit;

    public Travel(String accountId, long scooterId, Date createdAt, Date finishedAt, float pricePerMinute) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.pricePerMinute = pricePerMinute;
    }
    public Travel(String accountId, long scooterId, Date createdAt) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = createdAt;
    }
    public Travel(String accountId, long scooterId, float pricePerMinute) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = new Date();
        this.pricePerMinute = pricePerMinute;
    }
    public Travel(String accountId, long scooterId) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        this.createdAt = new Date();
    }

    public Travel(String accountId, long scooterId, double pricePerMinute, long pauseLimit, double extraPricePerMinute, Date lastPriceChange) {
        this.accountId = accountId;
        this.scooterId = scooterId;
        if(lastPriceChange==null){
            this.pricePerMinute = pricePerMinute;
        }else{
            this.lastPricePerMinute = pricePerMinute;
        }
        this.currentPricePerMinute = getUpdatedPricePerMinute();
        this.pauseLimit = pauseLimit;
        this.extraPricePerMinute = extraPricePerMinute;
        this.lastPriceChange = lastPriceChange;
        this.createdAt = new Date();
    }

    public void finishTravel(){
        if(this.paused){
            this.endPause();
        }
        this.finishedAt = new Date(System.currentTimeMillis());
        this.totalPrice = this.getPauseDuration() < pauseLimit ?
                (this.getUsageTime() - this.getPauseDuration()) * currentPricePerMinute :
                ((double) (this.pauseStartedAt.getTime() - this.createdAt.getTime()) / (1000*60) * currentPricePerMinute) + ((double) (this.finishedAt.getTime() - (this.pauseStartedAt.getTime()+pauseLimit)) / (1000*60) * extraPricePerMinute * currentPricePerMinute);
        this.kmTraveled = this.getPauseDuration() < pauseLimit ? (this.getUsageTime()-this.getPauseDuration()) * (kmPerHour / 60) : (this.getUsageTime()-pauseLimit) * (kmPerHour / 60);
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

    private double getUpdatedPricePerMinute() {
        Date now = new Date();
        if(lastPriceChange!=null && now.after(this.lastPriceChange)){
            pricePerMinute = lastPricePerMinute;
        }
        return pricePerMinute;
    }
}
