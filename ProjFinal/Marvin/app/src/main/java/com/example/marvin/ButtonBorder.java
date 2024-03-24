package com.example.marvin;


import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

public class ButtonBorder {

    private static int clickCount = 0;
    private static int clickGeneralCount = 0;




    public static void setupRandomMoveOnClick(final Button button, final WindowManager windowManager, final Button replacementButton, MainActivity mainActivity) {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                clickCount++;
                clickGeneralCount++;


                if (clickCount == 2 && clickGeneralCount < 20 ) {
                    // Obter as coordenadas após o movimento do botão
                    int x = (int) button.getX();
                    int y = (int) button.getY();
                    String buttonTag = (String) button.getTag();

                    // Enviar dados para o servidor
                    mainActivity.sendDataToServer("click", x, y, buttonTag);

                    // movimenta o botão
                    moveButtonRandomlyBorder(button, windowManager);
                    clickCount = 0;


                } else if (clickCount < 2 && clickGeneralCount < 20) {
                    // Obter as coordenadas após o movimento do botão
                    int x = (int) button.getX();
                    int y = (int) button.getY();
                    String buttonTag = (String) button.getTag();

                    // Enviar dados para o servidor
                    mainActivity.sendDataToServer("click", x, y, buttonTag);

                } if (clickGeneralCount > 20) {
                    button.setVisibility(View.GONE);
                    replacementButton.setVisibility(View.VISIBLE);
                }

            }
        });
    }


    private static void moveButtonRandomlyBorder(Button button, WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        Log.d("ButtonBorder", "Largura da tela: " + screenWidth);
        int screenHeight = displayMetrics.heightPixels;
        Log.d("ButtonBorder", "Altura da tela: " + screenHeight);
        int buttonWidth = button.getWidth();
        Log.d("ButtonBorder", "Largura do botão: " + buttonWidth);
        int buttonHeight = button.getHeight();
        Log.d("ButtonBorder", "Altura do botão: " + buttonHeight);


        Random random = new Random();

        // Escolhe uma borda aleatória: 0 para superior, 1 para direita, 2 para inferior, 3 para esquerda
        int edge = random.nextInt(4);
        Log.d("ButtonBorder", "edge: " + edge);

        int randomX = 0;
        int randomY = 280; // Definindo a posição inicial de Y

        switch (edge) {
            case 0: // Borda superior
                randomX = random.nextInt(900);
                Log.d("ButtonBorder", "caso 0 X: " + randomX);
                randomY = 0;
                Log.d("ButtonBorder", "caso 0 Y: " + randomY);
                Log.d("ButtonBorder", "Largura do botão: " + buttonWidth);
                Log.d("ButtonBorder", "Altura do botão: " + buttonHeight);
                // Define as coordenadas do botão
                button.setX(randomX);
                button.setY(randomY);
                break;
            case 1: // Borda direita
                randomX = 870;
                randomY = random.nextInt(2000);
                Log.d("ButtonBorder", "caso 1 X: " + randomX);
                Log.d("ButtonBorder", "caso 1 Y: " + randomY);
                Log.d("ButtonBorder", "Largura do botão: " + buttonWidth);
                Log.d("ButtonBorder", "Altura do botão: " + buttonHeight);
                // Define as coordenadas do botão
                button.setX(randomX);
                button.setY(randomY);
                break;
            case 2: // Borda inferior
                randomX = random.nextInt(900);
                randomY = 500;
                Log.d("ButtonBorder", "caso 2 X: " + randomX);
                Log.d("ButtonBorder", "caso 2 Y: " + randomY);
                Log.d("ButtonBorder", "Largura do botão: " + buttonWidth);
                Log.d("ButtonBorder", "Altura do botão: " + buttonHeight);
                // Define as coordenadas do botão
                button.setX(randomX);
                button.setY(randomY);
                break;
            case 3: // Borda esquerda
                randomX = 0;
                Log.d("ButtonBorder", "caso 3 X: " + randomX);
                randomY = random.nextInt(2050);
                Log.d("ButtonBorder", "caso 3 Y: " + randomY);
                Log.d("ButtonBorder", "Largura do botão: " + buttonWidth);
                Log.d("ButtonBorder", "Altura do botão: " + buttonHeight);
                // Define as coordenadas do botão
                button.setX(randomX);
                button.setY(randomY);
                break;
        }


    }



}
