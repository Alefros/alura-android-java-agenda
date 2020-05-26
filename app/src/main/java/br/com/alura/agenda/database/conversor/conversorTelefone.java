package br.com.alura.agenda.database.conversor;

import androidx.room.TypeConverter;

import br.com.alura.agenda.model.TipoTelefone;

public class conversorTelefone {

    @TypeConverter
    public String telefoneToString(TipoTelefone tipo){
        return tipo.name();
    }

    @TypeConverter
    public TipoTelefone stringToTipoTelefone(String valor){
        if(valor!=null){
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }

}
