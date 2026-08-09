package com.tatasteel.production.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coil_production")
public class CoilProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productionId;

    private String coilId;

    private LocalDateTime birthTime;

    public Long getProductionId() {
        return productionId;
    }

    public void setProductionId(Long productionId) {
        this.productionId = productionId;
    }

    public String getCoilId() {
        return coilId;
    }

    public void setCoilId(String coilId) {
        this.coilId = coilId;
    }

    public LocalDateTime getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(LocalDateTime birthTime) {
        this.birthTime = birthTime;
    }
}