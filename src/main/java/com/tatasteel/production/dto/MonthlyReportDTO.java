package com.tatasteel.production.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReportDTO {

    private String date;

    private Double target;

    private Double actual;

    private Double balance;

    private Integer coilCount;

    private Double avgWeight;

    private Double totalHours;
    private Double workingHours;
    private Double hourlyAskingRate;
    private Double dailyTarget;
}