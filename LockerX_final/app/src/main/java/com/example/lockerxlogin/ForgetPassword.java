package com.example.lockerxlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    TextView LSignIn, LResetEmail;
    Button LSendResetLink;
    ProgressBar RProgressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        LSignIn = findViewById(R.id.LSignIn);
        LResetEmail = findViewById(R.id.LResetEmail);
        LSendResetLink = findViewById(R.id.LSendResetLink);
        RProgressBar = findViewById(R.id.RProgressBar);

        auth = FirebaseAuth.getInstance();

        LSendResetLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
        LSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
        private void resetPassword() {
            String email = LResetEmail.getText().toString().trim();
            if (email.isEmpty()) {
                LResetEmail.setError("Email is required!");
                LResetEmail.requestFocus();
                return;
            }


            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                LResetEmail.setError("Please provide valid email!");
                LResetEmail.requestFocus();
                return;
            }

            RProgressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgetPassword.this, "Check your email to reset your password!", Toast.LENGTH_LONG);
                    } else {
                        Toast.makeText(ForgetPassword.this, "Try again!", Toast.LENGTH_LONG);
                    }
                    RProgressBar.setVisibility(View.GONE);
                }
            });



    }

}