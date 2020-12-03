package com.example.pizza_delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Modelo.Clientes;

public class listado_Clientes_act extends AppCompatActivity {

    private ListView listadoCl;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Clientes clienteSelecto;
    private ArrayList<Clientes> listarClientes = new ArrayList<Clientes>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado__clientes_act);

        listadoCl = (ListView)findViewById(R.id.list);

        inicializarBase();

        listadoCl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clienteSelecto = (Clientes)parent.getItemAtPosition(position);
            }
        });

        databaseReference.child("Clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot objSnapshot: snapshot.getChildren()){
                    Clientes c = objSnapshot.getValue(Clientes.class);
                    listarClientes.add(c);
                    ArrayAdapter adaptaLista = new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,listarClientes);

                    listadoCl.setAdapter(adaptaLista);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void inicializarBase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    public void EliminarCliente(View v){
        Clientes c = new Clientes();
        c.setId(clienteSelecto.getId());
        databaseReference.child("Clientes").child(c.getId()).removeValue();
        Toast.makeText(getBaseContext(),"Se ha eliminado el cliente: " + c.getNombre(),Toast.LENGTH_LONG).show();
    }
}