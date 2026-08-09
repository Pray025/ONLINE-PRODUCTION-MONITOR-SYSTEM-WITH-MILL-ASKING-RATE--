package com.tatasteel.production.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class HourlyAskingRateReport {

    @Id
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer hourNumber;

    private Double requiredProductionRate;

    private Double actualProduction;

    private Integer coilsProducedCount;

    private Integer remainingHours;

    private Double cumulativeShortfallSurplus;
}