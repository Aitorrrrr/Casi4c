package com.example.a2dam.quicktrade;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String nombreUser;
    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String password;

    public Usuario()
    {

    }

    public Usuario(String nombreUser, String nombre, String apellido, String email, String direccion, String password)
    {
        this.nombreUser = nombreUser;
        this.nombre = nombre;
        this.apellidos = apellido;
        this.email = email;
        this.direccion = direccion;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    //Código de la parcelización
    protected Usuario(Parcel in) {
        nombreUser = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        email = in.readString();
        direccion = in.readString();
        password = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreUser);
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(email);
        dest.writeString(direccion);
        dest.writeString(password);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUser='" + nombreUser + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}