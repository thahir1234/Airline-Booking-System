package com.airline.funtions;


import com.airline.DataClass;

public interface CustomerFunctions extends CommonFunctions{
    void register(DataClass dataClassObject);
    void bookFlight(DataClass dataClassObject);
    void cancelFlight(DataClass dataClassObject,String currentEmail);
    void rescheduleFlight(DataClass dataClassObject,String currentEmail);
}
