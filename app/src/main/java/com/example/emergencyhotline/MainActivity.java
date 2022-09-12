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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  //  private FirebaseAuth mAuth;
    EditText username ;
    EditText password;
    EditText Email;
    TextView reset;
    TextView admin;
    Button signButton;
    Button login;
    ImageView view1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.UserName);
        password = findViewById(R.id.Password);
        signButton=findViewById(R.id.btnsignup);
        Email=findViewById(R.id.email);
        login = findViewById(R.id.btnlogin);
       // mAuth = FirebaseAuth.getInstance();
        reset=findViewById(R.id.resetpassword);
        admin=findViewById(R.id.admin);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this,AdminLogin.class));
            }
        });

        view1=findViewById(R.id.eyepic);

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

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,ResetPassword.class));
            }
        });


        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login();
            }
        });

    }

    /*private void Login() {
        String name= username.getText().toString();
        String pass= password.getText().toString().trim();
        String email = Email.getText().toString().trim();

        if(name.isEmpty()){
            username.setError("name is required");
            username.requestFocus();
            return;
        }
        if(email.isEmpty()){
            Email.setError("email is required");
            Email.requestFocus();
            return;
        }


        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            Email.setError("invalid email");
            Email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        if(pass.length()<8){
            password.setError("password is too short (min is 8 characters)");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // go to
                    Toast.makeText(MainActivity.this,"welcome",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,Hospital_Hotline.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"failed to login! Try again!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }*/


}