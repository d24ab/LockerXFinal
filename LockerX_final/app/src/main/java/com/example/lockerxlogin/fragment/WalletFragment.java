package com.example.lockerxlogin.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lockerxlogin.Login;
import com.example.lockerxlogin.MainActivity;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.TopUpPage;
import com.example.lockerxlogin.ui.wallet.WalletViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class WalletFragment extends Fragment implements View.OnClickListener{
    TextView walletBalance;
    Button topUpButton;
    private WalletViewModel mViewModel;

    public static WalletFragment newInstance() {
        return new WalletFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_wallet, container, false);
        walletBalance = myView.findViewById(R.id.walletBalance);
        topUpButton = myView.findViewById(R.id.topUpButton);
        topUpButton.setOnClickListener(this);

        Float bal = Login.currUser.getWalletBalance();
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        walletBalance.setText("$" + df.format(bal).toString());

//        reff = FirebaseDatabase.getInstance().getReference().child("User").child("90059608");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String balance = snapshot.child("walletBalance").getValue().toString();
//                Float bal = Float.parseFloat(balance);
//                DecimalFormat df = new DecimalFormat("0.00");
//                df.setMaximumFractionDigits(2);
//                walletBalance.setText("$" + df.format(bal).toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        return myView;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topUpButton:
                this.startActivity(new Intent(getActivity(), TopUpPage.class));
                break;
        }

    }


   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        // TODO: Use the ViewModel
    }*/

}