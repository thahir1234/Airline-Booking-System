package com.airline.funtions;

import com.airline.DataClass;

public interface AdminFunctions extends CommonFunctions{
    void addFlight(DataClass dataClassObject);
    void editFlight(DataClass dataClassObject);
    void cancelFLight(DataClass dataClassObject);

}
