package com.julioalfaro.ejemplodb.data;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class Mascota {
    //POJO Plain Old fashion Java Object
    private int id;
    private String nombre;
    private String fecha_nac;
    private int sexo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public static List<Mascota> convertCursorToList(Cursor c) {
        List<Mascota> lista = null;
        Mascota temp;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            lista = new ArrayList<Mascota>();
            while (!c.isAfterLast()) {
                temp = new Mascota();
                temp.setNombre(c.getString(c.getColumnIndex("nombre")));
                temp.setSexo(c.getInt(c.getColumnIndex("sexo")));
                temp.setFecha_nac(c.getString(c.getColumnIndex("fecha_nac")));
                temp.setId(c.getInt(c.getColumnIndex("id")));
                lista.add(temp);
                c.moveToNext();
            }
        }
        return lista;
    }
}
