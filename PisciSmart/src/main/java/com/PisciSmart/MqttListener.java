package com.PisciSmart;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MqttListener {

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private SensorDataRepository sensorDataRepository; // Assurez-vous que ce repository est correctement configuré
    private final ObjectMapper objectMapper = new ObjectMapper(); // Déclarez objectMapper ici
    @PostConstruct
    public void init() {
        try {

            subscribe("WaterQuality/topic");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void subscribe(String topic) throws Exception {
        mqttClient.subscribe(topic, (t, msg) -> {
            String message = new String(msg.getPayload(), StandardCharsets.UTF_8);
            System.out.println("Message reçu sur le topic " + t + ": " + message);

            try {
                // Parser le message JSON manuellement
                JSONObject jsonObject = new JSONObject(message);

                // Extraire les valeurs
                double temperature = jsonObject.getDouble("temperature");
                int tds = jsonObject.getInt("tds");
                double ph = jsonObject.getDouble("ph");

                // Créer un nouvel objet SensorData
                SensorData sensorData = new SensorData();
                sensorData.setTemperature(temperature);
                sensorData.setTds(tds);
                sensorData.setPh(ph);

                // Enregistrer l'objet dans la base de données
                sensorDataRepository.save(sensorData);
                System.out.println("Données enregistrées avec succès.");
            } catch (Exception e) {
                System.out.println("Erreur lors du traitement des données : " + e.getMessage());
                e.printStackTrace();
            }
        });
    }



    private SensorData parseMessage(String message) {
        try {
            // Convertir le message JSON en objet SensorData
            return objectMapper.readValue(message, SensorData.class);
        } catch (Exception e) {
            System.out.println("Erreur lors du parsing du message JSON : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

