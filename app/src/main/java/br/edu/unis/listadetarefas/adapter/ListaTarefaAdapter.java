package br.edu.unis.listadetarefas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.room.entity.Tarefa;

public class ListaTarefaAdapter extends BaseAdapter {

    private final List<Tarefa> tarefas = new ArrayList<>();
    private final Context context;

    public ListaTarefaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tarefas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(this.context).inflate(R.layout.item_tarefa, parent, false);

        Tarefa tarefa = tarefas.get(position);

        TextView txtTarefaId = viewCriada.findViewById(R.id.item_tarefa_id);
        TextView txtTarefaTitulo = viewCriada.findViewById(R.id.item_tarefa_titulo);
        TextView txtTarefaDescricao = viewCriada.findViewById(R.id.item_tarefa_descricao);

        txtTarefaId.setText("(" + tarefa.getId() + ")");
        txtTarefaTitulo.setText(tarefa.getTitulo());
        txtTarefaDescricao.setText(tarefa.getDescricao());

        return viewCriada;
    }

    public void remove(Tarefa tarefa) {
        this.tarefas.remove(tarefa);
    }

    public void clear() {
        this.tarefas.clear();
    }

    public void addAll(List<Tarefa> tarefas) {
        this.tarefas.addAll(tarefas);
        notifyDataSetChanged();
    }
}
