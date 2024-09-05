package com.PisciSmart;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sensordata")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temperature;
    private double tds;
    private double ph;
    @Override
    public String toString() {
        return "SensorData{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", tds=" + tds +
                ", ph=" + ph +
                '}';
    }
}
