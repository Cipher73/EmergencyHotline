package com.example.emergencyhotline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Adminhome extends AppCompatActivity {
TextView click,form,fire,police;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        click = findViewById(R.id.userview1);
        form = findViewById(R.id.formview1);
        fire = findViewById(R.id.firelocations);
        police = findViewById(R.id.policelocations);
        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Adminhome.this,FormData.class));
            }
        });
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Adminhome.this,FireBrigade.class));
            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Adminhome.this,PoliceLocations.class));
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Adminhome.this,DataBaseView.class));
            }
        });

    }
}