package com.example.marvin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marvin.Dados;
import com.example.marvin.Evento;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private MqttClient mqttClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicia a conexão MQTT
        startMqttConnection();

        sendButton = findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor();
            }
        });

        // Inicia o movimento aleatório do botão
        startButtonMovement();
    }

    private void startMqttConnection() {
        try {
            mqttClient = new MqttClient("tcp://broker.hivemq.com:1883", MqttClient.generateClientId());
            mqttClient.connect();

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d("MQTT", "Conexão perdida com o servidor MQTT: " + cause.getMessage());
                    // Reconnect if needed
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // Not used for publishing
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used for publishing
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void enviarDadosParaServidor() {
        try {
            // Cria um evento e preenche os dados
            Evento evento = new Evento();
            evento.setEvento("clique");

            // Obtém as coordenadas do botão
            int x = (int) sendButton.getX();
            int y = (int) sendButton.getY();

            // Preenche as coordenadas no objeto Dados
            Dados dados = new Dados();
            dados.setX(x);
            dados.setY(y);

            evento.setDados(dados);

            // Converte o evento em JSON
            Gson gson = new Gson();
            String jsonEvento = gson.toJson(evento);

            // Imprime o JSON no Logcat
            Log.d("JSON", jsonEvento);

            // Publica a mensagem MQTT
            MqttMessage message = new MqttMessage(jsonEvento.getBytes());
            mqttClient.publish("android/screen/status", message);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void startButtonMovement() {
        sendButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveButtonRandomly();
                startButtonMovement();
            }
        }, 2000);
    }

    private void moveButtonRandomly() {
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        Random random = new Random();
        int randomX = random.nextInt(screenWidth - sendButton.getWidth());
        int randomY = random.nextInt(screenHeight - sendButton.getHeight());

        sendButton.setX(randomX);
        sendButton.setY(randomY);
    }
}
