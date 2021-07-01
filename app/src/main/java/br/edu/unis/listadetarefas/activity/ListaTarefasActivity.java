package br.edu.unis.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.adapter.ListaTarefaAdapter;
import br.edu.unis.listadetarefas.asynctask.BuscarTarefasTask;
import br.edu.unis.listadetarefas.asynctask.RemoverTarefaTask;
import br.edu.unis.listadetarefas.model.MinhasPreferencias;
import br.edu.unis.listadetarefas.room.entity.Tarefa;
import br.edu.unis.listadetarefas.room.TarefaDatabase;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;


public class ListaTarefasActivity extends AppCompatActivity {

    private ListView listaTarefa;
    private FloatingActionButton fabAddTarefa;
    private ListaTarefaAdapter adapter;
    private RoomTarefaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);
        carregarWidgets();
        configurarFABAddTarefa();
        instaciarRoom();
    }

    @Override
    protected void onResume() {
        super.onResume();

        configurarListaTarefas();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.lista_tarefas_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lista_tarefas_menu_sair, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        selecionarAcaoMenuContexto(item);
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences preferencias = MinhasPreferencias.getMinhasPreferencias(this);

        if (preferencias.contains(MinhasPreferencias.PREFERENCIA_USUARIO)) {
            preferencias.edit()
                    .clear()
                    .commit();
        }

        finish();
        return super.onOptionsItemSelected(item);
    }

    private void selecionarAcaoMenuContexto(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lista_tarefas_menu_remover:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

                alertDialog.setTitle("Remover a tarefa?")
                    .setPositiveButton("Sim",(dialog, which) -> removerTarefa(item))
                    .setNegativeButton("Não", (dialog, which) -> {

                    })
                    .show();

                break;
            case R.id.lista_tarefas_menu_ajuda:
                //Toast.makeText(this, "Hoje não!!!", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void removerTarefa(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Tarefa tarefaSelecionada = (Tarefa) adapter.getItem(menuInfo.position);


        new RemoverTarefaTask(dao,this).execute(tarefaSelecionada);

        //dao.remover(tarefaSelecionada);
        adapter.remove(tarefaSelecionada);
        adapter.notifyDataSetChanged();

        Toast.makeText(
                ListaTarefasActivity.this,
                "Tarefa Apagada",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void carregarWidgets() {
        fabAddTarefa = findViewById(R.id.fab_add_tarefa);
        listaTarefa = findViewById(R.id.lista_tarefas);
    }

    private void configurarFABAddTarefa() {
        fabAddTarefa.setOnClickListener(v -> startActivity(new Intent(
                ListaTarefasActivity.this,
                FormularioTarefaActivity.class
        )));
    }

    private void configurarListaTarefas() {
        configurarAdapterLista();
        recarregarAdapter();
        configurarAcaoClickLista();
    }

    private void instaciarRoom() {
        this.dao = TarefaDatabase.getTarefaDAOInstance(this);
    }

    private void recarregarAdapter() {
        new BuscarTarefasTask(adapter, this.dao, this).execute();
    }

    private void configurarAdapterLista() {
        adapter = new ListaTarefaAdapter(this);
        listaTarefa.setAdapter(adapter);
    }

    private void configurarAcaoClickLista() {
        configurarClickLista();
        configurarClickLongoLista();
    }

    private void configurarClickLista() {
        listaTarefa.setOnItemClickListener((parent, view, position, id) -> {
            Tarefa tarefaSelecionada = (Tarefa) parent.getItemAtPosition(position);

            Intent i = new Intent(
                    ListaTarefasActivity.this,
                    FormularioTarefaActivity.class
            );
            i.putExtra("tarefaSelecionada", tarefaSelecionada);

            startActivity(i);
        });
    }

    private void configurarClickLongoLista() {
        registerForContextMenu(listaTarefa);

        /*listaTarefa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefaSelecionada = (Tarefa) parent.getItemAtPosition(position);

                dao.remover(tarefaSelecionada);
                adapter.remove(tarefaSelecionada);
                adapter.notifyDataSetChanged();

                Toast.makeText(
                        ListaTarefasActivity.this,
                        "Tarefa Apagada",
                        Toast.LENGTH_SHORT
                ).show();

                return false;
            }
        });*/
    }
}




