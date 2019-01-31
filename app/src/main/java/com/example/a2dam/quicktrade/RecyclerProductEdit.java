package com.example.a2dam.quicktrade;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class RecyclerProductEdit extends RecyclerView.Adapter<RecyclerProductEdit.EditProdViewHolder> {

    private ArrayList<Producto> arrayProductos;
    private ArrayList<String> arrayIDS;

    private interfazEditarProd interfaz;

    public static class EditProdViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        public EditText nombre;
        public EditText descrip;
        public EditText precio;
        public Button eliminar;
        public Button guardar;

        public Context context;
        public View v;

        public EditProdViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (EditText) itemView.findViewById(R.id.rc_et_nombre);
            descrip = (EditText) itemView.findViewById(R.id.rc_et_descrip);
            precio = (EditText) itemView.findViewById(R.id.rc_et_precio);
            eliminar = (Button) itemView.findViewById(R.id.rc_btn_Eliminar);
            guardar = (Button) itemView.findViewById(R.id.rc_btn_guardar);

            v = itemView;
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public RecyclerProductEdit(ArrayList<Producto> array,ArrayList<String> arrayIDS, EditarProductos context)
    {
        this.arrayProductos = array;
        this.arrayIDS = arrayIDS;

        try{
            interfaz = (interfazEditarProd) context;
        }catch(ClassCastException ex){
            Log.d("MIO","interfaz mal"+ex.getMessage());
            //.. should log the error or throw and exception
        }
    }

    @NonNull
    @Override
    public EditProdViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_product_edit,viewGroup, false);

        return new EditProdViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EditProdViewHolder editProdViewHolder, int i) {
        final int aux = i;

        editProdViewHolder.nombre.setText(arrayProductos.get(i).getNombre());
        editProdViewHolder.descrip.setText(arrayProductos.get(i).getDescripcion());
        editProdViewHolder.precio.setText(arrayProductos.get(i).getPrecio());

        editProdViewHolder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editProdViewHolder.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayProductos.size();
    }

    public interface interfazEditarProd{

        void productoCambia(Producto p, String id);
        void productoElimina(String id);
    }
}
