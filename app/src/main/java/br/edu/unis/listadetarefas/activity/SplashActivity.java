package br.edu.unis.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.model.MinhasPreferencias;

public class SplashActivity extends AppCompatActivity {

    public static final int TEMPO_DE_ATRASO = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        carregando();
    }

    private void carregando() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(temCredenciaisSalvas()) {
                    abreListaDeTarefas();
                } else {
                    abreFormularioDeLogin();
                }
                finish();
            }
        }, TEMPO_DE_ATRASO);
    }

    private void abreListaDeTarefas() {
        startActivity(new Intent(this, ListaTarefasActivity.class));
    }

    private void abreFormularioDeLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private boolean temCredenciaisSalvas() {
        SharedPreferences credenciais = MinhasPreferencias.getMinhasPreferencias(this);

        if (credenciais.contains(MinhasPreferencias.PREFERENCIA_USUARIO)) {
            return true;
        }

        return false;
    }
}