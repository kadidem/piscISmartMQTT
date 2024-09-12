package com.PisciSmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SensorDataService {
    @Autowired
    private  SensorDataRepository sensorDataRepository;

    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll(); // Méthode pour récupérer toutes les données
    }

    public List<SensorData> getDataByDeviceId(Long idDispo) {
        return sensorDataRepository.findByIdDispo(idDispo); // Méthode pour récupérer les données par ID
    }

    public double calculateOverallPercentage(double temperature, double ph, double tds) {
        // Définir les plages pour chaque paramètre
        double minTemperature = 10.0;
        double maxTemperature = 60.0;
        double minPh = 6.5;
        double maxPh = 8.5;
        int minTds = 50;
        int maxTds = 500;

        // Normaliser chaque valeur entre 0 et 1
        double normalizedTemperature = (temperature - minTemperature) / (maxTemperature - minTemperature);
        double normalizedPh = (ph - minPh) / (maxPh - minPh);
        double normalizedTds = (double)(tds - minTds) / (maxTds - minTds);

        // Calculer le pourcentage global (moyenne des valeurs normalisées)
        double overallPercentage = (normalizedTemperature + normalizedPh + normalizedTds) / 3 * 100;

        // Limiter le pourcentage entre 0 et 100
        overallPercentage = Math.max(0, Math.min(100, overallPercentage));

        return overallPercentage;
    }
}
