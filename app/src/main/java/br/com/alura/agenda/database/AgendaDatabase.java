package br.com.alura.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agenda.database.conversor.conversorCalendar;
import br.com.alura.agenda.database.conversor.conversorTelefone;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 5, exportSchema = false)
@TypeConverters({conversorCalendar.class, conversorTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, "agenda.db")
                .addMigrations(AgendaMigrations.TODAS_MIGRATIONS)
                .build();
    }

    public abstract TelefoneDao getTelefoneDao();

}
