package br.com.alura.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agenda.database.conversor.conversorCalendar;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 3, exportSchema = false)
@TypeConverters({conversorCalendar.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .addMigrations(AgendaMigrations.TODAS_MIGRATIONS)
                .build();
    }
}
