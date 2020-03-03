package com.julioalfaro.ejemplodb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.julioalfaro.ejemplodb.adapter.MascotaAdapter;
import com.julioalfaro.ejemplodb.data.Mascota;
import com.julioalfaro.ejemplodb.utilities.DBUtility;

import java.util.List;

public class ListadoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listado;
    private DBUtility conn;
    private List<Mascota> listMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listado = findViewById(R.id.lstMascotas);
        conn = ((AppEjemplo) getApplication()).getDBUtility();
        listMascotas = conn.getMascotas();
        listado.setAdapter(new MascotaAdapter(this, listMascotas));
        listado.setOnItemClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345) {
            listMascotas = conn.getMascotas();
            listado.setAdapter(new MascotaAdapter(ListadoActivity.this, listMascotas));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Accion a realizar");
        builder.setMessage("Que desea hacer con mascota " + listMascotas.get(position).getNombre() + "?");
        builder.setNegativeButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListadoActivity.this, "Borrar", Toast.LENGTH_LONG).show();
                conn.deleteMascota(listMascotas.get(position).getId());
                listMascotas = conn.getMascotas();
                listado.setAdapter(new MascotaAdapter(ListadoActivity.this, listMascotas));
            }
        });
        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListadoActivity.this, "Editar", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ListadoActivity.this, MainActivity.class);
                intent.putExtra("MASCOTA_ID", listMascotas.get(position).getId());
                startActivityForResult(intent, 345);
            }
        });
        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListadoActivity.this, "Cerrar", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        //Toast.makeText(this, "Click en elemento " + listMascotas.get(position).getId(), Toast.LENGTH_LONG).show();
    }
}
