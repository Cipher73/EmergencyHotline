package com.example.emergencyhotline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hospital_hotline extends AppCompatActivity {

    TextView e_helpline;
    TextView p_helpline;
    TextView firebrigade;
    Bundle bundle;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotline);

        e_helpline=findViewById(R.id.emerg_helpline);
        p_helpline=findViewById(R.id.police_helpline);
        firebrigade=findViewById(R.id.firebrigade);
        bundle = getIntent().getExtras();

        // if the user clicks on the emergency helpline textview
        // they are directed to the form where they fill in some information

        // Extras are passed on to the form activity
        // Extras are just additional information that may be needed in the next activty
        // this was used to help store the information in the database in an orderly fashion at later stage

        e_helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type = "Hospital Helpline";
                startActivity(new Intent(Hospital_hotline.this,formActivity.class).putExtra("service",type));
            }
        });
        p_helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Police Helpline";
                startActivity(new Intent(Hospital_hotline.this, Helpline3.class).putExtra("service",type));
            }
        });
        firebrigade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Fire Brigade";
                startActivity(new Intent(Hospital_hotline.this,Helpline4.class).putExtra("service",type));
            }
        });
    }
}