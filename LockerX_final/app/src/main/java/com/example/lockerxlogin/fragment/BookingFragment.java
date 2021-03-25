package com.example.lockerxlogin.fragment;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lockerxlogin.R;

import java.text.SimpleDateFormat;
import java.util.*;
import com.example.lockerxlogin.ui.BookingViewModel;

import android.widget.Button;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.Toast;

import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BookingFragment extends Fragment implements View.OnClickListener ,DatePickerDialog.OnDateSetListener{

    private BookingViewModel mViewModel;
   // DatePickerDialog.OnDateSetListener
    private DatePickerDialog datePickerDialog;
    int mYear;
    int mMonth;
    int mDay;

    TextView BselectedDate, BselectedTime, BcurrentLocation;
    Spinner BspinnerSize;
    Button BstartBtn, BendBtn, BdateBtn, BliveBtn, BsearchBtn;
    DatePicker BdatePick;
    FirebaseAuth fAuth;
    FirebaseUser FBuser;




    private BookingViewModel bViewModel;


    public static BookingFragment newInstance() {
        return new BookingFragment();
    }

//    @Override
//    public Dialog OnCreateDialog(Bundle savedInstanceState){
//        final Calendar calendar = Calendar.getInstance();
//        int yy = calendar.get(Calendar.YEAR);
//        int mm = calendar.get(Calendar.MONTH);
//        int dd = calendar.get(Calendar.DAY_OF_MONTH);
//        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
//    }
//
//    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
//        populateSetDate(yy, mm+1, dd);
//    }
//    public void populateSetDate(int year, int month, int day) {
//        BselectedDate.setText(month+"/"+day+"/"+year);
//    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        BstartBtn = (Button)view.findViewById(R.id.sBtn);
        BendBtn = (Button)view.findViewById(R.id.endBtn);
        BdateBtn = view.findViewById(R.id.dateBtnStart);
        BliveBtn = view.findViewById(R.id.liveBtn);
        BsearchBtn = view.findViewById(R.id.searchBtn);
        BselectedDate = view.findViewById(R.id.selectedDateStart);

        BstartBtn.setOnClickListener(this);
        BendBtn.setOnClickListener(this);
        BdateBtn.setOnClickListener(this);
        BliveBtn.setOnClickListener(this);
        BsearchBtn.setOnClickListener(this);

        //get from intent
        String title = getArguments().getString("title");
        Toast.makeText(getActivity(),title +" BRUH.",Toast.LENGTH_SHORT)
                .show();

        return view;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
//        // TODO: Use the ViewModel
//    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.dateBtnStart:
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                showDatePickerDialog();

        }
    }

    private void showDatePickerDialog() {

//        DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//               int mYear = year;
//                int mMonth = month;
//                int mDay = dayOfMonth;
//                updateDisplay();
//                DatePickerDialog d = new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getParentFragment(),mYear,mMonth,mDay);
//                d.show();
//            }
//        };
        DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                updateDisplay();
            }
        };

        DatePickerDialog d = new DatePickerDialog(getActivity(),
                 mDateSetListener, mYear, mMonth, mDay);
        d.show();

        }
        private void updateDisplay(){
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

            BselectedDate.setText(sdf.format(c.getTime()));

            sdf = new SimpleDateFormat("yyyy-MM-dd");

           // transDateString=sdf.format(c.getTime());
        }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        BselectedDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
    }
}