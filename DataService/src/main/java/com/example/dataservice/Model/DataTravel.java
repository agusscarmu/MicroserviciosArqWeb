package com.example.dataservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTravel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double pricePerMinute = 15.0;
    private Long pauseLimit = 15L;
    private Double extraPricePerMinute = 1.5;

    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    public DataTravel(Double price, Long pauseLimit, Double extraPricePerMinute, Date date) {
        this.pricePerMinute = price != null ? price: this.pricePerMinute;
        this.pauseLimit = pauseLimit != null ? pauseLimit: this.pauseLimit;
        this.extraPricePerMinute = extraPricePerMinute != null ? extraPricePerMinute: this.extraPricePerMinute;
        this.appliedDate = date != null ? date : this.appliedDate;
    }
}
