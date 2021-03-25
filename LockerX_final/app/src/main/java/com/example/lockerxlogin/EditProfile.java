package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditProfile extends AppCompatActivity {
    EditText plainTextFullName, plaintextMobileNum;
    TextView textViewFullName, textViewMobileNum, textViewEmail,plainTextEmail;
    Button btnChangePassword, btnSaveChanges;
    FirebaseAuth fAuth;
    FirebaseUser user;
    private User currUser;
    //123


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        plainTextFullName = findViewById(R.id.plainTextFullName);
        plaintextMobileNum = findViewById(R.id.plainTextMobileNum);
        plainTextEmail = findViewById(R.id.plainTextEmail);
        textViewFullName = findViewById(R.id.textViewFullName);
        textViewMobileNum = findViewById(R.id.textViewMobileNum);
        textViewEmail = findViewById(R.id.textViewEmail);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        currUser = Login.currUser;

        plainTextFullName.setText(currUser.getName());
        plainTextEmail.setText(currUser.getEmail());
        plaintextMobileNum.setText(currUser.getMobile());

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserController uc = new UserController();
              //  uc.updateUserEmail(String.valueOf(plainTextEmail.getText()));
                uc.updateUserMobile(String.valueOf(plaintextMobileNum.getText()));
                uc.updateUserName(String.valueOf(plainTextFullName.getText()));


            }
        });


        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChangePassword.class));


            }
        });


    }
}