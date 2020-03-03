package com.julioalfaro.ejemplodb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.julioalfaro.ejemplodb.R;
import com.julioalfaro.ejemplodb.data.Mascota;

import java.util.List;

public class MascotaAdapter extends ArrayAdapter<Mascota> {
    private Context context;
    private int layout;
    private List<Mascota> lista;
    public MascotaAdapter(@NonNull Context context, @NonNull List<Mascota> objects) {
        super(context, R.layout.item_mascota_layout, objects);
        this.context = context;
        this.layout = R.layout.item_mascota_layout;
        this.lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(layout, null);
        }
        if (lista.get(position) != null) {
            ImageView imagen = v.findViewById(R.id.fotoMascota);
            TextView nombre = v.findViewById(R.id.txtItemNombre);
            TextView fecha = v.findViewById(R.id.txtItemFecha);
            if (lista.get(position).getSexo() == 0) {
                imagen.setImageResource(R.drawable.perrito);
            } else {
                imagen.setImageResource(R.drawable.perrita);
            }
            nombre.setText(lista.get(position).getNombre());
            fecha.setText(lista.get(position).getFecha_nac());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(layout, null);
        }
        if (lista.get(position) != null) {
            ImageView imagen = v.findViewById(R.id.fotoMascota);
            TextView nombre = v.findViewById(R.id.txtItemNombre);
            TextView fecha = v.findViewById(R.id.txtItemFecha);
            if (lista.get(position).getSexo() == 0) {
                imagen.setImageResource(R.drawable.perrito);
            } else {
                imagen.setImageResource(R.drawable.perrita);
            }
            nombre.setText(lista.get(position).getNombre());
            fecha.setText(lista.get(position).getFecha_nac());
        }
        return v;
    }
}
