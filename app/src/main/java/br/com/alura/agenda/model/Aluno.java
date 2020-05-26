package br.com.alura.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String email;
    private Calendar momentoDoCadastro = Calendar.getInstance();

    @Ignore
    public Aluno(String nome, String telefoneFixo, String telefoneCelular, String email) {
        this.nome = nome;
//        this.telefoneFixo = telefoneFixo;
//        this.telefoneCelular = telefoneCelular;
        this.email = email;
    }

    public Calendar getMomentoDoCadastro() {
        return momentoDoCadastro;
    }

    public void setMomentoDoCadastro(Calendar momentoDoCadastro) {
        this.momentoDoCadastro = momentoDoCadastro;
    }

    public Aluno() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getNomeCompleto() {
        return nome;
    }

}
