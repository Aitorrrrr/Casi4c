package com.example.a2dam.quicktrade;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {

    private String nombre;
    private String descripcion;
    private String precio;
    private String creador;
    private String categoria;

    public Producto()
    {

    }

    public Producto(String nombre, String descripcion, String precio, String creador, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.creador = creador;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getCreador() {
        return creador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    protected Producto(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        precio = in.readString();
        creador = in.readString();
        categoria = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(precio);
        dest.writeString(creador);
        dest.writeString(categoria);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}