package br.edu.unis.listadetarefas.model;

import android.content.Context;
import android.content.SharedPreferences;

public class MinhasPreferencias {

    private static final String TIPO_PREFERENCIA = "credenciais";
    public static final String PREFERENCIA_USUARIO = "credencial_usuario";

    public static SharedPreferences getMinhasPreferencias(Context contexto) {
        return contexto.getSharedPreferences(
                MinhasPreferencias.TIPO_PREFERENCIA,
                contexto.MODE_PRIVATE
        );
    }

    public static SharedPreferences.Editor getMinhasPreferenciasEditor(Context contexto) {
        return contexto.getSharedPreferences(
                MinhasPreferencias.TIPO_PREFERENCIA,
                contexto.MODE_PRIVATE
        ).edit();
    }

    public static String getUsuarioLogado(Context contexto) {
        SharedPreferences sp = contexto.getSharedPreferences(
            MinhasPreferencias.TIPO_PREFERENCIA,
            contexto.MODE_PRIVATE
        );

        return sp.getString(MinhasPreferencias.PREFERENCIA_USUARIO, "visitante");
    }

}
