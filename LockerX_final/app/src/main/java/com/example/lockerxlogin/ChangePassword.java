package com.example.lockerxlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class ChangePassword extends AppCompatActivity {

    Button btnConfirmChangePw;
    EditText plainTextOldPassword, plainTextNewPassword, plainTextNewConfirmPassword;
    FirebaseAuth fAuth;
    FirebaseUser fUser;
    String confirmedPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        fAuth = FirebaseAuth.getInstance();
        plainTextOldPassword = findViewById(R.id.plainTextOldPassword);
        plainTextNewPassword = findViewById(R.id.plainTextNewPassword);
        plainTextNewConfirmPassword = findViewById(R.id.plainTextOldPasswordCfm);
        btnConfirmChangePw = findViewById(R.id.btnConfirmChangePw);
        fUser = fAuth.getCurrentUser();

        btnConfirmChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (String.valueOf(plainTextNewConfirmPassword.getText()).equals(String.valueOf(plainTextNewPassword.getText()))) {
                    if (!checkPasswordRule(String.valueOf(plainTextNewConfirmPassword.getText())))
                        Toast.makeText(ChangePassword.this, "Password must have at least 8 character with a uppercase, a lower case, a number and a symbol.", Toast.LENGTH_SHORT).show();
                    else{
                        fAuth.signInWithEmailAndPassword(Login.currUser.getEmail(), String.valueOf(plainTextOldPassword.getText())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", "Succesfully signed in ");
                                    fUser.updatePassword(String.valueOf(plainTextNewPassword.getText())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(ChangePassword.this, "Password has been changed!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), MainFunc.class));
                                            } else {
                                                Toast.makeText(ChangePassword.this, "Error! Password not changed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                } else {
                                    Toast.makeText(ChangePassword.this, "Old password does not match!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                }

                    } else {
                        Toast.makeText(ChangePassword.this, "New password and confirm password does not match!", Toast.LENGTH_SHORT).show();
                    }

            }
        });


    }
    public boolean checkPasswordRule(String password){
        //return less 8
        if (password == null || password.length() <8 ) return false;
        Pattern upper = Pattern.compile("[a-z]");
        Pattern lower = Pattern.compile("[A-Z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        int i = 0;
        if (digit.matcher(password).find()) i++;
        if (upper.matcher(password).find())i++;
        if (lower.matcher(password).find()) i++;
        if (special.matcher(password).find()) i++;
        if (i  < 4 )  return false;
        return true;
    }


}