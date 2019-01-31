package com.example.a2dam.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class nuevoProd extends AppCompatActivity {

    private EditText nombreProd;
    private EditText descripcion;
    private EditText precio;
    private Spinner categoria;
    private Button guardar;

    ArrayAdapter<String> adapter;

    private FirebaseAuth mAuth;
    private DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_prod);

        nombreProd = (EditText) findViewById(R.id.nombreProd_Nuevo);
        descripcion = (EditText) findViewById(R.id.descripcionProd_Nuevo);
        precio = (EditText) findViewById(R.id.precioProd_Nuevo);
        categoria = (Spinner) findViewById(R.id.categoriaProd_Nuevo);

        String[] arraySpinner = new String[] {"Tecnología", "Coches", "Hogar"};
        adapter = new ArrayAdapter<String>(nuevoProd.this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);

        bbdd = FirebaseDatabase.getInstance().getReference("productos");
        mAuth = FirebaseAuth.getInstance();

        guardar = (Button) findViewById(R.id.btn_guardarProd_Nuevo);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser userApp = mAuth.getCurrentUser();

                Producto prodAux = new Producto(nombreProd.getText().toString(), descripcion.getText().toString(), precio.getText().toString(), userApp.getUid(), categoria.getSelectedItem().toString());

                guardarProd(prodAux);
            }
        });
    }

    public void guardarProd(Producto prodAux)
    {
        String clave = bbdd.push().getKey();
        Log.d("MIO", clave);

        bbdd.child(clave).setValue(prodAux);

        Toast.makeText(nuevoProd.this, "Nuevo producto creado.",
                Toast.LENGTH_SHORT).show();
    }
}
