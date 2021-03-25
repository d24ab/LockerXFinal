package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.lockerxlogin.fragment.AccountsFragment;
import com.example.lockerxlogin.fragment.HomeFragment;
import com.example.lockerxlogin.fragment.LockersFragment;
import com.example.lockerxlogin.fragment.WalletFragment;
import com.example.lockerxlogin.fragment.BookingFragment;
import com.google.android.gms.maps.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.*;
import android.widget.Toast;

public class MainFunc extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseUser FBuser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        user = Login.currUser;
//        Log.d("TAG", "name is on home fragment" + user.toString());

        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.booking_fragment);
        BookingFragment bookingFragment = new BookingFragment();
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }

        int id = getIntent().getIntExtra("id", 0);
        if (id == 101) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,new LockersFragment())
                    .addToBackStack(null)
                    .commit();
        }
        else if(id ==202){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,new LockersFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_lockers:
                            selectedFragment = new LockersFragment();
                            break;
                        case R.id.navigation_wallet:
                            selectedFragment = new WalletFragment();
                            break;

                        case R.id.navigation_account:
                            selectedFragment = new AccountsFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

}