package com.example.a2dam.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    private Button perfil;
    private Button prodNuevo;
    private Button editarProd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        perfil = (Button) findViewById(R.id.btn_perfil);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, Perfil.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usuario = mAuth.getCurrentUser();
        Log.d("MIO",usuario.getEmail());

        prodNuevo = (Button) findViewById(R.id.btn_nuevoProd);
        prodNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, nuevoProd.class);
                startActivity(i);
            }
        });

        editarProd = (Button) findViewById(R.id.btn_editarProd);
        editarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, EditarProductos.class);
                startActivity(i);
            }
        });
    }
}
