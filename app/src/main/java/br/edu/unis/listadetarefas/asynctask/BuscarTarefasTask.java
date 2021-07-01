package br.edu.unis.listadetarefas.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import br.edu.unis.listadetarefas.adapter.ListaTarefaAdapter;
import br.edu.unis.listadetarefas.model.MinhasPreferencias;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;
import br.edu.unis.listadetarefas.room.entity.Tarefa;

public class BuscarTarefasTask extends AsyncTask<Void, Void, List<Tarefa>> {

    private final ListaTarefaAdapter adapter;
    private final RoomTarefaDAO dao;
    private final Context context;

    public BuscarTarefasTask(ListaTarefaAdapter adapter, RoomTarefaDAO dao, Context context) {
        this.adapter = adapter;
        this.dao = dao;
        this.context = context;
    }

    @Override
    protected List<Tarefa> doInBackground(Void[] objects) {
        return dao.buscarTodos(MinhasPreferencias.getUsuarioLogado(this.context));
    }

    @Override
    protected void onPostExecute(List<Tarefa> todasTarefas) {
        super.onPostExecute(todasTarefas);
        adapter.clear();
        adapter.addAll(todasTarefas);
    }
}
