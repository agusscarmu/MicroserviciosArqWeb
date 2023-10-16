package com.example.scooterservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account associatedAccount;

    @ManyToOne
    @JoinColumn(name = "scooter_id")
    private Scooter associatedScooter;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedAt;

    @Temporal(TemporalType.TIME)
    private Date pauseStartedAt;

    @Temporal(TemporalType.TIME)
    private Date pauseFinishedAt;

    private float pricePerMinute;

    private boolean paused;

    public Travel(Account associatedAccount, Scooter associatedScooter, Date createdAt, Date finishedAt, float pricePerMinute) {
        this.associatedAccount = associatedAccount;
        this.associatedScooter = associatedScooter;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.pricePerMinute = pricePerMinute;
    }
    public Travel(Account associatedAccount, Scooter associatedScooter, Date createdAt, float pricePerMinute) {
        this.associatedAccount = associatedAccount;
        this.associatedScooter = associatedScooter;
        this.createdAt = createdAt;
        this.pricePerMinute = pricePerMinute;
    }

    public void finishTravel(){
        if(this.paused){
            this.endPause();
        }
        this.finishedAt = new Date(System.currentTimeMillis());
    }
    public void startPause(){
        this.pauseStartedAt = new Date(System.currentTimeMillis());
        this.paused = true;
    }

    public void endPause(){
        this.pauseFinishedAt = new Date(System.currentTimeMillis());
        this.paused = false;
    }

    public int getPauseDuration(){
        return (int) ((this.pauseFinishedAt.getTime() - this.pauseStartedAt.getTime()) / (1000 * 60)); // in minutes
    }
}
