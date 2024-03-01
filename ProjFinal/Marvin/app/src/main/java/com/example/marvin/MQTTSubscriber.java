package com.example.marvin;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTSubscriber {

    private static final String BROKER_URL = "tcp://broker.hivemq.com:1883"; // Endereço do servidor MQTT
    private static final String TOPIC = "seu/topico"; // Tópico MQTT que desejo assinar

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
                    // Processa a mensagem recebida
                    String payload = new String(message.getPayload());
                    System.out.println("Nova mensagem no tópico '" + topic + "': " + payload);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Implementa o que fazer quando a entrega da mensagem é completa
                    // Obtém o ID do token de entrega
                    int deliveryTokenId = token.getMessageId();

                    // Verifica se a entrega foi bem-sucedida
                    if (token.isComplete()) {
                        System.out.println("Mensagem entregue com sucesso. ID do token de entrega: " + deliveryTokenId);
                    } else {
                        System.out.println("Falha na entrega da mensagem. ID do token de entrega: " + deliveryTokenId);
                    }
                }
            });

            // Conecta ao servidor MQTT
            client.connect(options);

            // Inscreva-se no tópico
            client.subscribe(TOPIC);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
