package br.edu.unis.listadetarefas.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.edu.unis.listadetarefas.helper.Conversor;
import br.edu.unis.listadetarefas.room.dao.RoomUsuarioDAO;
import br.edu.unis.listadetarefas.room.entity.Tarefa;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;
import br.edu.unis.listadetarefas.room.entity.Usuario;

@Database(entities = {Tarefa.class, Usuario.class}, version = 5)
@TypeConverters({Conversor.class})
public abstract class TarefaDatabase extends RoomDatabase {

    public abstract RoomTarefaDAO getRoomTarefaDAO();
    public abstract RoomUsuarioDAO getRoomUsuarioDAO();

    private static final String BANCO_DE_DADOS = "listadetarefas.db";

    public static RoomTarefaDAO getTarefaDAOInstance(Context contexto) {

        return Room.databaseBuilder(
                contexto,
                TarefaDatabase.class,
                TarefaDatabase.BANCO_DE_DADOS
        )
            //.allowMainThreadQueries()
            //.fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
            .build()
            .getRoomTarefaDAO();
    }

    public static RoomUsuarioDAO getUsuarioDAOInstance(Context contexto) {

        return Room.databaseBuilder(
                contexto,
                TarefaDatabase.class,
                TarefaDatabase.BANCO_DE_DADOS
        )
                //.allowMainThreadQueries()
                //.fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
                .build()
                .getRoomUsuarioDAO();
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `Usuario` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `usuario` TEXT, `senha` TEXT)"
            );
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `Tarefanova` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titulo` TEXT, `descricao` TEXT)"
            );

            database.execSQL("INSERT INTO Tarefanova (id, titulo, descricao) " +
                "SELECT id, titulo, descricao FROM Tarefa"
            );

            database.execSQL("DROP TABLE Tarefa");

            database.execSQL("ALTER TABLE TarefaNova RENAME TO Tarefa");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Tarefa ADD COLUMN `usuario` TEXT");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Tarefa ADD COLUMN `prazo` INTEGER");
        }
    };
}
