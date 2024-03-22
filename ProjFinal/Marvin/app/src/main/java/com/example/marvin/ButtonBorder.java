package com.example.marvin;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

public class ButtonBorder {
    private static int clickCount = 0;
    private static int clickGeneralCount = 0;


    public static void setupRandomMoveOnClick(final Button button, final WindowManager windowManager, final Button replacementButton) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                clickCount++;
                clickGeneralCount++;
                if (clickCount >= 2 && clickGeneralCount < 20) {
                    moveButtonRandomlyBorder(button, windowManager);
                    clickCount = 0;

                } else if (clickGeneralCount >= 20) {
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
        int screenHeight = displayMetrics.heightPixels;
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        int margin = 0;

        Random random = new Random();

        // Limites máximos e mínimos para o movimento do botão
        int minX = margin;
        int minY = margin;
        int maxX = screenWidth - buttonWidth - margin;
        int maxY = screenHeight - buttonHeight - margin;

        // Movimento aleatório ao longo das bordas
        int randomX = 0;
        int randomY = 0;

        // Seleciona aleatoriamente entre movimento horizontal ou vertical
        if (random.nextBoolean()) { // Movimento horizontal
            randomX = random.nextBoolean() ? minX : maxX;
            randomY = random.nextInt(maxY - minY) + minY;
        } else { // Movimento vertical
            randomX = random.nextInt(maxX - minX) + minX;
            randomY = random.nextBoolean() ? minY : maxY;
        }

        // Ajusta as coordenadas para garantir que o botão permaneça dentro dos limites da tela
        randomX = Math.max(minX, Math.min(randomX, maxX));
        randomY = Math.max(minY, Math.min(randomY, maxY));

        // Define as coordenadas do botão
        button.setX(randomX);
        button.setY(randomY);
    }
}
