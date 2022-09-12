package com.example.emergencyhotline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Helpline1 extends AppCompatActivity {

    // Hospital Helpline Activity

    TextView medication,emergency,consultation;
    String type;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitalhelpline);
        medication = findViewById(R.id.medication);
        emergency= findViewById(R.id.emergency);
        consultation=findViewById(R.id.consultation);
        bundle = getIntent().getExtras();
        type = bundle.getString("service");
        // is user clicks on medication /emergency/consultation the information is passed on using the intent
        //to the relevant activities so that the proper information is stored at a later stage

        medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(Helpline1.this, "medication selected", Toast.LENGTH_SHORT).show();

                if (type.equals("Hospital Helpline")) {
                    Intent intent = new Intent(Helpline1.this, Helpline2.class);
                    intent.putExtra("service",type);
                    intent.putExtra("type","MEDICATION");
                    startActivity(intent);
                }
            }


        });

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Helpline1.this, "emergency selected", Toast.LENGTH_SHORT).show();


                if (type.equals("Hospital Helpline")) {
                    Intent intent = new Intent(Helpline1.this, Helpline2.class);
                    intent.putExtra("service",type);
                    intent.putExtra("type","EMERGENCY");
                    startActivity(intent);
                }
            }

        });
        consultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Helpline1.this, "consultation selected", Toast.LENGTH_SHORT).show();
                if (type.equals("Hospital Helpline")) {
                    Intent intent = new Intent(Helpline1.this, Helpline2.class);
                    intent.putExtra("service",type);
                    intent.putExtra("type","CONSULTATION");
                    startActivity(intent);
                }
            }

        });




    }
}