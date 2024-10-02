package com.PisciSmart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

        @Bean
        public MqttClient mqttClient() throws Exception {

                MqttClient client = new MqttClient("tcp://13.48.194.11:1883", MqttClient.generateClientId(),
                                new MemoryPersistence());
                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(false); // Ne pas effacer la session après déconnexion
                options.setAutomaticReconnect(true); // Reconnexion automatique en cas de déconnexion
                options.setKeepAliveInterval(60); // Intervalle de maintien de la connexion en secondes
                client.connect(options);

                System.out.println("Vous êtes connecté au broker: tcp://13.50.63.221:1883");

                return client;
        }
}
