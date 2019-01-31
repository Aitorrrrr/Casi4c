package com.example.a2dam.quicktrade;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuscarProd extends AppCompatActivity {

    private Spinner categoria;
    private Button buscar;
    private EditText usuario;
    private ListView listaProd;

    ArrayAdapter<String> adapter;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapterLista;

    private FirebaseAuth mAuth;
    private DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_prod);

        adapterLista=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listaProd.setAdapter(adapterLista);

        categoria = (Spinner) findViewById(R.id.spinner_Busq);
        buscar = (Button) findViewById(R.id.btn_Buscar_Busq);
        usuario = (EditText) findViewById(R.id.user_Busq);
        listaProd = (ListView) findViewById(R.id.lista_Busq);

        String[] arraySpinner = new String[] {"Tecnología", "Coches", "Hogar"};
        adapter = new ArrayAdapter<String>(BuscarProd.this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);

        bbdd = FirebaseDatabase.getInstance().getReference("productos");
        mAuth = FirebaseAuth.getInstance();

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaProd.setAdapter(null);
                String categ = categoria.getSelectedItem().toString();

                buscarProd(categ);
            }
        });
    }

    public void buscarProd(String categ)
    {
        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot auxiliar: dataSnapshot.getChildren())
                {
                    Producto prodAux = auxiliar.getValue(Producto.class);
                    adapterLista.add(prodAux.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
