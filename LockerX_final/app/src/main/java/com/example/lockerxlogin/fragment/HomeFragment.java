package com.example.lockerxlogin.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Handler;
import android.se.omapi.SEService;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lockerxlogin.Account;
import com.example.lockerxlogin.Booking;
import com.example.lockerxlogin.BookingActivity;
import com.example.lockerxlogin.Login;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.ui.home.HomeViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import android.widget.Toast;
public class HomeFragment extends Fragment  {
    //implements SEService.OnConnectedListener {
//0
    private Handler mHandler = new Handler();
    private HomeViewModel mViewModel;
    private GoogleMap mMap;
    private String post;
    private FusedLocationProviderClient client;
    public final LatLng defaultLocation = new LatLng(1.3521, 103.8198);
    public final LatLng Woodlands = new LatLng(1.439874, 103.779376);
    public final LatLng Jurong = new LatLng(1.339874,103.706464);
    public final LatLng USS = new LatLng(1.256752, 103.820331);
    public TextView textViewForProgressBar;
    public ProgressBar DBprogressBar;



    // System.out.println(latLng.latitude);


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {





        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SupportMapFragment mMapFragment;

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);


        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        defaultLocation, 10
                ));
                //Toast.
                Toast.makeText(getActivity(), "Click on a marker to make a booking", Toast.LENGTH_LONG).show();
                MarkerOptions woodlandsMarker = new MarkerOptions();
                MarkerOptions jurongMarker = new MarkerOptions();
                MarkerOptions ussMarker = new MarkerOptions();

                woodlandsMarker.position(Woodlands);
                jurongMarker.position(Jurong);
                ussMarker.position(USS);

                googleMap.addMarker(woodlandsMarker
                        .title("Woodlands")
                        .snippet("Postal Code: 738600"));
                googleMap.addMarker(jurongMarker
                        .title("Jurong")
                        .snippet("Postal Code: 648886"));
                googleMap.addMarker(ussMarker
                        .title("USS")
                        .snippet("Postal Code: 098269"));

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if(marker != null && marker.getTitle().equals("Woodlands")){
                            post = "738600";
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    marker.getPosition(), 15
                            ));
                            Toast.makeText(getActivity(),"Woodlands is selected",Toast.LENGTH_SHORT)
                                    .show();
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    //Fake method to delay time
                                    final androidx.appcompat.app.AlertDialog.Builder continueBookingDialog = new AlertDialog.Builder(view.getContext());
                                    continueBookingDialog.setTitle("Book locker for " + marker.getTitle() + " " + marker.getSnippet() +"?");
                                    // resendVerificationMailDialog.setView(resendVerificationEditText);
                                    continueBookingDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Intent intent = new Intent(getActivity(), BookingActivity.class);
                                            intent.putExtra("title", marker.getTitle());
                                            intent.putExtra("postal",post);
                                            Toast.makeText(getActivity(),marker.getTitle() +" is selected.",Toast.LENGTH_SHORT)
                                                    .show();
                                            getActivity().startActivity(intent);
                                        }
                                    });
                                    continueBookingDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Automatically close the dialog

                                        }
                                    });
                                    continueBookingDialog.show();

                                }
                            },  1600);


                         /*   AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                            LayoutInflater factory = LayoutInflater.from(view.getContext());
                            final View view = factory.inflate(R.layout., null);
                            alertDialog.setView(view);
                            alertDialog.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alertDialog.show();*/


                        }
                        else if(marker != null && marker.getTitle().equals("Jurong")){
                            post = "648886";
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    marker.getPosition(), 15
                            ));

                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    //Fake method to delay time
                                    final androidx.appcompat.app.AlertDialog.Builder continueBookingDialog = new AlertDialog.Builder(view.getContext());
                                    continueBookingDialog.setTitle("Book locker for " + marker.getTitle() + " " + marker.getSnippet() +"?");
                                    // resendVerificationMailDialog.setView(resendVerificationEditText);
                                    continueBookingDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                            //Pass marker.getTitle();
//                                            AccountsFragment af = new AccountsFragment();
//                                            Bundle args = new Bundle();
//                                            args.putString("title", marker.getTitle());
//                                            af.setArguments(args);
//                                            Toast.makeText(getActivity(),marker.getTitle() +" is selected.",Toast.LENGTH_SHORT)
//                                                    .show();
//
//                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, af).commit();
                                            Intent intent = new Intent(getActivity(), BookingActivity.class);
                                            intent.putExtra("title", marker.getTitle());
                                            intent.putExtra("postal",post);

                                            Toast.makeText(getActivity(),marker.getTitle() +" is selected.",Toast.LENGTH_SHORT)
                                                    .show();
                                            getActivity().startActivity(intent);
                                            //getChildFragmentManager().beginTransaction().add(R.id.fragment_container, af).commit();
                                            //  getActivity().startActivity(intent);
                                            // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountsFragment(),null).commit();
                                            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountsFragment(),null).commit();
                                        }
                                    });
                                    continueBookingDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Automatically close the dialog

                                        }
                                    });
                                    continueBookingDialog.show();

                                }
                            },  1600);
                        }
                        else if(marker != null && marker.getTitle().equals("USS")){
                            post = "098269";
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    marker.getPosition(), 15
                            ));
                            Toast.makeText(getActivity(),"USS is selected",Toast.LENGTH_SHORT)
                                    .show();
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    //Fake method to delay time
                                    final androidx.appcompat.app.AlertDialog.Builder continueBookingDialog = new AlertDialog.Builder(view.getContext());
                                    continueBookingDialog.setTitle("Book locker for " + marker.getTitle() + " " + marker.getSnippet() +"?");
                                    // resendVerificationMailDialog.setView(resendVerificationEditText);
                                    continueBookingDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                            //Pass marker.getTitle();
//                                            AccountsFragment af = new AccountsFragment();
//                                            Bundle args = new Bundle();
//                                            args.putString("title", marker.getTitle());
//                                            af.setArguments(args);
//                                            Toast.makeText(getActivity(),marker.getTitle() +" is selected.",Toast.LENGTH_SHORT)
//                                                    .show();
//
//                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, af).commit();
                                            Intent intent = new Intent(getActivity(), BookingActivity.class);
                                            intent.putExtra("title", marker.getTitle());
                                            intent.putExtra("postal",post);
                                            Toast.makeText(getActivity(),marker.getTitle() +" is selected.",Toast.LENGTH_SHORT)
                                                    .show();
                                            getActivity().startActivity(intent);
                                        }
                                    });
                                    continueBookingDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Automatically close the dialog

                                        }
                                    });
                                    continueBookingDialog.show();

                                }
                            },  1600);
                        }

                        return true;
                    }
                });

                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        if(marker != null && marker.getTitle().equals("Woodlands")){
                            //Toast.makeText(getActivity(),"Woodlands is selected",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //When clicked on map
                        //Initialise marker option
                        MarkerOptions markerOptions = new MarkerOptions();

                        //Set position of market


                        //markerOptions.position(latLng);
                        markerOptions.position(defaultLocation);
                        //set title marker
                        markerOptions.title(defaultLocation.latitude + ": " + defaultLocation.longitude);

                        //removeall markers
                        // googleMap.clear();
                        //Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                defaultLocation, 10
                        ));
                        //Add marker on map
                        googleMap.addMarker(markerOptions);



                    }
                });


            }



        });





        return view;
    }
  /*  public void progressBarSetVisible(){
        textViewForProgressBar.setVisibility(View.VISIBLE);
        DBprogressBar.setVisibility(View.VISIBLE);
    }

    public void progressBarSetGone(){
        textViewForProgressBar.setVisibility(View.GONE);
        DBprogressBar.setVisibility(View.GONE);

    }*/




    /*@Override
    public void onConnected() {
        FusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            Log.i("MainActivity ", "" + location.getLongitude())
                        }
                    }
                });
        FusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        FusedLocationClient.requestLocationUpdates(requestLocation(),
                new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        for (Location location : locationResult.getLocations()) {
                            Log.i("MainActivity ", "" + location.getLongitude());
                            //not getting current location updates every 2 minutes
                        }
                    }

                    ;

                }, null);
    }
*/


   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }*/

}