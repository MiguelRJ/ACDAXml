package com.example.xmlacda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPartes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPartes = (Button) findViewById(R.id.btnPartes);
        btnPartes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnPartes){
            startActivity(new Intent(MainActivity.this,Partes.class));
        }
    }
}
