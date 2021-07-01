package br.edu.unis.listadetarefas.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import br.edu.unis.listadetarefas.room.entity.Tarefa;
import java.util.List;

@Dao
public interface RoomTarefaDAO {

    @Insert
    void salvar(Tarefa tarefa);

    @Update
    void editar(Tarefa tarefa);

    @Query("SELECT * FROM Tarefa WHERE usuario = :usuario")
    List<Tarefa> buscarTodos(String usuario);

    @Delete
    void remover(Tarefa tarefa);

}
