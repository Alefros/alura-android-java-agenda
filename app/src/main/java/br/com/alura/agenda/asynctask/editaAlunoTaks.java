package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.RoomAlunoDAO;
import br.com.alura.agenda.database.TelefoneDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

public class editaAlunoTaks extends AsyncTask<Void, Void, Void> {

    private final RoomAlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone fixo;
    private final Telefone celular;
    private final TelefoneDao telefoneDao;
    private final AlunoEditadoListener listener;
    private final List<Telefone> telefonesDoAluno;

    public editaAlunoTaks(RoomAlunoDAO alunoDAO,
                          Aluno aluno,
                          Telefone fixo,
                          Telefone celular,
                          TelefoneDao telefoneDao,
                          List<Telefone> telefonesDoAluno,
                          AlunoEditadoListener listener) {

        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.fixo = fixo;
        this.celular = celular;
        this.telefoneDao = telefoneDao;
        this.listener = listener;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefones(aluno.getId(), fixo, celular);
        atualizaIdDosTelefones(fixo, celular);
        telefoneDao.atualiza(fixo, celular);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoEditado();
    }

    public interface AlunoEditadoListener {
        void quandoEditado();
    }
    
    
    private void vinculaAlunoComTelefones(int idAluno, Telefone... telefones) {

        for (Telefone telefone:
                telefones
        ) {
            telefone.setAlunoId(idAluno);
        }
    }

    private void atualizaIdDosTelefones(Telefone fixo, Telefone celular) {
        for (Telefone telefone:
                telefonesDoAluno
        ) {

            if(telefone.getTipo() == TipoTelefone.FIXO){
                fixo.setId(telefone.getId());
            }else if(telefone.getTipo() == TipoTelefone.CELULAR){
                celular.setId(telefone.getId());
            }
        }
    }



}
