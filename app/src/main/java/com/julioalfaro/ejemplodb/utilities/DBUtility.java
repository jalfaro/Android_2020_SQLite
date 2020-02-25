package com.julioalfaro.ejemplodb.utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.julioalfaro.ejemplodb.utilities.DBUtility.DBHelper;

import com.julioalfaro.ejemplodb.data.Mascota;

import java.util.List;

public class DBUtility {
    public static final String DB_NAME = "Petshop";
    public static final int DB_VERSION = 2;

    private DBHelper conn;
    private Context context;
    private SQLiteDatabase db;

    public DBUtility(Context context) {
        this.context = context;
        this.conn = new DBHelper(context);
    }

    private void getDB(boolean readable) {
        if (conn == null) {
            conn = new DBHelper(context);
        }
        db = (readable) ? conn.getReadableDatabase() : conn.getWritableDatabase();
    }

    //Insert
    public void insertMascota(Mascota m) {
        String query = "INSERT INTO mascota (nombre, sexo, fecha_nac) VALUES ('" + m.getNombre()
                + "'," + m.getSexo() + ",'" + m.getFecha_nac() + "')";
        getDB(false);
        db.execSQL(query);
    }
    //Update
    public void updateMascota(Mascota m) {
        String query = "UPDATE mascota SET nombre = '" + m.getNombre() + "', sexo =" +m.getSexo()
            + ", fecha_nac = '" + m.getFecha_nac() + "' WHERE id = " + m.getId();
        getDB(false);
        db.execSQL(query);
    }

    //Delete
    public void deleteMascota(int id) {
        String query = "DELETE FROM mascota WHERE id = " + id;
        getDB(false);
        db.execSQL(query);
    }

    //Select
    public List<Mascota> getMascotas() {
        List<Mascota> lista = null;
        String query = "SELECT id, nombre, fecha_nac, sexo FROM mascota";
        getDB(true);
        Cursor c = db.rawQuery(query, null);
        lista = Mascota.convertCursorToList(c);
        return lista;
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE mascota (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "       nombre TEXT, sexo INTEGER, " +
                    "       fecha_nac TEXT)";
            db.execSQL(query);
            query = "CREATE TABLE dueno (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion == 1 && newVersion == 2) {
                String query = "CREATE TABLE dueno (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
                db.execSQL(query);
            }
        }
    }
}
