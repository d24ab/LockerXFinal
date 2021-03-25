package com.example.lockerxlogin.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lockerxlogin.EditProfile;
import com.example.lockerxlogin.Login;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.User;
import com.example.lockerxlogin.ui.accounts.AccountsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountsFragment extends Fragment  {

    private AccountsViewModel mViewModel;
    String userEmail;
    String mobileNum;
    private Handler mainHandler = new Handler();
    private volatile boolean stopThread = false;
    FirebaseAuth fAuth;
    FirebaseUser fUser;
    User user;
    String currentUsername, currentUserEmail, currentUserMobile;



    public static AccountsFragment newInstance() {
        return new AccountsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_accounts, container, false);
        AccountsFragment.RetrieveAccountThread runnable = new AccountsFragment.RetrieveAccountThread();
        new Thread(runnable).start();
        user = Login.currUser;
        Log.d("TAG", "The user account is " +user.getEmail());
        currentUsername = user.getName();
        currentUserEmail = user.getEmail();
        currentUserMobile = user.getMobile();
        TextView textViewEditProfileID = (TextView) myView.findViewById(R.id.textViewEditProfileID);
        TextView textViewEmailID = (TextView) myView.findViewById(R.id.textViewEmailID);
        TextView textViewLateFees = (TextView) myView.findViewById(R.id.textViewLateFees);
        TextView textViewMobileNumID = (TextView) myView.findViewById(R.id.textViewMobileNumID);
        TextView textViewNameID = (TextView) myView.findViewById(R.id.textViewNameID);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail();
        fAuth = FirebaseAuth.getInstance();
        textViewNameID.setText(currentUsername);
        textViewEmailID.setText("Email address: " + currentUserEmail);
        Log.d("TAG" , "The current user mobile is "  + currentUserMobile);
        textViewMobileNumID.setText("Mobile Number : " + currentUserMobile);
        textViewEditProfileID.setVisibility(View.VISIBLE);
        textViewLateFees.setText("Late fees: " + Login.currUser.getLateFees());

        textViewEditProfileID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent
                Intent intent = new Intent(getActivity(), EditProfile.class);

                getActivity().startActivity(intent);
            }
        });




//        String title = getArguments().getString("title");
     //   Toast.makeText(getActivity(),title +" BRUH.",Toast.LENGTH_SHORT)
             //   .show();

        //textViewMobileNumID.setText(user.getPhoneNumber());
        //String markerLocation = getActivity().getIntent().getStringExtra("selectedLocationString");
        //Toast.makeText(getActivity(),markerLocation,Toast.LENGTH_SHORT).show();



        return myView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountsViewModel.class);
        // TODO: Use the ViewModel
    }
    class RetrieveAccountThread implements Runnable{
        RetrieveAccountThread(){
            //
        }

        @Override
        public void run() {
            Log.d("TAG", "Adding progress bar");
            //textViewForProgressBar.setVisibility(View.VISIBLE);
            String name, email, mobile;







            for (int i = 0; i < 10000; i++) {


//

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }







            }
            stopThread = true;
            if (stopThread){
                //dbProgressBar.setVisibility(View.GONE);
                Log.d("TAG", "Stopping thread");

                return;}

        }

    }


    public void startThread(View view){
        stopThread = false;
        AccountsFragment.RetrieveAccountThread runnable = new AccountsFragment.RetrieveAccountThread();
        new Thread(runnable).start();

    }
    public void stopThread(View view){
        stopThread = true;
    }



}