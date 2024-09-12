package com.PisciSmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin ("*")
@RestController
@RequestMapping("/data")
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

    @GetMapping("/device/{idDispo}/percentage")
    public ResponseEntity<Double> getSensorDataPercentage(@PathVariable Long idDispo) {
        List<SensorData> sensorDataList = sensorDataService.getDataByDeviceId(idDispo);
        if (!sensorDataList.isEmpty()) {
            // Utiliser les dernières valeurs (ou une autre logique selon vos besoins)
            SensorData latestData = sensorDataList.get(sensorDataList.size() - 1);
            double percentage = sensorDataService.calculateOverallPercentage(
                    latestData.getTemperature(),
                    latestData.getPh(),
                    latestData.getTds()
            );
            return new ResponseEntity<>(percentage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
