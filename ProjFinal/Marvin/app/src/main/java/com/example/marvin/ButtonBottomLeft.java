package com.example.marvin;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

public class ButtonBottomLeft {
    private static int clickCount = 0;

    public static void setupRandomMoveOnClick(final Button button, final WindowManager windowManager, final Button replacementButton) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                if (clickCount >= 2) {
                    moveButtonRandomlyBottomLeft(button, windowManager);
                    clickCount = 0;
                }
            }


        });
    }

    private static final int MARGIN = 0;

    public static void moveButtonRandomlyBottomLeft(Button button, WindowManager windowManager) {
        // Obtém as dimensões da tela
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        Random random = new Random();

        // Calcula novas coordenadas X e Y para o botão no canto inferior esquerdo
        int randomX = random.nextInt(screenWidth / 2 - buttonWidth - MARGIN) + MARGIN;
        int randomY = random.nextInt(screenHeight / 2 - buttonHeight - MARGIN) + (screenHeight / 2 - buttonHeight - MARGIN);

        // Define as novas coordenadas para o botão
        button.setX(randomX);
        button.setY(randomY);
    }
}

