package com.br.controledelicenca.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
    public enum DateFormat {
        USA_FORMAT("MM/dd/yyyy"),
        BR_FORMAT("dd/MM/yyyy");

        private String format;

        private DateFormat(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public static Date parse(String date, DateFormat formato) throws ParseException {
        if(date == null){
            throw new ParseException("Deverá ser informada uma data a ser formatada", 0);
        }
        if(formato == null){
            throw new ParseException("Deverá ser informado um formato de data para a formatação", 0);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(formato.getFormat());
        return sdf.parse(date);
    }

    public static String parse(Date date, DateFormat formato) throws ParseException {
        if(date == null){
            throw new ParseException("Deverá ser informada uma data a ser formatada", 0);
        }
        if(formato == null){
            throw new ParseException("Deverá ser informado um formato de data para a formatação", 0);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(formato.getFormat());
        return sdf.format(date);
    }
}
