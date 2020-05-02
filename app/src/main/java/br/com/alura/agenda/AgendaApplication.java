package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosMock();
    }

    private void criaAlunosMock() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Alef", "1122223333", "alef@gmail.com"));
        dao.salva(new Aluno("Rodrigo", "1122223333", "rodrigo@gmail.com"));
    }
}
