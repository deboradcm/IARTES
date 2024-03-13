package com.example.marvin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class BackgroundView extends View {

    private Paint paint;

    public BackgroundView(Context context) {
        super(context);
        init();
    }

    public BackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init() {
        paint = new Paint();
        paint.setColor(0xFF000000); // 0xFF para a opacidade e os demais 6 dígitos hexadecimais para a cor preta
        paint.setStrokeWidth(10); // largura da linha
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // Obtém as dimensões da View
        float width = (float) getWidth();
        float height = (float) getHeight();


        // Desenha os quadrantes
        canvas.drawLine(width / 2, 0, width / 2, height, paint); // linha vertical
        canvas.drawLine(0, height / 2, width, height / 2, paint); // linha horizontal
    }
}

