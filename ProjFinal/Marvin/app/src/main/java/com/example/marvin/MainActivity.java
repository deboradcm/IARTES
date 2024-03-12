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
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicia a assinatura do tópico MQTT
        MQTTSubscriber subscriber = new MQTTSubscriber();
        subscriber.start();

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

    private void enviarDadosParaServidor() {
        try {
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

            // Converte a instância de Evento em JSON
            Gson gson = new Gson();
            String jsonEvento = gson.toJson(evento);

            // Imprime o JSON no Logcat
            Log.d("JSON", jsonEvento);

            // Obtém o caminho para o diretório de persistência de arquivos
            String persistencePath = getApplicationContext().getFilesDir().getAbsolutePath();

            // Cria uma instância do cliente MQTT usando o caminho de persistência de arquivos personalizado
            MqttClient client = new MqttClient("tcp://broker.hivemq.com:1883", MqttClient.generateClientId(), new MqttDefaultFilePersistence(persistencePath));

            // Conecta-se ao servidor MQTT
            client.connect();

            // Cria uma mensagem MQTT com o JSON como payload
            MqttMessage message = new MqttMessage(jsonEvento.getBytes());

            // Publica a mensagem em um tópico MQTT
            client.publish("seu/topico", message);

            // Desconecta do servidor MQTT após a publicação
            client.disconnect();

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

        int randomX = (int) (Math.random() * (screenWidth - sendButton.getWidth()));
        int randomY = (int) (Math.random() * (screenHeight - sendButton.getHeight()));

        sendButton.setX(randomX);
        sendButton.setY(randomY);
    }
}