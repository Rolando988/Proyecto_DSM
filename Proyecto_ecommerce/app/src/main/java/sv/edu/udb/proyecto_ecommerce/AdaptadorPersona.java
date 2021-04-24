package sv.edu.udb.proyecto_ecommerce;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import sv.edu.udb.proyecto_ecommerce.datos.Persona;

import java.util.List;

public class AdaptadorPersona extends ArrayAdapter<Persona> {

    List<Persona> personas;
    private Activity context;

    public AdaptadorPersona(@NonNull Activity context, @NonNull List<Persona> personas) {
        super(context, R.layout.persona_layout, personas);
        this.context = context;
        this.personas = personas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        if (view == null)
             rowview = layoutInflater.inflate(R.layout.persona_layout,null);
        else rowview = view;

        TextView tvCategoria = rowview.findViewById(R.id.tvCategoria);
        TextView tvDescripccion = rowview.findViewById(R.id.tvDescripccion);
        TextView tvPrecio = rowview.findViewById(R.id.tvPrecio);
        TextView tvUbicacion = rowview.findViewById(R.id.tvUbicacion);

        tvCategoria.setText("Categoria : "+personas.get(position).getCategoria());
        tvDescripccion.setText("Descripccion : " + personas.get(position).getDescripccion());
        tvPrecio.setText("Precio : " + personas.get(position).getPrecio());
        tvUbicacion.setText("Ubicacion : " + personas.get(position).getUbicacion());

        return rowview;
    }
}
