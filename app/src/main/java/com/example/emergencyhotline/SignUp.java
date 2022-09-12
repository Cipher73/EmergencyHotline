package com.example.emergencyhotline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText confirm_password;
    EditText idNum;
    EditText Email;
    Button register;
    ImageView view1;
    ImageView view2;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.name);
        password = findViewById(R.id.Password);
        confirm_password = findViewById(R.id.confirm_pass);
        idNum = findViewById(R.id.idNum);
        register = findViewById(R.id.register);
        Email=findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
        view1=findViewById(R.id.showpass1);
        view2=findViewById(R.id.showpass2);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    view1.setImageResource(R.drawable.see);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    view1.setImageResource(R.drawable.hide);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    view2.setImageResource(R.drawable.see);
                    confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    view2.setImageResource(R.drawable.hide);
                    confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


    }
    private void Register() {
        String name = username.getText().toString();
        String pass = password.getText().toString().trim();
        String confirm = confirm_password.getText().toString().trim();
        String id = idNum.getText().toString().trim();
        String email = Email.getText().toString().trim();

        if (name.isEmpty()) {
            username.setError("name is required");
            username.requestFocus();
            return;
        }
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

        if (!(id.length() == 13)) {
            idNum.setError("invalid identification number");
            idNum.requestFocus();
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
        if (confirm.isEmpty()) {
            confirm_password.setError("password is required");
            confirm_password.requestFocus();
            return;
        }

        if (!pass.equals(confirm)) {
            confirm_password.setError("passwords do not match");
            confirm_password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user=new User(name,pass,id,email,false);

                    // data is then stored in the Users "table" in the database

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "user has been successfully registered", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUp.this,Hospital_hotline.class));
                            }
                            else{
                                Toast.makeText(SignUp.this, "failed to register", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }
                else{
                    Toast.makeText(SignUp.this, "failed to register", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    }