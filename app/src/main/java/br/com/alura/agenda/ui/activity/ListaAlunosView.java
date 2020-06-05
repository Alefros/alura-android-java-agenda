package br.com.alura.agenda.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import br.com.alura.agenda.asynctask.buscaAlunoTask;
import br.com.alura.agenda.asynctask.removeAlunoTask;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private ListaAlunosAdapter adapter;
    private RoomAlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        dao = AgendaDatabase.getInstance(context)
                .getRoomAlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Tem Certeza Que Deseja Remover este aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        new buscaAlunoTask(dao, adapter).execute();
    }

    private void remove(Aluno aluno) {
        new removeAlunoTask(dao, adapter, aluno).execute();
    }

    public void configuraAdapter(final ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(context);
        listaDeAlunos.setAdapter(adapter);
    }
}
