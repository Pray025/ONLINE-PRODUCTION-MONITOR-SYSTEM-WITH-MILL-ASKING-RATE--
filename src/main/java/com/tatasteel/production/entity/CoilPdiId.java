package com.tatasteel.production.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoilPdiId implements Serializable {

    private String coilId;

    private LocalDateTime currentBirthDate;
}