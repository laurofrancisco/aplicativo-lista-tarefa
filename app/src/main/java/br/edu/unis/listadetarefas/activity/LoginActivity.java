package br.edu.unis.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.asynctask.AutenticarUsuarioTask;
import br.edu.unis.listadetarefas.model.MinhasPreferencias;
import br.edu.unis.listadetarefas.room.TarefaDatabase;
import br.edu.unis.listadetarefas.room.dao.RoomUsuarioDAO;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario, txtSenha;
    Button btnEntrar, btnCriarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        carregarWidgets();
        configurarClickBotaoEntrar();
        configurarClickBotaoCriarUsuario();
    }

    @Override
    protected void onResume() {
        if (temCredenciaisSalvas()) {
            abreListaDeTarefas();
        }
        super.onResume();
    }

    private void carregarWidgets() {
        txtUsuario = findViewById(R.id.login_usuario);
        txtSenha = findViewById(R.id.login_senha);
        btnEntrar = findViewById(R.id.login_botao_entrar);
        btnCriarUsuario = findViewById(R.id.login_botao_criar_usuario);
    }

    private void configurarClickBotaoEntrar() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (credenciaisEstaoVazias()) {
                    Toast.makeText(
                            LoginActivity.this,
                            "Usuario e Senha devem estar preenchidos",
                            Toast.LENGTH_LONG
                    ).show();
                } else {

                    RoomUsuarioDAO dao = TarefaDatabase.getUsuarioDAOInstance(getApplicationContext());

                    String usuario = txtUsuario.getText().toString();
                    String senha = txtSenha.getText().toString();

                    new AutenticarUsuarioTask(dao,getApplicationContext()).execute(usuario,senha);

                }
            }
        });
    }

    private void configurarClickBotaoCriarUsuario() {
        btnCriarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, FormularioUsuarioActivity.class));
            }
        });
    }

    private boolean credenciaisEstaoVazias() {
        if (txtUsuario.getText().length() == 0) {
            return true;
        }

        if (txtSenha.getText().length() == 0) {
            return true;
        }

        return false;
    }

    private void autenticar() {
        /*if (autorizaCredenciais()) {

            finish();
        } else {
            Toast.makeText(this, "Usuario ou Senha Incorretos", Toast.LENGTH_LONG).show();
        }*/
    }




    private boolean temCredenciaisSalvas() {
        SharedPreferences credenciais = MinhasPreferencias.getMinhasPreferencias(this);

        if (credenciais.contains(MinhasPreferencias.PREFERENCIA_USUARIO)) {
            return true;
        }

        return false;
    }

    public void abreListaDeTarefas() {

        startActivity(new Intent(
                this,
                ListaTarefasActivity.class
        ));
    }

}








