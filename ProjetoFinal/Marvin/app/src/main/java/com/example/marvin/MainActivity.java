package com.example.marvin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Capturar as coordenadas do clique
                int x = (int) view.getX();
                int y = (int) view.getY();

                // Criar uma instância da classe Evento e preencher seus campos
                Evento evento = new Evento();
                evento.setEvento("clique");

                Dados dados = new Dados();
                dados.setX(x);
                dados.setY(y);

                evento.setDados(dados);

                // Converter a instância de Evento em JSON
                Gson gson = new Gson();
                String jsonEvento = gson.toJson(evento);

                // Aqui você pode enviar o JSON para o servidor, por exemplo:
                // enviarParaServidor(jsonEvento);
            }
        });
    }
}

