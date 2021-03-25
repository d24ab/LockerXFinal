package com.example.lockerxlogin;

public class BookingHistoryArr {
    private String bookingId, endDate, endTime, lockerID, mobile, startDate, startTime, status, structureID;

    public BookingHistoryArr(String bookingId, String endDate, String endTime, String lockerID, String mobile, String startDate, String startTime, String status, String structureID) {
        this.bookingId = bookingId;
        this.endDate = endDate;
        this.endTime = endTime;
        this.lockerID = lockerID;
        this.mobile = mobile;
        this.startDate = startDate;
        this.startTime = startTime;
        this.status = status;
        this.structureID = structureID;
    }
    
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLockerID() {
        return lockerID;
    }

    public void setLockerID(String lockerID) {
        this.lockerID = lockerID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStructureID() {
        return structureID;
    }

    public void setStructureID(String structureID) {
        this.structureID = structureID;
    }





}
