package com.PisciSmart.DashBoard.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Seuil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSeuil;
    private double minTemperature;
    private double maxTemperature;
    private double minPh;
    private double maxPh;
    private int minTds;
    private int maxTds;
}
