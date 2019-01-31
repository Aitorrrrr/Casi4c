package com.example.a2dam.quicktrade;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {

    private EditText et_nombre;
    private EditText et_apellidos;
    private EditText et_direccion;
    private Button btn_guardar;

    private DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        et_nombre = (EditText) findViewById(R.id.et_nombrePerf);
        et_apellidos = (EditText) findViewById(R.id.et_apellidosPerf);
        et_direccion = (EditText) findViewById(R.id.et_direccionPerf);
        btn_guardar = (Button) findViewById(R.id.btn_guardarPerf);

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        bbdd = FirebaseDatabase.getInstance().getReference("usuarios").child(usuario.getUid());
        Log.d("MIO",bbdd.toString());

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario auxiliar = dataSnapshot.getValue(Usuario.class);

                et_nombre.setText(auxiliar.getNombre());
                et_apellidos.setText(auxiliar.getApellidos());
                et_direccion.setText(auxiliar.getDireccion());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nombre = et_nombre.getText().toString();
                final String apellidos = et_apellidos.getText().toString();
                final String direccion = et_direccion.getText().toString();

                bbdd.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d("MIO",dataSnapshot.getRef().toString());
                        dataSnapshot.getRef().child("nombre").setValue(nombre);
                        dataSnapshot.getRef().child("apellidos").setValue(apellidos);
                        dataSnapshot.getRef().child("direccion").setValue(direccion);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
