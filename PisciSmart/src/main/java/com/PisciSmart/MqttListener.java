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

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Seuils pour la qualité de l'eau (à ajuster selon les besoins)
    //  private static final double MIN_TEMPERATURE = 18.0;
    //private static final double MAX_TEMPERATURE = 30.0;
    private static final double MIN_PH = 6.5;
    private static final double MAX_PH = 8.0;
    private static final int MIN_TDS = 200;
    private static final int MAX_TDS = 500;

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
                   // double temperature = jsonObject.getDouble("temperature");
                    int tds = jsonObject.getInt("tds");
                    double ph = jsonObject.getDouble("ph");
                    String NumSerie = jsonObject.getString("NumSerie");

                    SensorData sensorData = new SensorData();
                  //  sensorData.setTemperature(temperature);
                    sensorData.setTds(tds);
                    sensorData.setPh(ph);
                    sensorData.setNumSerie(NumSerie);

                    sensorDataRepository.save(sensorData);
                    System.out.println("Données enregistrées avec succès.");

                    // Vérification des seuils
                    if (
                            ph < MIN_PH || ph > MAX_PH || tds < MIN_TDS || tds > MAX_TDS) {

                        // Générer une alerte si les valeurs sont hors seuil
                        String alertMessage = generateAlertMessage( ph, tds);
                        publishAlert(alertMessage, NumSerie, ph, tds);
                    }

                }
            } catch (Exception e) {
                System.out.println("Erreur lors du traitement des données : " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private String generateAlertMessage( double ph, int tds) {
        StringBuilder alertMessage = new StringBuilder("Alerte : ");
       // if (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE) {
        //    alertMessage.append("Température hors seuil. ");
        //}
        if (ph < MIN_PH || ph > MAX_PH) {
            alertMessage.append("pH hors seuil. ");
        }
        if (tds < MIN_TDS || tds > MAX_TDS) {
            alertMessage.append("TDS hors seuil. ");
        }
        return alertMessage.toString();
    }

    public void publishAlert(String alertMessage, String NumSerie,  double ph, int tds) throws Exception {
        JSONObject alertData = new JSONObject();
        alertData.put("NumSerie", NumSerie);
        alertData.put("alert", alertMessage);
       // alertData.put("temperature", temperature);
        alertData.put("ph", ph);
        alertData.put("tds", tds);

        MqttMessage message = new MqttMessage(alertData.toString().getBytes(StandardCharsets.UTF_8));
        mqttClient.publish("WaterQuality/alerts", message);
        System.out.println("Alerte publiée: " + alertMessage);
    }
}

