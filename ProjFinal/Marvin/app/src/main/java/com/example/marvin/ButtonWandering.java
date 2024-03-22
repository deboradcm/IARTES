package com.example.marvin;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

public class ButtonWandering {
    private static int clickCount = 0;
    private static int clickGeneralCount = 0;


    public static void setupRandomMoveOnClick(final Button button, final WindowManager windowManager, final Button replacementButton) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                clickGeneralCount++;
                if (clickCount >= 2 && clickGeneralCount < 20) {
                    moveButtonRandomly(button, windowManager);
                    clickCount = 0;

                } else if (clickGeneralCount >= 20) {
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
