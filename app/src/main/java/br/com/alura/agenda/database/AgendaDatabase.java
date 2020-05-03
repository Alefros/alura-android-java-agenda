package br.com.alura.agenda.database;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

}
