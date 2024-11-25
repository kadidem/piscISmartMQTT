package com.PisciSmart.DashBoard;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Sensordata")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double temperature;
    private double tds;
    private double ph;
    private long idDispo;
    private String numSerie;
    // Attributs pour les seuils
    private double minTemperature;
    private double maxTemperature;
    private double minPh;
    private double maxPh;
    private int minTds;
    private int maxTds;
    @Override
    public String toString() {
        return "SensorData{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", tds=" + tds +
                ", ph=" + ph +
                ", numSerie=" + numSerie +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", minPh=" + minPh +
                ", maxPh=" + maxPh +
                ", minTds=" + minTds +
                ", maxTds=" + maxTds +
                '}';
    }
}
