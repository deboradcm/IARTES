package com.example.marvin;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

public class ButtonTopLeft {
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
                    moveButtonRandomlyTopLeft(button, windowManager);
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

    private static void moveButtonRandomlyTopLeft(Button button, WindowManager windowManager) {
        // Obtém as dimensões da tela
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        int margin = 0;

        Random random = new Random();

        // Calcula novas coordenadas X e Y para o botão no quadrante esquerdo do topo da tela
        int randomX = (int) (Math.random() * (screenWidth / 2 - buttonWidth - margin) + margin);
        int randomY = (int) (Math.random() * (screenHeight / 2 - buttonHeight - margin) + margin);

        // Define as novas coordenadas para o botão
        button.setX(randomX);
        button.setY(randomY);
    }
}

