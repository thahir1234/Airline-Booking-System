package com.airline.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class TicketOnewayModel extends TicketModel{
    public FlightModel flight;

    public TicketOnewayModel(String bookingEmail,LocalDate dateOfBooking, FlightModel flight) {
        super(bookingEmail,dateOfBooking);
        this.flight = flight;
    }
}
