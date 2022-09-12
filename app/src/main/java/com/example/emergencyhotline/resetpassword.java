package com.example.emergencyhotline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetpassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btnReset;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mAuth = FirebaseAuth.getInstance();

        btnReset = findViewById(R.id.btnReset);
        email = findViewById(R.id.user_email);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });



    }

    private void Reset() {

        String user_email = email.getText().toString().trim();
        if(user_email.isEmpty()){
            email.setError("email is required");
            email.requestFocus();
            return;
        }

        if(!(Patterns.EMAIL_ADDRESS.matcher(user_email).matches())){
            email.setError("invalid email");
            email.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(resetpassword.this, "Email sent! Please Check Email", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(resetpassword.this, "failed to reset password! try again", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}