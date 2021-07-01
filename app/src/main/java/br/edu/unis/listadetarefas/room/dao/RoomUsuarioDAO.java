package br.edu.unis.listadetarefas.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.unis.listadetarefas.room.entity.Usuario;

@Dao
public interface RoomUsuarioDAO {

    @Insert
    void salvar(Usuario usuario);

    @Update
    void editar(Usuario usuario);

    @Query("SELECT * FROM Usuario")
    List<Usuario> buscarTodos();

    @Query("SELECT * FROM Usuario WHERE usuario = :usuario AND senha = :senha")
    Usuario autenticarUsuario(String usuario, String senha);

    @Delete
    void remover(Usuario usuario);

}
