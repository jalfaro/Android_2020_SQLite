package com.julioalfaro.ejemplodb;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button guardar;
    private List<Mascota> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instanciar las vistas
        nombre = findViewById(R.id.txtNombre);
        fecha = findViewById(R.id.txtFecha);
        sexo = findViewById(R.id.rbSexo);
        guardar = findViewById(R.id.btnGuardar);
        guardar.setOnClickListener(this);
        ((RadioButton)sexo.getChildAt(0)).setChecked(true);
        list = ((AppEjemplo) getApplication()).getDBUtility().getMascotas();
    }

    @Override
    public void onClick(View v) {
        Mascota temp;
        if (v.getId() == R.id.btnGuardar) {
            temp = new Mascota();
            temp.setNombre(nombre.getText().toString());
            temp.setFecha_nac(fecha.getText().toString());
            temp.setSexo(((RadioButton)sexo.getChildAt(0)).isChecked()?0:1);
            ((AppEjemplo) getApplication()).getDBUtility().insertMascota(temp);
            Toast.makeText(this, "Se guardo exitosamente la mascota", Toast.LENGTH_LONG).show();
            clearData();
        }
    }

    private void clearData() {
        nombre.setText("");
        fecha.setText("");
        ((RadioButton)sexo.getChildAt(0)).setChecked(true);
    }
}
