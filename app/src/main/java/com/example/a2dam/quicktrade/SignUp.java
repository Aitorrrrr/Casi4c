package com.example.a2dam.quicktrade;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private Button enviar;
    private Button cancelar;

    private EditText user;
    private EditText nom;
    private EditText apell;
    private EditText email;
    private EditText tel;
    private EditText pw;

    private FirebaseAuth mAuth;
    private DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user = (EditText) this.findViewById(R.id.input_usuario);
        nom = (EditText) this.findViewById(R.id.input_nombre);
        apell = (EditText) this.findViewById(R.id.input_apellidos);
        email = (EditText) this.findViewById(R.id.input_email);
        tel = (EditText) this.findViewById(R.id.input_telefono);
        pw = (EditText) this.findViewById(R.id.input_password);

        bbdd = FirebaseDatabase.getInstance().getReference("usuarios");

        enviar = (Button) this.findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String mail = email.getText().toString();
                String password = pw.getText().toString();

                registrar(mail, password);
            }
        });

        cancelar = (Button) this.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    public void registrar(String mail, String password)
    {
        mAuth = FirebaseAuth.getInstance();
        Log.d("MIO",bbdd.toString());

        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            //bbdd.push().getKey();
                            FirebaseUser usuario = mAuth.getCurrentUser();
                            Usuario aux = new Usuario(user.getText().toString(), nom.getText().toString(), apell.getText().toString(), email.getText().toString(), tel.getText().toString(), pw.getText().toString());
                            String id = usuario.getUid();
                            Log.d("MIO",bbdd.child(id).toString());
                            bbdd.child(usuario.getUid()).setValue(aux);

                            setResult(RESULT_OK);
                            finish();
                        }
                        else
                        {
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Log.d("MIO",e.getMessage());
                            Log.d("MIO",e.getErrorCode());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                });
    }
}
