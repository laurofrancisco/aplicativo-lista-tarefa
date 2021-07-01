package br.edu.unis.listadetarefas.helper;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Conversor {

    @TypeConverter
    public long paraLongo(Calendar valor) {
        return valor.getTimeInMillis();
    }

    @TypeConverter
    public Calendar paraCalendar(long valor) {
        Calendar valorCalendar = Calendar.getInstance();
        valorCalendar.setTimeInMillis(valor);
        return valorCalendar;
    }

    public static Calendar stringParaCalendar(String valor) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Conversor.getFormato().parse(valor));

        return calendar;
    }

    public static String calendarParaString(Calendar valor) {
        return Conversor.getFormato().format(valor.getTime());
    }

    private static SimpleDateFormat getFormato() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }
}
