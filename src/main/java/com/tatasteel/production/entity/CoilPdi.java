package com.tatasteel.production.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "coil_pdi")

@Data

@IdClass(CoilPdiId.class)

public class CoilPdi {

    /*
        PRIMARY KEY 1
     */
    @Id
    @Column(name = "coil_id")
    private String coilId;

    /*
        PRIMARY KEY 2
     */
    @Id
    @Column(name = "current_birth_date")
    private LocalDateTime currentBirthDate;

    /*
        ORIGINAL BIRTH TIME
     */
    @Column(name = "birth_time")
    private LocalDateTime birthTime;

    /*
        COIL WEIGHT
     */
    @Column(name = "coil_weight")
    private Double coilWeight;
}