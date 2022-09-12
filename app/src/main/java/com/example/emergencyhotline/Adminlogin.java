package com.example.emergencyhotline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adminlogin extends AppCompatActivity {
    private FirebaseAuth mAuth;

    DatabaseReference reference;
    EditText password;
    EditText Email;
    TextView reset;
    Button signButton;
    Button login;
    ImageView view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        password = findViewById(R.id.admin_pass);
        signButton=findViewById(R.id.Abtnsignup);
        Email=findViewById(R.id.admin_email);
        login = findViewById(R.id.adminlogin);
        mAuth = FirebaseAuth.getInstance();
        reset=findViewById(R.id.resetpassword1);
        view1=findViewById(R.id.eyepic1);
        reference= FirebaseDatabase.getInstance().getReference("Users");
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Adminlogin.this,resetpassword.class));
            }
        });

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Adminlogin.this,Adminsignup.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

    }


    private void Login() {

        String pass = password.getText().toString().trim();
        String email = Email.getText().toString().trim();


        if (email.isEmpty()) {
            Email.setError("email is required");
            Email.requestFocus();
            return;
        }


        if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            Email.setError("invalid email");
            Email.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        if (pass.length() < 8) {
            password.setError("password is too short (min is 8 characters)");
            password.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // go to
                    FirebaseUser s = mAuth.getCurrentUser();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                UserClass info=dataSnapshot.getValue(UserClass.class);
                                if(info.getEmail().equals(s.getEmail())){
                                    if((info.getIsAdmin()+"").equals("true")){
                                        Toast.makeText(Adminlogin.this, "welcome", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Adminlogin.this,Adminhome.class));
                                    }
                                    else{
                                        Toast.makeText(Adminlogin.this, "failed to login! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                    break;
                                }




                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else {
                    Toast.makeText(Adminlogin.this, "failed to login! Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}