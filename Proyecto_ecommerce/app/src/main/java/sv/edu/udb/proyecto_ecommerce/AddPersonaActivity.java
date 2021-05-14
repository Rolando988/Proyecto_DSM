package sv.edu.udb.proyecto_ecommerce;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import sv.edu.udb.proyecto_ecommerce.datos.Persona;

public class AddPersonaActivity extends AppCompatActivity {
    EditText edtCategoria, edtDescripccion, edtPrecio, edtUbicacion;
    Button btnsubir;
    private StorageReference nstorage;
    private static final int GALERY_INTENT= 1;
    ImageView SubirImagen;
    ProgressDialog progressDialog;
    TextView prueba1;
    String key="",categoria="",descripccion="",precio="", ubicacion="",accion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String identificador=user.getEmail();


        nstorage= FirebaseStorage.getInstance().getReference();

        btnsubir=findViewById(R.id.btnsubir);
        SubirImagen=findViewById(R.id.imagen);
        progressDialog=new ProgressDialog(this);

        btnsubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALERY_INTENT);
            }
        });

        prueba1=findViewById(R.id.prueba1);
        prueba1.setText(identificador);
        inicializar();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== GALERY_INTENT && resultCode== RESULT_OK){
            progressDialog.setTitle("Subiendo...");
            progressDialog.setMessage("Subiendo foto a la base de datos");
            progressDialog.setCancelable(false);
            progressDialog.show();


            Uri uri= data.getData();
            StorageReference filePath = nstorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                  


                    Toast.makeText(AddPersonaActivity.this,"Se a agregado exitosamente", Toast.LENGTH_LONG).show();
                }
            });

        }
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
