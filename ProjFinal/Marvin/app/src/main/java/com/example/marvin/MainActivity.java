package com.example.marvin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicia a assinatura do tópico MQTT
        MQTTSubscriber subscriber = new MQTTSubscriber();
        subscriber.start();

        Button sendButton = findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor();
            }
        });
    }

    private void enviarDadosParaServidor() {
        try {
            Evento evento = new Evento();
            evento.setEvento("clique");

            // Obtém as coordenadas do botão
            Button sendButton = findViewById(R.id.send);
            int x = (int) sendButton.getX();
            int y = (int) sendButton.getY();

            // Preenche as coordenadas no objeto Dados
            Dados dados = new Dados();
            dados.setX(x);
            dados.setY(y);

            evento.setDados(dados);

            // Converte a instância de Evento em JSON
            Gson gson = new Gson();
            String jsonEvento = gson.toJson(evento);

            // Imprime o JSON no Logcat
            Log.d("JSON", jsonEvento);

            // Cria uma instância do cliente MQTT
            MqttClient client = new MqttClient("tcp://broker.hivemq.com:1883", MqttClient.generateClientId());

            // Conecta-se ao servidor MQTT
            client.connect();

            // Cria uma mensagem MQTT com o JSON como payload
            MqttMessage message = new MqttMessage(jsonEvento.getBytes());

            // Publica a mensagem em um tópico MQTT
            client.publish("topic", message);

            // Desconecta do servidor MQTT após a publicação
            client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}


