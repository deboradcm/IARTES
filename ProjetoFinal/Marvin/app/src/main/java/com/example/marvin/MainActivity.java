package com.example.marvin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
            // Criar uma instância da classe Evento e preenche seus campos
            Evento evento = new Evento();
            evento.setEvento("clique");

            // Simular coordenadas fixas para exemplo
            Dados dados = new Dados();
            dados.setX(100);
            dados.setY(200);

            evento.setDados(dados);

            // Converter a instância de Evento em JSON
            Gson gson = new Gson();
            String jsonEvento = gson.toJson(evento);

            // Crie uma instância do cliente MQTT
            MqttClient client = new MqttClient("tcp://broker.hivemq.com:1883", MqttClient.generateClientId());

            // Conecte-se ao servidor MQTT
            client.connect();

            // Criar uma mensagem MQTT com o JSON como payload
            MqttMessage message = new MqttMessage(jsonEvento.getBytes());

            // Publicar a mensagem em um tópico MQTT
            client.publish("topic", message);

            // Desconectar do servidor MQTT após a publicação
            client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}


