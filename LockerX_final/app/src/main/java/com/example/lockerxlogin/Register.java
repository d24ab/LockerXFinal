package com.example.lockerxlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText mEmail, mPassword, mName, mMobile;
    TextView mLoginBtn,alreadyRegisterButton;
    Button mRegisterBtn;
    ProgressBar mprogressBar;
    FirebaseAuth fAuth;
//    DatabaseReference reff;
    DatabaseController dc = new DatabaseController();

    //number
    public static final String REG_NUMBER = ".\\d+.";
    //lowercase
    public static final String REG_UPPERCASE = ".[A-Z]+.";
    //uppercase
    public static final String REG_LOWERCASE = ".[a-z]+.";
    //special character
    public static final String REG_SYMBOL = ".[~!@#$%^&()_+|<>,.?/:;'\\[\\]{}\"]+.*";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //String variableToReceive = getIntent().getStringExtra("variableToPass");
        //Log.d("TAG", " THE STGRINZG PASSSED IS " +variableToReceive);

        mName = findViewById(R.id.RName);
        mEmail = findViewById(R.id.REamil);
        mPassword = findViewById(R.id.RPassword);
        mMobile = findViewById(R.id.RPhone);
        //mLoginBtn = findViewById(R.id.LoginFromRegisterBtn);
        alreadyRegisterButton = findViewById(R.id.alreadyRegisterText);
        mprogressBar = findViewById(R.id.LprogressBar);
        fAuth = FirebaseAuth.getInstance();
        mRegisterBtn = findViewById(R.id.RRegisterBtn);
//      reff = FirebaseDatabase.getInstance().getReference().child("User"); //reference to the "User" table of the db

        /*if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/
        alreadyRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }

        });


        mRegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String smobile = mMobile.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }
                if(!checkPasswordRule(password)){
                    mPassword.setError("Password must have at least 8 character with a uppercase, a lower case, a number and a symbol");
                    return;
                }

                if(TextUtils.isEmpty(smobile)){
                    mMobile.setError("Phone is required");
                    return;
                }

                if(smobile.length()!=8){
                    mMobile.setError("Please enter 8 digits only");
                }

                if (!isNumeric(smobile)) {
                    mMobile.setError("Please enter only digits");
                }

                mprogressBar.setVisibility(View.GONE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){


                            FirebaseUser FBuser = fAuth.getCurrentUser();
                            FBuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid){
                                    Toast.makeText(Register.this, "Verification email has been sent!", Toast.LENGTH_SHORT).show();
                                    mprogressBar.setVisibility(View.GONE);//
                                }

                            }).addOnFailureListener(new OnFailureListener(){
                                @Override
                                public void onFailure(@NonNull Exception e){
                                    Log.d("TAG", "OnFailure: Email not sent " +e.getMessage());
                                    mprogressBar.setVisibility(View.GONE);
                                }

                            });

//                            User user = new User(name, email, mobile, 0);
//                            reff.child(smobile).setValue(user); //store new user to db
                            dc.storeNewUser(name, email, smobile);
                            Toast.makeText(Register.this, "User created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }
                        else{
                            Toast.makeText(Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            long num = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}