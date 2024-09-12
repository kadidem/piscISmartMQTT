package com.PisciSmart;

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
    @Override
    public String toString() {
        return "SensorData{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", tds=" + tds +
                ", ph=" + ph +
                ", idDispo=" + idDispo +
                '}';
    }
}
