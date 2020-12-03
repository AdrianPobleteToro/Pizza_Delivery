package com.example.pizza_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText tUsuario, tPsswrd;
    Button btIngresa;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tUsuario = (EditText)findViewById(R.id.etUser);
        tPsswrd = (EditText)findViewById(R.id.etPwrd);
        btIngresa = (Button)findViewById(R.id.btIni);


        btIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!tUsuario.getText().toString().equalsIgnoreCase("ANDROID") && !tPsswrd.getText().toString().equals("123")){
                    Toast.makeText(MainActivity.this, "Los campos son obligatorios. El usuario es: 'ANDROID' y contraseña: '123'", Toast.LENGTH_LONG).show();

                }else{
                    new Task().execute();
                }
            }
        });

        progressBar = (ProgressBar)findViewById(R.id.pbCarga);

        progressBar.setVisibility(View.INVISIBLE);
    }
    class Task extends AsyncTask<String, Void, String> {

        @Override //Es la configuración inicial de la tarea.
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE); //La barra aparece.
        }

        @Override //Procesa la tarea pesada...
        protected String doInBackground(String... strings) {

            for(int i = 1; i <= 3; i++)
            {
                try {
                    Thread.sleep(1000);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.INVISIBLE);
            Intent i = new Intent(getBaseContext(), Menu_act.class);
            startActivity(i);
        }
    }
}