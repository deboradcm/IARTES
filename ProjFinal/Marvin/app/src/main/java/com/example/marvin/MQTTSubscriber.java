package com.example.marvin;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTSubscriber {

    private static final String BROKER_URL = "tcp://broker.hivemq.com:1883"; // Endereço do servidor MQTT
    private static final String TOPIC = "seu/topico"; // Tópico MQTT que você deseja assinar

    public void start() {
        try {
            // Cria um cliente MQTT
            MqttClient client = new MqttClient(BROKER_URL, MqttClient.generateClientId(), new MemoryPersistence());

            // Configura opções de conexão
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);

            // Define um callback para processar as mensagens recebidas
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    // Implemente o que fazer quando a conexão é perdida
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // Implemente o que fazer quando uma mensagem é recebida
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Implemente o que fazer quando a entrega da mensagem é completa
                }
            });

            // Conecta ao servidor MQTT
            client.connect(options);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
