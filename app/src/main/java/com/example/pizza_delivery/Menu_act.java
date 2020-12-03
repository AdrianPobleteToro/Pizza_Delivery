package com.example.pizza_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_act);
    }

    public void Gestion(View v){
        Intent i = new Intent(this, firebase_act.class);
        startActivity(i);
    }
    public void Listado(View v){
        Intent i = new Intent(this, Promociones_act.class);
        startActivity(i);
    }
}