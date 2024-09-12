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
import java.util.List;

@Service
public class MqttListener {

    @Autowired
    private MqttClient mqttClient;
    @Autowired
    private SensorDataRepository sensorDataRepository;
    @Autowired
    private SensorDataService sensorDataService;

    @Autowired
    private NotificationDataRepository notificationDataRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
                JSONObject jsonObject = new JSONObject(message);

                if (t.equals("WaterQuality/topic")) {
                    double temperature = jsonObject.getDouble("temperature");
                    int tds = jsonObject.getInt("tds");
                    double ph = jsonObject.getDouble("ph");
                    Long idDispo = jsonObject.getLong("idDispo");

                    SensorData sensorData = new SensorData();
                    sensorData.setTemperature(temperature);
                    sensorData.setTds(tds);
                    sensorData.setPh(ph);
                    sensorData.setIdDispo(idDispo);

                    sensorDataRepository.save(sensorData);
                    System.out.println("Données enregistrées avec succès.");
                } else if (t.equals("WaterQuality/alerts")) {
                    String alertMessage = jsonObject.getString("alert");
                    Long idDispo = jsonObject.getLong("idDispo");
                    double temperature = jsonObject.optDouble("temperature", -1);
                    int tds = jsonObject.optInt("tds", -1);
                    double ph = jsonObject.optDouble("ph", -1);

                    NotificationData notification = new NotificationData();
                    notification.setIdDispo(idDispo);
                    notification.setAlertMessage(alertMessage);
                    notification.setTemperature(temperature);
                    notification.setTds(tds);
                    notification.setPh(ph);

                    notificationDataRepository.save(notification);
                    System.out.println("Notification enregistrée avec succès.");
                }
            } catch (Exception e) {
                System.out.println("Erreur lors du traitement des données : " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}

