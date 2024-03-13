package com.example.marvin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
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
    private Button northButton;
    private Button southButton;
    private Button topLeftButton;
    private Button topRightButton;
    private Button bottomLeftButton;
    private Button bottomRightButton;

    private Button randomCornerButton1;
    private Button randomCornerButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicia a assinatura do tópico MQTT
        MQTTSubscriber subscriber = new MQTTSubscriber();
        subscriber.start();

        sendButton = findViewById(R.id.send);
        northButton = findViewById(R.id.northButton);
        southButton = findViewById(R.id.southButton);
        topLeftButton = findViewById(R.id.topLeftButton);
        topRightButton = findViewById(R.id.topRightButton);
        bottomLeftButton = findViewById(R.id.bottomLeftButton);
        bottomRightButton = findViewById(R.id.bottomRightButton);
        randomCornerButton1 = findViewById(R.id.randomCornerButton1);
        randomCornerButton2 = findViewById(R.id.randomCornerButton2);



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor();
            }
        });

        // Inicia o movimento aleatório do botão
        startButtonMovement(sendButton);
        startButtonMovement(northButton);
        startButtonMovement(southButton);
        startButtonMovement(topLeftButton);
        startButtonMovement(topRightButton);
        startButtonMovement(bottomLeftButton);
        startButtonMovement(bottomRightButton);



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


    private void startButtonMovement(final Button button) {
        button.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveButtonRandomly(button);
                startButtonMovement(button);
            }
        }, 2000);
    }

    private void moveButtonRandomly(Button button) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        int margin = 100; // Margem de 100 pixels em todas as direções

        int randomX;
        int randomY;

        if (button == northButton) {
            randomX = (int) (Math.random() * (screenWidth - 2 * margin - buttonWidth) + margin);
            randomY = (int) (Math.random() * (screenHeight / 2 - buttonHeight - margin) + margin);
        } else if (button == southButton) {
            randomX = (int) (Math.random() * (screenWidth - 2 * margin - buttonWidth) + margin);
            randomY = (int) (Math.random() * (screenHeight / 2 - buttonHeight - margin) + (screenHeight / 2) + margin);
        } else if (button == topLeftButton) {
            randomX = (int) (Math.random() * (screenWidth / 2 - buttonWidth - margin) + margin);
            randomY = (int) (Math.random() * (screenHeight / 2 - buttonHeight - margin) + margin);
        } else if (button == topRightButton) {
            randomX = (int) (Math.random() * (screenWidth / 2 - buttonWidth - margin) + (screenWidth / 2) + margin);
            randomY = (int) (Math.random() * (screenHeight / 2 - buttonHeight - margin) + margin);
        } else if (button == bottomLeftButton) {
            randomX = (int) (Math.random() * (screenWidth / 2 - buttonWidth - margin) + margin);
            randomY = (int) (Math.random() * (screenHeight / 2 - buttonHeight - margin) + (screenHeight / 2) + margin);
        } else if (button == bottomRightButton) {
            randomX = (int) (Math.random() * (screenWidth / 2 - buttonWidth - margin) + (screenWidth / 2) + margin);
            randomY = (int) (Math.random() * (screenHeight / 2 - buttonHeight - margin) + (screenHeight / 2) + margin);
        } else {
            randomX = (int) (Math.random() * (screenWidth - buttonWidth - 2 * margin) + margin);
            randomY = (int) (Math.random() * (screenHeight - buttonHeight - 2 * margin) + margin);
        }

        button.setX(randomX);
        button.setY(randomY);
    }






}