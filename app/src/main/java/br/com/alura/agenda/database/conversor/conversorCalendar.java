package br.com.alura.agenda.database.conversor;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class conversorCalendar {

    @TypeConverter
    public Long calendarParaLong(Calendar valor){
        if(valor!=null){
            return valor.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar longParaCalendar(Long valor){
        Calendar momentoAtual = Calendar.getInstance();
        if(valor != null){
            momentoAtual.setTimeInMillis(valor);
        }
        return momentoAtual;
    }

}
