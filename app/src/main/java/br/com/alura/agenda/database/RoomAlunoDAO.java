package br.com.alura.agenda.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Aluno;

@Dao
public interface RoomAlunoDAO {

    @Insert
    Long salva(Aluno aluno);

    @Delete
    void remove(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> todos();

    @Update
    void edita(Aluno aluno);
}
