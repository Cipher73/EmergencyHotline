package com.example.emergencyhotline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class formActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;

    EditText fName,e_fName,lName,e_lName,email,contact,relationship,sex,weight,height,address,status,e_contact,dob,nok,medication;
    Button submit;
    String type;
    Bundle bundle;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);


        bundle = getIntent().getExtras();
        type = bundle.getString("service");
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        e_fName = findViewById(R.id.e_fname);
        e_lName = findViewById(R.id.e_lname);
        e_contact = findViewById(R.id.e_contact);
        email = findViewById(R.id.e_mail);
        contact = findViewById(R.id.number);
        relationship = findViewById(R.id.relationship);
        sex = findViewById(R.id.sex);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        address = findViewById(R.id.address);
        status = findViewById(R.id.status);
        dob = findViewById(R.id.DOB);
        nok = findViewById(R.id.nok);
        medication = findViewById(R.id.medication);

        submit = findViewById(R.id.btnSubmit);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fName,e_fName,lName,e_lName,email,contact,relationship,sex,weight,height,address,status,e_contact,dob,nok,medication
                completeform();
               /* String fname = fName.getText().toString().trim();
                String e_fname = e_fName.getText().toString().trim();
                String lname = lName.getText().toString().trim();
                String e_lname = e_lName.getText().toString().trim();
                String e_mail = email.getText().toString().trim();
                String number = contact.getText().toString().trim();
                String relation = relationship.getText().toString().trim();
                String gender = sex.getText().toString().trim();
                String Weight = weight.getText().toString().trim();
                String Height = height.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String cuffed = status.getText().toString().trim();
                String E_num = e_contact.getText().toString().trim();
                String DOB = dob.getText().toString().trim();
                String NOK = nok.getText().toString().trim();
                String medicine = medication.getText().toString().trim();*/


            }

        });

    }


    private void completeform() {

        String fname= fName.getText().toString().trim();
        String e_fname= e_fName.getText().toString().trim();
        String lname= lName.getText().toString().trim();
        String e_lname= e_lName.getText().toString().trim();
        String e_mail= email.getText().toString().trim();
        String number= contact.getText().toString().trim();
        String relation= relationship.getText().toString().trim();
        String gender= sex.getText().toString().trim();
        String Weight= weight.getText().toString().trim();
        String Height= height.getText().toString().trim();
        String Address= address.getText().toString().trim();
        String cuffed= status.getText().toString().trim();
        String E_num= e_contact.getText().toString().trim();
        String DOB= dob.getText().toString().trim();
        String NOK= nok.getText().toString().trim();
        String medicine= medication.getText().toString().trim();

        if (fname.isEmpty()) {
            fName.setError("enter first name");
            fName.requestFocus();
            return;
        }
        if(lname.isEmpty()){
            lName.setError("enter last name");
            lName.requestFocus();
            return;
        }
        if (DOB.isEmpty()) {
            dob.setError("enter date of birth(dd/mm/yyyy)");
            dob.requestFocus();
            return;
        }
        if(gender.isEmpty()){
            sex.setError("enter gender (F/M)");
            sex.requestFocus();
            return;
        }
        if(gender.length()!=1){
            sex.setError("enter valid gender (F/M)");
            sex.requestFocus();
            return;
        }
        if (number.isEmpty()) {
            contact.setError("Contact Number  is required");
            contact.requestFocus();
            return;
        }

        if(e_mail.isEmpty()){
            email.setError("email is required");
            email.requestFocus();
            return;
        }

        if(!(Patterns.EMAIL_ADDRESS.matcher(e_mail).matches())){
            email.setError("invalid email");
            email.requestFocus();
            return;
        }
        if(Address.isEmpty()){
            address.setError("address is required");
            address.requestFocus();
            return;
        }
        if(cuffed.isEmpty()){
            status.setError("please enter marital Status");
            status.requestFocus();
            return;
        }
        if(NOK.isEmpty()){
            nok.setError("lease enter next of kin");
            nok.requestFocus();
            return;
        }
        if(e_fname.isEmpty()){
            e_fName.setError("please enter first name of contact");
            e_fName.requestFocus();
            return;
        }
        if(e_lname.isEmpty()){
            e_lName.setError("please enter last name of contact");
            e_lName.requestFocus();
            return;
        }
        if(relation.isEmpty()){
            relationship.setError("please enter relationship");
            relationship.requestFocus();
            return;
        }
        if(Weight.isEmpty()){
            weight.setError("please enter Weight(pounds)");
            weight.requestFocus();
            return;
        }

        if(Height.isEmpty() ){
            height.setError("please enter Height(inches)");
            height.requestFocus();
            return;
        }
            FormHelper helper = new FormHelper(fname, e_fname, lname, e_lname, e_mail, number, relation, gender, Weight, Height, Address, cuffed, E_num, DOB, NOK, medicine);
            firebaseDatabase.getReference("FormData").child(number).setValue(helper);
            Toast.makeText(formActivity.this, "information saved", Toast.LENGTH_LONG).show();
            startActivity(new Intent(formActivity.this, Helpline1.class).putExtra("service", type));
        }



}