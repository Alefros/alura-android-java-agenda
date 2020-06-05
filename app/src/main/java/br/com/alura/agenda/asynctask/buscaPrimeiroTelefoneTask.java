package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.agenda.database.TelefoneDao;
import br.com.alura.agenda.model.Telefone;

public class buscaPrimeiroTelefoneTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDao dao;
    private final TextView campoTelefone;
    private final int alunoId;

    public buscaPrimeiroTelefoneTask(TelefoneDao dao, TextView campoTelefone, int alunoId) {
        this.dao = dao;
        this.campoTelefone = campoTelefone;
        this.alunoId = alunoId;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone telefone) {
        super.onPostExecute(telefone);
        if(telefone != null){
            campoTelefone.setText(telefone.getNumero());
        }
    }
}
