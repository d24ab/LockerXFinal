package com.example.lockerxlogin.fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockerxlogin.Booking;
import com.example.lockerxlogin.BookingActivity;
import com.example.lockerxlogin.BookingController;
import com.example.lockerxlogin.BookingHistoryArr;
import com.example.lockerxlogin.CancelBooking;
import com.example.lockerxlogin.DatabaseController;
import com.example.lockerxlogin.MainActivity;
import com.example.lockerxlogin.R;
import com.example.lockerxlogin.Register;
import com.example.lockerxlogin.ReturnLocker;

import org.w3c.dom.CDATASection;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookingHistoryArrAdapter extends RecyclerView.Adapter<BookingHistoryRecyclerViewHolder> {
    private final ArrayList<Booking> bookingHistoryArr;
//    private final ArrayList<String> locationList;
//    private final ArrayList<Character> sizeList;
    private final ArrayList<HashMap<String, Object>> locationSizeList;
    private String bookid;
    private LocalDate endDate;
    private LocalTime endTime;
    private long lockerid;
    private LocalDate startDate;
    private LocalTime startTime;
    private char status;
    private long structureid;
    private String mobile;
    private String location;
    private String size;
    private String totalPay;

    DatabaseController dc = new DatabaseController();
    BookingController bc = new BookingController();


    public BookingHistoryArrAdapter(ArrayList<Booking> bookingHistoryArr1, ArrayList<HashMap<String, Object>> locSizeList) {

        this.bookingHistoryArr = bookingHistoryArr1;
        this.locationSizeList = locSizeList;
//        this.locationList=locations;
//        this.sizeList=sizes;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.booking_history_item;
    }

    @NonNull
    @Override
    public BookingHistoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new BookingHistoryRecyclerViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull BookingHistoryRecyclerViewHolder holder, int position) {
        Booking bha = bookingHistoryArr.get(position);
        //holder.getBookingID().setText("Booking id: " + bha.getBookingId());
        holder.getLockerID().setText("Locker id: " + bha.getLockerID());
        holder.getEndDate().setText("End date: " + bha.getEndDate());
        holder.getEndTime().setText("End time: " + bha.getEndTime());
        holder.getMobile().setText("Mobile: " + bha.getMobile());
        holder.getStartDate().setText("Start date: " + bha.getStartDate());
        holder.getStartTime().setText("Start time: " + bha.getStartTime());
        holder.getStatus().setText("Status: " + bha.getStatus());
        holder.getStructure().setText("Structure ID: " + bha.getStructureID());

        for (HashMap<String, Object> hashMap : locationSizeList) {
            if ((int)hashMap.get("position")==position) {
                holder.getLocation().setText(("Location: " + hashMap.get("location")));
                holder.getSize().setText("Size: " + hashMap.get("size"));
            }
        }


        //bookid = bha.getBookingId();
        endDate = bha.getEndDate();
        endTime = bha.getEndTime();
        lockerid = bha.getLockerID();
        startDate = bha.getStartDate();
        startTime = bha.getStartTime();
        status = bha.getStatus();
        structureid = bha.getStructureID();
        mobile = bha.getMobile();
//        location = dc.retrieveLocationName(Long.parseLong(String.valueOf(structureid)));
//        size = Character.toString(dc.retrieveLockerSize(Long.parseLong(String.valueOf(structureid)),Long.parseLong(String.valueOf(lockerid))));
//        totalPay = Float.toString(bc.calculateRentalFees(Long.parseLong(structureid),Long.parseLong(lockerid), LocalDate.parse(startDate),
//                LocalTime.parse(startTime), LocalDate.parse(endDate), LocalTime.parse(endTime)));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = v.getContext();
                final Intent intent;
                //intent =  new Intent(mContext, Register.class);
                //TODO put if else statement to compare time here


//                if(bha.getStatus() == 'R'){//Returned locker
//                    holder.getStatus().setTextColor(Color.parseColor("#e63946"));
//                } else if (bha.getStatus() == 'B'){// future
//                    holder.getStatus().setTextColor(Color.parseColor("#2a9d8f"));
//                    showDialogForFuture(v, position);
//
//                } else if (bha.getStatus() == 'O') {
//                    holder.getStatus().setTextColor(Color.parseColor("#2a9d8f"));
//                    showDialogPresent(v, position);
//                }else if (bha.getStatus() == 'C') {
//                    holder.getStatus().setTextColor(Color.parseColor("#e63946"));
//                }
                if(bha.getStatus() == 'R'){//Returned locker
                    holder.getStatus().setTextColor(Color.parseColor("#e63946"));
                    holder.getStatus().setText("Returned");
                } else if (bha.getStatus() == 'B'){// future
                    holder.getStatus().setTextColor(Color.parseColor("#2a9d8f"));
                    holder.getStatus().setText("Booked");
                    showDialogForFuture(v, position);

                } else if (bha.getStatus() == 'O') {
                    holder.getStatus().setTextColor(Color.parseColor("#2a9d8f"));
                    holder.getStatus().setText("Ongoing");
                    showDialogPresent(v, position);
                }else if (bha.getStatus() == 'C') {
                    holder.getStatus().setTextColor(Color.parseColor("#e63946"));
                    holder.getStatus().setText("Cancelled");
                }
               //


                Log.d("TAG","The booking id before passed is " + bha.getMobile());
                //intent.putExtra("variableToPass", bha.getBookingId());
              //  mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return bookingHistoryArr.size();
    }

    public void showDialogForFuture(View view, int position){
        final androidx.appcompat.app.AlertDialog.Builder continueBookingDialog = new AlertDialog.Builder(view.getContext());
        continueBookingDialog.setTitle("What option would you like to do?");
        // resendVerificationMailDialog.setView(resendVerificationEditText);
        continueBookingDialog.setPositiveButton("Cancel Booking", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Booking currBooking = bookingHistoryArr.get(position);
                //Automatically close the dialog
                Context mContext = view.getContext();
                final Intent intent;
                intent =  new Intent(mContext, CancelBooking.class);

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//                intent.putExtra("bookid",bookid);
                intent.putExtra("endDate",currBooking.getEndDate().toString());
                intent.putExtra("endTime",currBooking.getEndTime().format(timeFormatter));
                intent.putExtra("lockerid",currBooking.getLockerID()+"");
                intent.putExtra("startDate",currBooking.getStartDate().toString());
                intent.putExtra("startTime",currBooking.getStartTime().format(timeFormatter));
                intent.putExtra("status",Character.toString(currBooking.getStatus()));
                intent.putExtra("structureid",currBooking.getStructureID()+"");
                intent.putExtra("mobile",currBooking.getMobile());
//                intent.putExtra("location",location);
//                intent.putExtra("size",size);
//                intent.putExtra("totalPay",totalPay);


                /* Mayb transfer variable
                intent.putExtra("title", marker.getTitle());
                intent.putExtra("postal",post);*/

                mContext.startActivity(intent);



            }
        });
        continueBookingDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        continueBookingDialog.show();
    }

    public void showDialogPresent(View view, int position){
        final androidx.appcompat.app.AlertDialog.Builder continueBookingDialog = new AlertDialog.Builder(view.getContext());
        continueBookingDialog.setTitle("What would you like to do?");
        // resendVerificationMailDialog.setView(resendVerificationEditText);
        continueBookingDialog.setPositiveButton("Return Locker", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Booking currBooking = bookingHistoryArr.get(position);
                //Automatically close the dialog
                Context mContext = view.getContext();
                final Intent intent;
                intent =  new Intent(mContext, ReturnLocker.class);

//                intent.putExtra("bookid",bookid);
//                intent.putExtra("endDate",endDate);
//                intent.putExtra("endTime",endTime);
//                intent.putExtra("lockerid",lockerid);
//                intent.putExtra("startDate",startDate);
//                intent.putExtra("startTime",startTime);
//                intent.putExtra("status",status);
//                intent.putExtra("structureid",structureid);
//                intent.putExtra("mobile",mobile);
//                intent.putExtra("location",location);
//                intent.putExtra("size",size);
//                intent.putExtra("totalPay",totalPay);
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                intent.putExtra("endDate",currBooking.getEndDate().toString());
                intent.putExtra("endTime",currBooking.getEndTime().format(timeFormatter));
                intent.putExtra("lockerid",currBooking.getLockerID()+"");
                intent.putExtra("startDate",currBooking.getStartDate().toString());
                intent.putExtra("startTime",currBooking.getStartTime().format(timeFormatter));
                intent.putExtra("status",Character.toString(currBooking.getStatus()));
                intent.putExtra("structureid",currBooking.getStructureID()+"");
                intent.putExtra("mobile",currBooking.getMobile());
//                intent.putExtra("location",location);
//                intent.putExtra("size",size);


                /* Mayb transfer variable
                intent.putExtra("title", marker.getTitle());
                intent.putExtra("postal",post);*/

                mContext.startActivity(intent);



            }
        });
        continueBookingDialog.setNegativeButton("Lock/Unlock", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context mContext = view.getContext();
                final Intent intent;

                intent =  new Intent(mContext, MainActivity.class);
                /* Mayb transfer variable
                intent.putExtra("title", marker.getTitle());
                intent.putExtra("postal",post);*/

                mContext.startActivity(intent);


            }
        });

        continueBookingDialog.show();
    }


}
