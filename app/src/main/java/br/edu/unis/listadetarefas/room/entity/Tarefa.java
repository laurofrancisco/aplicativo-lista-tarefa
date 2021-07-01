package br.edu.unis.listadetarefas.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;
    private String descricao;
    private String usuario;
    private Calendar prazo;

    public Tarefa(String titulo, String descricao, Calendar prazo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Calendar getPrazo() {
        return prazo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrazo(Calendar prazo) {
        this.prazo = prazo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
