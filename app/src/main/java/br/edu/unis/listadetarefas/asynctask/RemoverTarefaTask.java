package br.edu.unis.listadetarefas.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import br.edu.unis.listadetarefas.adapter.ListaTarefaAdapter;
import br.edu.unis.listadetarefas.model.MinhasPreferencias;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;
import br.edu.unis.listadetarefas.room.entity.Tarefa;

public class RemoverTarefaTask extends AsyncTask<Tarefa, Void, Void>{

    private final RoomTarefaDAO dao;
    private final Context context;

    public RemoverTarefaTask(RoomTarefaDAO dao, Context context) {

        this.dao = dao;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Tarefa... tarefas) {

        Tarefa tarefa = tarefas[0];

        dao.remover(tarefa);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }
    /*
    @Override
    protected List<Tarefa> doInBackground(Void[] objects) {
        return dao.buscarTodos(MinhasPreferencias.getUsuarioLogado(this.context));
    }

    @Override
    protected void onPostExecute(List<Tarefa> todasTarefas) {
        super.onPostExecute(todasTarefas);
        adapter.clear();
        adapter.addAll(todasTarefas);
    }*/
}
