package sv.edu.udb.proyecto_ecommerce;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import sv.edu.udb.proyecto_ecommerce.datos.Persona;

public class AddPersonaActivity extends AppCompatActivity {
    EditText edtCategoria, edtDescripccion, edtPrecio, edtUbicacion;
    TextView prueba1;
    String key="",categoria="",descripccion="",precio="", ubicacion="",accion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String identificador=user.getEmail();

        prueba1=findViewById(R.id.prueba1);
        prueba1.setText(identificador);
        inicializar();

    }

    private void inicializar() {
        edtCategoria = findViewById(R.id.edtCategoria);
        edtDescripccion = findViewById(R.id.edtDescripccion);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtUbicacion = findViewById(R.id.edtUbicacion);


        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        categoria = datos.getString("categoria");
        descripccion=datos.getString("descripccion");
        precio=datos.getString("precio");
        ubicacion=datos.getString("ubicacion");
        accion=datos.getString("accion");
        edtCategoria.setText(categoria);
        edtDescripccion.setText(descripccion);
        edtPrecio.setText(precio);
        edtUbicacion.setText(ubicacion);
    }

    public void guardar(View v){
        String categoria = edtCategoria.getText().toString();
        String descripccion = edtDescripccion.getText().toString();
        String precio= edtPrecio.getText().toString();
        String ubicacion= edtUbicacion.getText().toString();
        // Se forma objeto persona
        Persona persona = new Persona(categoria,descripccion,precio,ubicacion);

        if (accion.equals("a")) { //Agregar usando push()
            PersonasActivity.refPersonas.push().setValue(persona);
        }
        else // Editar usando setValue
        {
            PersonasActivity.refPersonas.child(key).setValue(persona);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }


}
