package com.example.lockerxlogin.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.lockerxlogin.Booking;
import com.example.lockerxlogin.BookingController;
import com.example.lockerxlogin.BookingHistoryArr;
import com.example.lockerxlogin.DatabaseController;
import com.example.lockerxlogin.Login;
import com.example.lockerxlogin.MainActivity;
import com.example.lockerxlogin.MainFunc;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.User;
import com.example.lockerxlogin.UserController;
import com.example.lockerxlogin.ui.lockers.LockersViewModel;
import com.google.android.gms.common.util.ArrayUtils;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class LockersFragment extends Fragment implements View.OnClickListener {

    Button LockerModeBtn;
    private ProgressBar myLockerProgressBar;



    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Handler mainHandler = new Handler();
    private volatile boolean stopThread = false;
    ArrayList<Booking> bookingHistoryArr = new ArrayList<Booking>();
    HashMap<String, Object> bookingHistoryMap = new HashMap<>();
//    private User user;
//    private String currUserMobile;
    private long userBookingCount;
    ArrayList<String> locationList = new ArrayList<String>();
    ArrayList<Character> sizeList = new ArrayList<Character>();
    ArrayList<HashMap<String, Object>> locationSizeList = new ArrayList<>();



    private LockersViewModel mViewModel;

    public static LockersFragment newInstance() {
        return new LockersFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




        View myView = inflater.inflate(R.layout.fragment_lockers, container, false);

        myLockerProgressBar = myView.findViewById(R.id.myLockerProgressBar);
        myLockerProgressBar.setVisibility(View.VISIBLE);

        //Start multi thread here.
        LockersFragment.RetrieveBookingThread runnable = new LockersFragment.RetrieveBookingThread();
//        user = Login.currUser;
//        currUserMobile = Login.currUser.getMobile();
        new Thread(runnable).start();


        /*ArrayList<BookingHistoryArr> bookingHistoryArr = new ArrayList<BookingHistoryArr>();
        bookingHistoryArr.add(new BookingHistoryArr("1","2021-02-17",
                "16:00:00","3","91237777", "2021-02-17",
                "13:00:00","R", "1"));
        bookingHistoryArr.add(new BookingHistoryArr("2","2021-03-02",
                "16:00:00","2","91237777", "2021-03-02",
                "14:00:00","R", "2"));
        bookingHistoryArr.add(new BookingHistoryArr("3","2021-02-19",
                "13:00:00","1","90059608", "2021-02-19",
                "12:00:00","R", "1"));
        bookingHistoryArr.add(new BookingHistoryArr("4","2021-04-21",
                "19:00:00","3","90059608", "2021-04-21",
                "13:00:00","B", "2"));*/


        mRecyclerView = myView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
      //  mRecyclerView.setHasFixedSize(true);

       // mAdapter = new ExampleAdapter(exampleList);
       // mRecyclerView.setLayoutManager(mLayoutManager);
       // mRecyclerView.setAdapter(mAdapter);

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        //mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rvBooking);
//        LockerModeBtn = myView.findViewById(R.id.LLockerMode);
//        LockerModeBtn.setOnClickListener(this);
//        lockerArray.add("testLockerID");

//        mLayoutManager = new LinearLayoutManager(getActivity());
      //  mAdapter = new MainAdapter(lockerArray);
      //  mRecyclerView.setLayoutManager(mLayoutManager);
     //   mRecyclerView.setAdapter(mAdapter);
        return myView;
    }
    //
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LLockerMode:
                this.startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    class RetrieveBookingThread implements Runnable{
        RetrieveBookingThread(){
            //
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
            Log.d("TAG", "Adding progress bar");

            //textViewForProgressBar.setVisibility(View.VISIBLE);



            for (int i = 0; i < 100000000; i++) {
                DatabaseController dc = new DatabaseController();
                UserController uc = new UserController();
                //bookingHistoryArr = uc.getUserLockers("90000001");
//                bookingHistoryArr = uc.getUserLockers(Login.currUser.getMobile());
                ArrayList<Booking> userOBookings = new ArrayList<Booking>();
                ArrayList<Booking> userFBookings = new ArrayList<Booking>();
                Log.d("IN THREAD OF LOCKERS FRAGMENT", "before dbcalls user mobile = " +Login.currUser.getMobile());
                ArrayList<Booking> userBBookings = dc.retrieveBBookingsForUser(Login.currUser.getMobile());
                ArrayList<Booking> userRBookings = dc.retrieveRBookingsForUser(Login.currUser.getMobile());
                ArrayList<Booking> userCBookings = dc.retrieveCBookingsForUser(Login.currUser.getMobile());
                userBookingCount = dc.retrieveUserBookingCount(Login.currUser.getMobile());
//

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (userBookingCount!=-1) {
                    if (userBBookings.size() + userRBookings.size() + userCBookings.size() == userBookingCount) {
                        LocalDate currDate = java.time.LocalDate.now();
                        LocalTime currTime = java.time.LocalTime.now();

                        //filter B bookings into ongoing (O) and future (F) bookings
                        for (Booking booking : userBBookings) {
                            if ((currDate.compareTo(booking.getStartDate())<0) || (currDate.compareTo(booking.getEndDate())>0)) {
                                userFBookings.add(booking);
                            } else if ((currDate.compareTo(booking.getStartDate())==0) && (currDate.compareTo(booking.getEndDate())==0)) {
                                if ((currTime.compareTo(booking.getStartTime())<0) || (currTime.compareTo(booking.getEndTime())>0)) {
                                    userFBookings.add(booking);
                                }
                            } else {
                                booking.setStatus('O');
                                userOBookings.add(booking);
                            }
                        }


                        //sort all the sub-lists using startDate, then startTime
                        Comparator<Booking> bookingComparator = Comparator.comparing(Booking::getStartDate).thenComparing(Booking::getStartTime);
                        Collections.sort(userOBookings, bookingComparator);
                        Collections.sort(userFBookings, bookingComparator);
                        Collections.sort(userRBookings, bookingComparator);
                        Collections.sort(userCBookings, bookingComparator);


                        ArrayList<Booking> userBookings = new ArrayList<Booking>();
                        //combine all 4 lists into userBookings list
                        userBookings.addAll(userOBookings);
                        userBookings.addAll(userFBookings);
                        userBookings.addAll(userRBookings);
                        userBookings.addAll(userCBookings);

                        bookingHistoryArr = userBookings;

                        //getting locations and locker sizes of the bookings
                        for (Booking booking : userBookings) {
                            //another thread to get location and size
                            LockersFragment.RetrieveLocAndSize anotherRunnable = new LockersFragment.RetrieveLocAndSize(userBookings.indexOf(booking), booking.getStructureID(), booking.getLockerID());
                            if (locationSizeList.size()!=userBookingCount) {
                                new Thread(anotherRunnable).start();
                            }
                        }


                        if(locationSizeList.size()==userBookingCount) {
                            stopThread = true;
                            if (stopThread) {
                                //dbProgressBar.setVisibility(View.GONE);
                                Log.d("FIRST THREAD", "Stopping thread");
                                Log.d("FIRST THREAD", bookingHistoryArr.get(0).getMobile());
                                getActivity().runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        Log.d("FIRST THREAD", "Setting the adapter now");

                                        mRecyclerView.setAdapter(new BookingHistoryArrAdapter(bookingHistoryArr, locationSizeList));
                                        myLockerProgressBar.setVisibility(View.GONE);
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
//                Log.d("FIRST THREAD", "i value is " +i );



            }



        }

    }

    class RetrieveLocAndSize implements Runnable {
        private int position;
        private long structureID;
        private long lockerID;

        RetrieveLocAndSize(int position, long structureID, long lockerID) {
            this.position=position;
            this.structureID=structureID;
            this.lockerID=lockerID;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
//            Log.d("SECOND THREAD", "Adding progress bar");

            //textViewForProgressBar.setVisibility(View.VISIBLE);



            for (int i = 0; i < 100000000; i++) {
                DatabaseController dc = new DatabaseController();

                String location = dc.retrieveLocationName(this.structureID);
                char size = dc.retrieveLockerSize(this.structureID, this.lockerID);

                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (size!='a' && !location.equals("error")) {

                    HashMap<String, Object> locSize = new HashMap<>();
                    locSize.put("position", position);
                    locSize.put("location", location);
                    locSize.put("size", size);
                    if (!locationSizeList.contains(locSize)) {
                        locationSizeList.add(locSize);
                    }

                    stopThread = true;
                    if (stopThread) {
                        //dbProgressBar.setVisibility(View.GONE);
                        Log.d("SECOND THREAD", "Stopping thread");
                        return;
                    }

                }
                //Log.d("SECOND THREAD", "i value is " +i );



            }



        }
    }


    public void startThread(View view){
        stopThread = false;
        LockersFragment.RetrieveBookingThread runnable = new LockersFragment.RetrieveBookingThread();
        new Thread(runnable).start();

    }
    public void stopThread(View view){
        stopThread = true;
    }
}




