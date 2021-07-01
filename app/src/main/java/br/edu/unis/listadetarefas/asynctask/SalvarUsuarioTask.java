package br.edu.unis.listadetarefas.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import br.edu.unis.listadetarefas.room.dao.RoomUsuarioDAO;
import br.edu.unis.listadetarefas.room.entity.Usuario;

public class SalvarUsuarioTask extends AsyncTask<Usuario,Void,Void> {
    private final RoomUsuarioDAO dao;
    private final Context context;

    public SalvarUsuarioTask(RoomUsuarioDAO dao, Context context) {
        this.dao = dao;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Usuario... usuarios) {
        dao.salvar(usuarios[0]);
        return null;
    }
}
