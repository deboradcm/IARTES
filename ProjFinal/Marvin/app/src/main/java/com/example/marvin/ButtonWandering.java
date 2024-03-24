package com.example.marvin;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

public class ButtonWandering {
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
                    moveButtonRandomly(button, windowManager);
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

    private static void moveButtonRandomly(View button, WindowManager windowManager) {
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        Random random = new Random();
        int randomX = random.nextInt(screenWidth - buttonWidth);
        int randomY = random.nextInt(screenHeight - buttonHeight);
        button.setX(randomX);
        button.setY(randomY);
    }
}
