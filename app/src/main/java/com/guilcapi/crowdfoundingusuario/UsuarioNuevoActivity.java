package com.guilcapi.crowdfoundingusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UsuarioNuevoActivity extends AppCompatActivity {

    private EditText userID, userNombre, userContrasena;
    private Button btnUnirme;
    private String idUsuario, nombreUsuario, contrasenaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_nuevo);

        userID =findViewById(R.id.usuarioUsuario1);
        userNombre =findViewById(R.id.usuarioNombre1);
        userContrasena =findViewById(R.id.usuarioContrasena1);
        btnUnirme = findViewById(R.id.btnUnirme1);

        btnUnirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idUsuario = userID.getText().toString();
                nombreUsuario = userNombre.getText().toString();
                contrasenaUsuario = userContrasena.getText().toString();
                if(idUsuario.length()>0 && nombreUsuario.length()>0 && contrasenaUsuario.length()>0){
                    CrearAdministrador(idUsuario, nombreUsuario, contrasenaUsuario);
                }else {
                    Toast.makeText(UsuarioNuevoActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CrearAdministrador(final String idUsuario, final String nombreUsuario, final String contrasenaUsuario) {
        final DatabaseReference usuarioReferencia;
        usuarioReferencia = FirebaseDatabase.getInstance().getReference();
        usuarioReferencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!(dataSnapshot.child("Usuario").child(idUsuario).exists())){

                    //Creamos el nuevo usuario
                    HashMap<String, Object> usuario = new HashMap<>();
                    usuario.put("usuario",idUsuario);
                    usuario.put("nombre",nombreUsuario);
                    usuario.put("contrasena",contrasenaUsuario);

                    usuarioReferencia.child("Usuario").child(idUsuario).setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(UsuarioNuevoActivity.this, " Gracias por Unirte ",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(UsuarioNuevoActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }else {
                                Toast.makeText(UsuarioNuevoActivity.this, " Error de red ",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }else {
                    Toast.makeText(UsuarioNuevoActivity.this, " Nombre de Usuario Ocupado con anterioridad",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    }

