package com.example.marvin;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

public class ButtonTopRight {
    private static int clickCount = 0;
    private static int clickGeneralCount = 0;


    public static void setupRandomMoveOnClick(final Button button, final WindowManager windowManager, final Button replacementButton) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                clickGeneralCount++;
                if (clickCount >= 2 && clickGeneralCount < 20) {
                    moveButtonRandomlyTopRight(button, windowManager);
                    clickCount = 0;

                } else if (clickGeneralCount >= 20) {
                    button.setVisibility(View.GONE);
                    replacementButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private static void moveButtonRandomlyTopRight(Button button, WindowManager windowManager) {
        // Obtém as dimensões da tela
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        int margin = 0;

        Random random = new Random();

        // Calcula novas coordenadas X e Y para o botão no quadrante direito do topo
        int randomX = (int) (Math.random() * (screenWidth / 2 - buttonWidth - margin) + screenWidth / 2);
        int randomY = (int) (Math.random() * (screenHeight / 2 - buttonHeight - margin) + margin);

        // Define as novas coordenadas para o botão
        button.setX(randomX);
        button.setY(randomY);
    }
}

