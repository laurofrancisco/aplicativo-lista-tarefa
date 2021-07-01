package br.edu.unis.listadetarefas.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import br.edu.unis.listadetarefas.adapter.ListaTarefaAdapter;
import br.edu.unis.listadetarefas.model.MinhasPreferencias;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;
import br.edu.unis.listadetarefas.room.entity.Tarefa;

public class SalvarTarefaTask extends AsyncTask<Tarefa, Void, Void> {

    private final RoomTarefaDAO dao;
    private final Context context;

    public SalvarTarefaTask(RoomTarefaDAO dao, Context context) {
        this.dao = dao;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Tarefa... tarefas) {
        Tarefa tarefa = tarefas[0];
        dao.salvar(tarefa);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }

}
