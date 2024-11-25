package com.PisciSmart.DashBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin ("*")
@RestController

public class SensorDataControler {

    @Autowired
    private SensorDataRepository sensorDataRepository;
    @Autowired
    private SensorDataService sensorDataService;


    @GetMapping("/device")
    public ResponseEntity<List<SensorData>> getAllSensorData() {
        try {
            List<SensorData> sensorData = sensorDataService.getAllSensorData();
            if (sensorData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sensorData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/device/{idDispo}")
    public ResponseEntity<List<SensorData>> getSensorDataByDeviceId(@PathVariable Long idDispo) {
        try {
            List<SensorData> sensorData = sensorDataService.getDataByDeviceId(idDispo);
            if (sensorData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sensorData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/device/{numSerie}")
    public ResponseEntity<List<SensorData>> getDataByDeviceNumSerie(@PathVariable String numSerie) {
        try {
            List<SensorData> sensorData = sensorDataService.getDataByDeviceNumSerie(numSerie);
            if (sensorData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sensorData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/device/{numSerie}/percentage")
    public ResponseEntity<Double> getSensorDataPercentage(@PathVariable String numSerie) {
        List<SensorData> sensorDataList = sensorDataService.getDataByDeviceNumSerie(numSerie);
        if (!sensorDataList.isEmpty()) {
            // Utiliser les dernières valeurs (ou une autre logique selon vos besoins)
            SensorData latestData = sensorDataList.get(sensorDataList.size() - 1);
            double percentage = sensorDataService.calculateOverallPercentage(
                   // latestData.getTemperature(),
                    latestData.getPh(),
                    latestData.getTds()
            );
            return new ResponseEntity<>(percentage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
