package com.example.lockerxlogin;

import java.lang.reflect.Array;
import java.util.ArrayList;

//this class is for retrieving data and being able to use the data outside of the onDataChange() method
public class TempDataStorage {
//    private String str;
//    private long longNum;
    private String mobile;
    private ArrayList<User> userList = new ArrayList<User>();
    private User user;
    private long userCount;
    private ArrayList<LockerStructure> structureList = new ArrayList<LockerStructure>();
    private ArrayList<Locker> lockerList = new ArrayList<Locker>();
    private char lockerSize;
    private long structureID;
    private long lockerID;
    private Booking booking;
    private String locationName;
    private boolean isLocked;
    private long userBookingCount;

    private ArrayList<Booking> bBookingList = new ArrayList<Booking>();
    private ArrayList<Booking> userOBookingList = new ArrayList<Booking>();
    private ArrayList<Booking> userBBookingList = new ArrayList<Booking>();
    private ArrayList<Booking> userRBookingList = new ArrayList<Booking>();
    private ArrayList<Booking> userCBookingList = new ArrayList<Booking>();


    public TempDataStorage() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    //    public String getStr() {
//        return str;
//    }
//
//    public void setStr(String str) {
//        this.str = str;
//    }
//
//    public long getLongNum() {
//        return longNum;
//    }
//
//    public void setLongNum(long longNum) {
//        this.longNum = longNum;
//    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public void clearUserList(ArrayList<User> userList) {
        this.userList.clear();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public ArrayList<LockerStructure> getStructureList() {
        return structureList;
    }

    public void setStructureList(ArrayList<LockerStructure> structureList) {
        this.structureList = structureList;
    }

    public ArrayList<Locker> getLockerList() {
        return lockerList;
    }

    public void setLockerList(ArrayList<Locker> lockerList) {
        this.lockerList = lockerList;
    }

    public char getLockerSize() {
        return lockerSize;
    }

    public void setLockerSize(char lockerSize) {
        this.lockerSize = lockerSize;
    }

    public long getStructureID() {
        return structureID;
    }

    public void setStructureID(long structureID) {
        this.structureID = structureID;
    }

    public ArrayList<Booking> getBBookingList() {
        return bBookingList;
    }

    public void setBBookingList(ArrayList<Booking> bBookingList) {
        this.bBookingList = bBookingList;
    }

    public ArrayList<Booking> getUserOBookingList() {
        return userOBookingList;
    }

    public void setUserOBookingList(ArrayList<Booking> userOBookingList) {
        this.userOBookingList = userOBookingList;
    }

    public ArrayList<Booking> getUserBBookingList() {
        return userBBookingList;
    }

    public void setUserBBookingList(ArrayList<Booking> userBBookingList) {
        this.userBBookingList = userBBookingList;
    }

    public ArrayList<Booking> getUserRBookingList() {
        return userRBookingList;
    }

    public void setUserRBookingList(ArrayList<Booking> userRBookingList) {
        this.userRBookingList = userRBookingList;
    }

    public ArrayList<Booking> getUserCBookingList() {
        return userCBookingList;
    }

    public void setUserCBookingList(ArrayList<Booking> userCBookingList) {
        this.userCBookingList = userCBookingList;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public long getLockerID() {
        return lockerID;
    }

    public void setLockerID(long lockerID) {
        this.lockerID = lockerID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean locked) {
        isLocked = locked;
    }

    public long getUserBookingCount() {
        return userBookingCount;
    }

    public void setUserBookingCount(long userBookingCount) {
        this.userBookingCount = userBookingCount;
    }
}
