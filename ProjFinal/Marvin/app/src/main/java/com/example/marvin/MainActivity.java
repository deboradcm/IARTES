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
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button wandering1;
    private Button wandering2;
    private Button wandering3;
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

        wandering1 = findViewById(R.id.wandering1);
        wandering2 = findViewById(R.id.wandering2);
        wandering3 = findViewById(R.id.wandering3);
        northButton = findViewById(R.id.northButton);
        southButton = findViewById(R.id.southButton);
        topLeftButton = findViewById(R.id.topLeftButton);
        topRightButton = findViewById(R.id.topRightButton);
        bottomLeftButton = findViewById(R.id.bottomLeftButton);
        bottomRightButton = findViewById(R.id.bottomRightButton);
        randomCornerButton1 = findViewById(R.id.randomCornerButton1);
        randomCornerButton2 = findViewById(R.id.randomCornerButton2);

        wandering1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        wandering2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        wandering3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        topLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        topRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        bottomLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        bottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        randomCornerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });

        randomCornerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosParaServidor(view);
            }
        });


        // Inicia o movimento aleatório dos botões
        startButtonMovement(wandering1);
        startButtonMovement(wandering2);
        startButtonMovement(wandering3);
        startButtonMovement(northButton);
        startButtonMovement(southButton);
        startButtonMovement(topLeftButton);
        startButtonMovement(topRightButton);
        startButtonMovement(bottomLeftButton);
        startButtonMovement(bottomRightButton);
        startButtonMovement(randomCornerButton1);
        startButtonMovement(randomCornerButton2);
    }

    private void enviarDadosParaServidor(View view) {
        try {
            Evento evento = new Evento();
            evento.setEvento("click");

            // Obtém as coordenadas dos botões
            int x = (int) view.getX();
            int y = (int) view.getY();

            // Obtém a tag do botão clicado
            String buttonTag = (String) view.getTag();

            // Preenche as coordenadas e a tag do botão no objeto Dados
            Dados dados = new Dados();
            dados.setX(x);
            dados.setY(y);
            dados.setButtonTag(buttonTag);

            evento.setDados(dados);

            // Converte a instância de Evento em JSON
            JSONObject jsonEvento = new JSONObject();
            jsonEvento.put("x", evento.getDados().getX());
            jsonEvento.put("y", evento.getDados().getY());
            jsonEvento.put("button", evento.getDados().getButtonTag()); // Use button_tag em vez de button_id
            jsonEvento.put("event", evento.getEvento());

            // Imprime o JSON no Logcat
            Log.d("JSON", jsonEvento.toString());

            // Obtém o caminho para o diretório de persistência de arquivos
            String persistencePath = getApplicationContext().getFilesDir().getAbsolutePath();

            // Cria uma instância do cliente MQTT usando o caminho de persistência de arquivos personalizado
            MqttClient client = new MqttClient("tcp://broker.hivemq.com:1883", MqttClient.generateClientId(), new MqttDefaultFilePersistence(persistencePath));

            // Conecta-se ao servidor MQTT
            client.connect();

            // Cria uma mensagem MQTT com o JSON como payload
            MqttMessage message = new MqttMessage(jsonEvento.toString().getBytes());

            // Publica a mensagem em um tópico MQTT
            client.publish("app/robot_interactions", message);

            // Desconecta do servidor MQTT após a publicação
            client.disconnect();

        } catch (MqttException | JSONException e) {
            e.printStackTrace();
        }
    }


    private void startButtonMovement(final Button button) {
        button.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(button == randomCornerButton1 || button == randomCornerButton2) {
                    moveButtonRandomlyBorder(button);
                } else {
                    moveButtonRandomly(button);
                }
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


    private void moveButtonRandomlyBorder(Button button) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Margem de 0 pixels para que os botões toquem as bordas
        int margin = 0;

        int randomX;
        int randomY;

        // Movimento ao longo das bordas e nos cantos
        if (Math.random() < 0.5) { // Movimento horizontal
            randomX = (int) (Math.random() * (screenWidth - buttonWidth));
            randomY = Math.random() < 0.5 ? margin : screenHeight - buttonHeight;
        } else { // Movimento vertical
            randomX = Math.random() < 0.5 ? margin : screenWidth - buttonWidth;
            randomY = (int) (Math.random() * (screenHeight - buttonHeight));
        }

        button.setX(randomX);
        button.setY(randomY);
    }


}