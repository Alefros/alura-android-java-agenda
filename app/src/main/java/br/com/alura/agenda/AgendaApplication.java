package br.com.alura.agenda;

import android.app.Application;

import androidx.room.Room;

import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //criaAlunosMock();
    }

    private void criaAlunosMock() {

        AgendaDatabase database = Room
                .databaseBuilder(this, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build();
        RoomAlunoDAO dao = database.getRoomAlunoDAO();

        dao.salva(new Aluno("Alef", "Oliveira", "1122223333", "alef@gmail.com"));
        dao.salva(new Aluno("Rodrigo", "Santos", "1122223333", "rodrigo@gmail.com"));
    }
}
