package com.example.lockerxlogin;
import java.util.ArrayList;

public class Locker extends LockerStructure {
    private long lockerID;
    private char size;  //'S'=small, 'M'=medium, 'L'=large
    private boolean isLocked; // False = Unlocked, True = Locked
    //private ArrayList<Booking> booking;

    public Locker(){} //empty constructor

    public Locker(long structureID, int locationID, String location, String postalCode, String address, int lockerID, char size){
        super.structureID = structureID;
//        super.locationID = locationID;
//        super.location = location;
        super.postalCode = postalCode;
        super.address = address;
        this.lockerID = lockerID;
        this.size = size;
        //this.booking = new ArrayList<Booking>();
        this.isLocked = false; //unlocked
    }

    public void setLockerID(long lockerID){
        this.lockerID = lockerID;
    }
    public long getLockerID(){
        return this.lockerID;
    }

    public void setSize(char size){
        this.size = size;
    }
    public char getSize(){
        return this.size;
    }

//    public ArrayList<Booking> getBooking() {
//        return booking;
//    }


    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        isLocked = isLocked;
    }
}
