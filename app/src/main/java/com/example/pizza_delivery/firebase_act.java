package com.example.pizza_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Modelo.Clientes;

public class firebase_act extends AppCompatActivity {

    Button btGuarda;
    EditText etNom,etDest,etPro;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_act);

        btGuarda= (Button)findViewById(R.id.btGuardaCl);
        etNom = (EditText)findViewById(R.id.etNomCl);
        etDest = (EditText)findViewById(R.id.etDestCl);
        etPro = (EditText)findViewById(R.id.etProCl);

        inicializarBase();

        btGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clientes c = new Clientes();
                c.setId(UUID.randomUUID().toString());
                c.setNombre(etNom.getText().toString());
                c.setDestino(etDest.getText().toString());
                c.setPromocion(etPro.getText().toString());

                databaseReference.child("Clientes").child(c.getId()).setValue(c);

                Toast.makeText(getBaseContext(), "Se ha agregado cliente", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void inicializarBase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    public void Listado(View v){
        Intent i = new Intent(this, listado_Clientes_act.class);
        startActivity(i);
    }
}