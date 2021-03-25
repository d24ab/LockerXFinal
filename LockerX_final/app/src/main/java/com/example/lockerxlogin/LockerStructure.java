package com.example.lockerxlogin;

public class LockerStructure {
    protected long structureID;
//    protected int locationID;
    protected String postalCode;
    protected String address;
    protected String locationName;

    public LockerStructure(){} //empty constructor

    public LockerStructure(long structureID, String postalCode, String address, String locationName){
        this.structureID = structureID;
        this.postalCode = postalCode;
        this.address = address;
        this.locationName = locationName;
    }
    public void setStructureID(long structureID){
        this.structureID = structureID;
    }
    public long getStructureID(){
        return this.structureID;
    }

//    public void setLocationID(int lockerID){
//        this.locationID = locationID;
//    }
//    public int getLocationID(){
//        return this.locationID;
//    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
