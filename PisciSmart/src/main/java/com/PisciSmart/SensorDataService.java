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

    public List<SensorData> getDataByDeviceNumSerie(String numSerie) {
        return sensorDataRepository.findByNumSerie(numSerie);
    }
    public double calculateOverallPercentage( double ph, double tds) {
        // Définir les plages pour chaque paramètre
        // double minTemperature = 10.0;
        //double maxTemperature = 60.0;
        double minPh = 6.0;
        double maxPh = 8.5;
        int minTds = 400;
        int maxTds = 1500;

        // Normaliser chaque valeur entre 0 et 1
       // double normalizedTemperature = (temperature - minTemperature) / (maxTemperature - minTemperature);
        double normalizedPh = (ph - minPh) / (maxPh - minPh);
        double normalizedTds = (double)(tds - minTds) / (maxTds - minTds);

        // Calculer le pourcentage global (moyenne des valeurs normalisées)
        double overallPercentage = ( normalizedPh + normalizedTds) / 2 * 100;

        // Limiter le pourcentage entre 0 et 100
        overallPercentage = Math.max(0, Math.min(100, overallPercentage));

        return overallPercentage;
    }
}
