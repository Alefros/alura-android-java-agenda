package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class buscaAlunoTask extends AsyncTask<Void, Void, List<Aluno>> {

    private RoomAlunoDAO dao;
    private ListaAlunosAdapter adapter;

    public buscaAlunoTask(RoomAlunoDAO dao, ListaAlunosAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}
