package com.example.marvin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Random;


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

        WindowManager windowManager = getWindowManager();
        ButtonBorder.setupRandomMoveOnClick(randomCornerButton1, windowManager, northButton, this );
        ButtonNorth.setupRandomMoveOnClick(northButton, windowManager,southButton, this );
        ButtonSouth.setupRandomMoveOnClick(southButton, windowManager, topRightButton, this);
        ButtonTopRight.setupRandomMoveOnClick(topRightButton, windowManager, topLeftButton, this);
        ButtonTopLeft.setupRandomMoveOnClick(topLeftButton, windowManager, bottomRightButton, this);
        ButtonBottomRight.setupRandomMoveOnClick(bottomRightButton, windowManager, bottomLeftButton, this);
        ButtonBottomLeft.setupRandomMoveOnClick(bottomLeftButton, windowManager, wandering1, this);

    }


    public void sendDataToServer(String evento, int x, int y, String buttonTag) {
        try {
            Evento eventoObj = new Evento();
            eventoObj.setEvento(evento);

            // Preenche as coordenadas e a tag do botão no objeto Dados
            Dados dados = new Dados();
            dados.setX(x);
            dados.setY(y);
            dados.setButtonTag(buttonTag);

            eventoObj.setDados(dados);

            JSONObject jsonEvento = new JSONObject();
            jsonEvento.put("x", eventoObj.getDados().getX());
            jsonEvento.put("y", eventoObj.getDados().getY());
            jsonEvento.put("event", eventoObj.getEvento());
            // Verifica se a tag do botão não está vazia antes de adicioná-la ao JSON
            if (!eventoObj.getDados().getButtonTag().isEmpty()) {
                jsonEvento.put("button", eventoObj.getDados().getButtonTag());
            }

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
            Log.e("TAG", "Erro ao processar MQTT ou JSON: " + e.getMessage(), e);
        }
    }

    private boolean isDragging = false;
    private float startX, startY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TOUCH_EVENT", "X: " + event.getX() + ", Y: " + event.getY() + ", Action: " + event.getAction());

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX(); // acessando a variável startX
                startY = event.getY(); // acessando a variável startY
                isDragging = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isDragging && isDragging(x, y)) {
                    // Se o usuário começar a arrastar, é marcado como arrastando
                    isDragging = true;
                    sendDataToServer("button_drag_start", x, y, "");
                }
                break;

            case MotionEvent.ACTION_UP:
                if (isDragging) {
                    // Se o usuário estava arrastando e parou, é enviada uma mensagem indicando o fim do arrasto
                    sendDataToServer("button_drag_end", x, y, "");
                    isDragging = false;
                } else {
                    // Se não houve arrasto e o dedo foi levantado, verifique se foi um clique fora dos botões
                    if (!isInsideButton(x, y)) {
                        sendDataToServer("click_outside_button", x, y, "");
                    }
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    private boolean isInsideButton(int x, int y) {
        // Verifica cada botão individualmente
        if (isInsideView(x, y, wandering1) || isInsideView(x, y, wandering2) || isInsideView(x, y, wandering3) ||
                isInsideView(x, y, northButton) || isInsideView(x, y, southButton) || isInsideView(x, y, topLeftButton) ||
                isInsideView(x, y, topRightButton) || isInsideView(x, y, bottomLeftButton) || isInsideView(x, y, bottomRightButton) ||
                isInsideView(x, y, randomCornerButton1)) {
            return true;
        }
        return false;
    }

    private boolean isInsideView(int x, int y, View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();

        return (x >= viewX && x <= (viewX + viewWidth) && y >= viewY && y <= (viewY + viewHeight));
    }

    private boolean isDragging(int x, int y) {
        // Calcula a distância entre o ponto inicial e o ponto atual
        double distance = Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2));
        // Se a distância for maior que um determinado limite, consideramos que o usuário está arrastando
        return distance > TOUCH_SLOP;
    }

    private static final int TOUCH_SLOP = 20;


}




