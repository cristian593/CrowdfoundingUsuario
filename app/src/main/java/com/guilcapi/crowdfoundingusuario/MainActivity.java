package com.guilcapi.crowdfoundingusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText usuarioNombre, usuarioContrasena;
    private Button usuarioIngresar, usuarioUnirme;
    private Intent intent;
    private String usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioNombre = findViewById(R.id.usuarioNombre);
        usuarioContrasena = findViewById(R.id.usuarioContrasena);
        usuarioIngresar = findViewById(R.id.btnIngresarU);
        usuarioUnirme = findViewById(R.id.btnUnirme);

        usuarioIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = usuarioNombre.getText().toString();
                contrasena = usuarioContrasena.getText().toString();
                if(usuario.length()<1 && contrasena.length()<1){
                    Toast.makeText(MainActivity.this, "Ingrese los datos solicitados",Toast.LENGTH_SHORT).show();
                }else {
                    Ingresar(usuario, contrasena);
                }

            }
        });

        usuarioUnirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, UsuarioNuevoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Ingresar(final String usuario, final String contrasena) {
        final DatabaseReference adminRef;
        adminRef = FirebaseDatabase.getInstance().getReference();
        adminRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Usuario").child(usuario).exists()){
                    String contrasena2 =dataSnapshot.child("Usuario").child(usuario).child("contrasena").getValue().toString();
                    if(contrasena2.equals(contrasena)){
                        intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("USER",usuario);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Contraseña equivocada ggggg",Toast.LENGTH_SHORT).show();
                        usuarioContrasena.setText("");
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Usuario no encontrado :(",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    }

