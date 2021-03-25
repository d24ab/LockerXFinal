package com.example.lockerxlogin;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.NoCopySpan;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

import static com.example.lockerxlogin.Register.isNumeric;

public class TopUpPage extends AppCompatActivity {
    Button topUp;
    EditText topUpValue;
    TextView topUpBalance, topUpAmount, topUpTitle, changeInBalance;
    Spinner paymentType;
    ImageView visaLogo, masterLogo, paylahLogo;
//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    DatabaseReference reff;
    UserController uc = new UserController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        topUpTitle = findViewById(R.id.topUpTitle);
        topUpAmount = findViewById(R.id.topUpAmount);
        topUpBalance = findViewById(R.id.topUpBalance);
        topUpValue = findViewById(R.id.topUpValue);
        changeInBalance = findViewById(R.id.changeInBalance);
        topUp = findViewById(R.id.topUp);
        paymentType = findViewById(R.id.paymentType);
        visaLogo = findViewById(R.id.visaLogo);
        masterLogo = findViewById(R.id.masterLogo);
        paylahLogo = findViewById(R.id.paylahLogo);

        //reff = FirebaseDatabase.getInstance().getReference().child("User").child("90059608");

        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TopUpPage.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.name));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentType.setAdapter(myAdapter);

        //System.out.println(paymentType.getSelectedItem().toString());
        paymentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (paymentType.getSelectedItem().toString().equals("Visa")) {
                    visaLogo.setVisibility(View.VISIBLE);
                    masterLogo.setVisibility(View.INVISIBLE);
                    paylahLogo.setVisibility(View.INVISIBLE);
                }
                if (paymentType.getSelectedItem().toString().equals("MasterCard")) {
                    visaLogo.setVisibility(View.INVISIBLE);
                    masterLogo.setVisibility(View.VISIBLE);
                    paylahLogo.setVisibility(View.INVISIBLE);
                }
                if (paymentType.getSelectedItem().toString().equals("PayLah!")) {
                    visaLogo.setVisibility(View.INVISIBLE);
                    masterLogo.setVisibility(View.INVISIBLE);
                    paylahLogo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                return;
            }
        });





//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String balance = snapshot.child("walletBalance").getValue().toString();
//                Float bal = Float.parseFloat(balance);
//                topUpBalance.setText("$ " + df.format(bal));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

        String balance = Float.toString(Login.currUser.getWalletBalance());
        Float bal = Float.parseFloat(balance);
        topUpBalance.setText("$ " + df.format(bal));

        topUpValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String firstline = "You have $";
                String secondline = "\n\nTop up amount: $";
                String thirdline = "\n\nAfter top up: $";
                Float topUp = Float.parseFloat(topUpValue.getText().toString().trim());
                double total = topUp + bal;
                changeInBalance.setText(firstline + df.format(bal) + secondline + s + thirdline + df.format(total));

//                reff.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String balance = snapshot.child("walletBalance").getValue().toString();
//                        Float bal = Float.parseFloat(balance);
//                        Float topUp = Float.parseFloat(topUpValue.getText().toString().trim());
//                        double total = topUp+bal;
//                        changeInBalance.setText(firstline + df.format(bal) + secondline + s + thirdline + df.format(total));
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                    }
//                });

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        topUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder confirmTopUpDialog = new AlertDialog.Builder(v.getContext());
                confirmTopUpDialog.setTitle("Confirm Top Up");
                confirmTopUpDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Amount = topUpValue.getText().toString().trim();

                        if (TextUtils.isEmpty(Amount)) {
                            topUpValue.setError("Please Enter an Amount");
                            return;
                        } else if (Float.parseFloat(Amount) <= 0.009) {
                            topUpValue.setError("Please enter a valid value");
                        } else {
                            Float amount = Float.parseFloat(Amount);
                            Float total = amount + bal;
                            uc.updateUserWalletBalance(total);
                            topUpBalance.setText("$ " + total.toString());
                            Toast.makeText(TopUpPage.this, "Top Up Success!", Toast.LENGTH_SHORT).show();

//                            reff.addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    Float balance = Float.parseFloat(snapshot.child("walletBalance").getValue().toString());
//                                    Float amount = Float.parseFloat(Amount);
//                                    reff.child("walletBalance").setValue((amount + balance));
//                                    Float total = amount + balance;
//                                    topUpBalance.setText("$ " + total.toString());
//                                    Toast.makeText(TopUpPage.this, "Top Up Success!", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//                                }
//
//                            });
                        }
                    }
                });
                confirmTopUpDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Automatically close the dialog
                    }
                });
                confirmTopUpDialog.show();

            }
        });
    }
}
