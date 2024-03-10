package com.example.marvin;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MQTTDataGenerator {

    private static final String MQTT_BROKER = "tcp://broker.hivemq.com:1883";
    private static final String MQTT_TOPIC = "android/screen/status";  //As mensagens serão publicadas aqui

    private static MqttClient client;
    // client é utilizada para se conectar ao broker MQTT e publicar mensagens

    public static void main(String[] args) {
        try {
            client = new MqttClient(MQTT_BROKER, MqttClient.generateClientId(), new MemoryPersistence());
            client.connect();
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Conexão perdida com o servidor MQTT: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // Não utilizado no script de envio
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Não utilizado no script de envio
                }
            });

            while (true) {
                JSONObject data = generateRandomData();
                String payload = data.toString();
                MqttMessage message = new MqttMessage(payload.getBytes());
                client.publish(MQTT_TOPIC, message);
                Thread.sleep(5000); // Publica a cada 5 segundos
            }
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONObject generateRandomData() throws JSONException {
        Random random = new Random();
        JSONObject data = new JSONObject();
        data.put("x", random.nextInt(1920));
        data.put("y", random.nextInt(1080));
        data.put("status", (random.nextBoolean()) ? "ligado" : "desligado");
        return data;
    }
}
