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
    public double calculateOverallPercentage(double ph, double tds) {
        // Définir les plages pour chaque paramètre
        double minPh = 0;
        double maxPh = 14;
        int minTds = 0;
        int maxTds = 5000;

        // Normaliser chaque valeur entre 0 et 1 et limiter les résultats entre 0 et 1
        double normalizedPh = Math.max(0, Math.min(1, (ph - minPh) / (maxPh - minPh)));
        double normalizedTds = Math.max(0, Math.min(1, (double)(tds - minTds) / (maxTds - minTds)));

        // Calculer le pourcentage global (moyenne des valeurs normalisées)
        double overallPercentage = ((normalizedPh + normalizedTds) / 2) * 100;

        // Limiter le pourcentage entre 0 et 100
        overallPercentage = Math.max(0, Math.min(100, overallPercentage));

        return overallPercentage;
    }

}
