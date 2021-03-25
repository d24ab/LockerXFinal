package com.example.lockerxlogin;

public class LockerController {
    private Locker locker;

    public LockerController(Locker locker){
        this.locker = locker;
    }

//    public boolean lockOrUnlock(DatabaseController dc){
//        locker.setIsLocked();
//        dc.storeLockerStatus(this.locker.getLockerID(),this.locker.getStructureID());
//        return locker.getIsLocked(); //returns true or false, false being unlocked true being locked
//    }
}
