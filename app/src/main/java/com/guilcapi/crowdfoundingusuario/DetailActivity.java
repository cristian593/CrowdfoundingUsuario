package com.guilcapi.crowdfoundingusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productDescription, productName, productemail, productTelefono;
    private Button descargar;
    private String productId, archivoURL, numTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        productImage = findViewById(R.id.product_detail_image);
        productDescription = findViewById(R.id.product_detail_description);
        productName = findViewById(R.id.product_detail_name);
        productemail = findViewById(R.id.product_detail_email);
        productTelefono = findViewById(R.id.product_detail_telefono);
        descargar = findViewById(R.id.btnDescargar);


        productId =getIntent().getExtras().getString("pid");

        getProductDetails();

        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(DetailActivity.this, archivoURL, Toast.LENGTH_SHORT).show();
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(archivoURL));
                startActivity(intentWeb);
            }
        });

        productTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dial =  "tel:" + numTelefono;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });

    }

    private void getProductDetails() {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");
        databaseReference.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Products products = dataSnapshot.getValue(Products.class);

                    productName.setText(products.getPname());
                    productDescription.setText(products.getDescription());
                    productemail.setText(products.getEmail());
                    productTelefono.setText(products.getTelefono());
                    numTelefono=products.getTelefono();
                    archivoURL = products.getArchivo();
                    Picasso.with(DetailActivity.this).load(products.getImage()).fit().into(productImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
