package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class removeAlunoTask extends AsyncTask<Void, Void, Void> {

    private final RoomAlunoDAO dao;
    private final Aluno aluno;
    private final ListaAlunosAdapter adapter;

    public removeAlunoTask(RoomAlunoDAO alunoDAO, ListaAlunosAdapter adapter, Aluno aluno) {
        this.dao = alunoDAO;
        this.adapter = adapter;
        this.aluno = aluno;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove(aluno);
    }

}
