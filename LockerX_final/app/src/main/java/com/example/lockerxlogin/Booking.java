package com.example.lockerxlogin;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private java.time.LocalDate startDate;
    private java.time.LocalTime startTime;
    private java.time.LocalDate endDate;
    private java.time.LocalTime endTime;
    private String mobile;
    private long structureID;
    private long lockerID;
    private char status;    //'B'=booked/ongoing, 'R'=returned, 'C'=cancelled

    public Booking(){} //empty constructor

    public Booking(LocalDate startDate, LocalTime startTime,
                   LocalDate endDate, LocalTime endTime, String mobile,
                   long structureID, long lockerID, char status){
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.mobile = mobile;
        this.structureID = structureID;
        this.lockerID = lockerID;
        this.status = status;
    }

    public void setStartDate(java.time.LocalDate startDate){
        this.startDate = startDate;
    }
    public java.time.LocalDate getStartDate(){
        return this.startDate;
    }

    public void setStartTime(java.time.LocalTime startTime){
        this.startTime = startTime;
    }
    public java.time.LocalTime getStartTime(){
        return this.startTime;
    }

    public void setEndDate(java.time.LocalDate endDate){
        this.endDate = endDate;
    }
    public java.time.LocalDate getEndDate(){
        return this.endDate;
    }

    public void setEndTime(java.time.LocalTime endTime){
        this.endTime = endTime;
    }
    public java.time.LocalTime getEndTime(){
        return this.endTime;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){ return this.mobile; }

    public void setStructureID(long lockerStructureID){
        this.structureID = lockerStructureID;
    }
    public long getStructureID(){
        return this.structureID;
    }

    public void setLockerID(long lockerID){
        this.lockerID = lockerID;
    }
    public long getLockerID(){
        return this.lockerID;
    }

    public void setStatus(char status){
        this.status = status;
    }
    public char getStatus(){
        return this.status;
    }
}
