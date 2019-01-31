package com.example.a2dam.quicktrade;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditarProductos extends AppCompatActivity implements RecyclerProductEdit.interfazEditarProd {

    private RecyclerView rc;
    private RecyclerProductEdit adaptadorRecycler;
    private RecyclerView.LayoutManager rvLM;

    private DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;

    private Button cargar;

    private ArrayList<Producto> arrayProductos;
    private ArrayList<String> arrayIDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_productos);

        cargar = (Button) findViewById(R.id.btn_cargar_edit_prod);
        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarRecycler();
            }
        });

        arrayIDS = new ArrayList<>();
        arrayProductos = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        bbdd = FirebaseDatabase.getInstance().getReference("productos");

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot aux: dataSnapshot.getChildren())
                {
                    Producto auxiliar = aux.getValue(Producto.class);

                    Log.d("MIO",aux.getKey());
                    Log.d("MIO",""+aux.getRef());

                    arrayProductos.add(auxiliar);
                    arrayIDS.add(dataSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void cargarRecycler()
    {
        rc = (RecyclerView) findViewById(R.id.recycler_edit_prod);
        rvLM = new LinearLayoutManager(this);
        rc.setLayoutManager(rvLM);

        adaptadorRecycler = new RecyclerProductEdit(arrayProductos,arrayIDS, this);
        rc.setAdapter(adaptadorRecycler);
    }

    @Override
    public void productoCambia(Producto p, String id) {

    }

    @Override
    public void productoElimina(String id) {

    }
}
