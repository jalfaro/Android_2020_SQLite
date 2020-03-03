package com.julioalfaro.ejemplodb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.julioalfaro.ejemplodb.data.Mascota;
import com.julioalfaro.ejemplodb.utilities.DBUtility;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nombre, fecha;
    private RadioGroup sexo;
    private Button guardar, verListado;
    private List<Mascota> list;
    private int idMascota;
    private Mascota mascota;
    private DBUtility conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn = ((AppEjemplo) getApplication()).getDBUtility();
        //instanciar las vistas
        idMascota = getIntent().getIntExtra("MASCOTA_ID", 0);
        nombre = findViewById(R.id.txtNombre);
        fecha = findViewById(R.id.txtFecha);
        sexo = findViewById(R.id.rbSexo);
        guardar = findViewById(R.id.btnGuardar);
        verListado = findViewById(R.id.btnListado);
        guardar.setOnClickListener(this);
        verListado.setOnClickListener(this);
        ((RadioButton)sexo.getChildAt(0)).setChecked(true);
        list = ((AppEjemplo) getApplication()).getDBUtility().getMascotas();
        if (idMascota > 0) {
            mascota = conn.getMascota(idMascota);
            nombre.setText(mascota.getNombre());
            fecha.setText(mascota.getFecha_nac());
            ((RadioButton) sexo.getChildAt(mascota.getSexo())).setChecked(true);
            guardar.setText("Actualizar");
            verListado.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        Mascota temp;
        if (v.getId() == R.id.btnGuardar) {
            if (idMascota > 0) {
                mascota.setNombre(nombre.getText().toString());
                mascota.setFecha_nac(fecha.getText().toString());
                mascota.setSexo(((RadioButton) sexo.getChildAt(0)).isChecked() ? 0 : 1);
                conn.updateMascota(mascota);
                finish();
            }else {
                temp = new Mascota();
                temp.setNombre(nombre.getText().toString());
                temp.setFecha_nac(fecha.getText().toString());
                temp.setSexo(((RadioButton) sexo.getChildAt(0)).isChecked() ? 0 : 1);
                conn.insertMascota(temp);
                Toast.makeText(this, "Se guardo exitosamente la mascota", Toast.LENGTH_LONG).show();
                clearData();
            }
        } else if (v.getId() == R.id.btnListado) {
            Intent intent = new Intent(this, ListadoActivity.class);
            startActivity(intent);
        }
    }

    private void clearData() {
        nombre.setText("");
        fecha.setText("");
        ((RadioButton)sexo.getChildAt(0)).setChecked(true);
    }
}
