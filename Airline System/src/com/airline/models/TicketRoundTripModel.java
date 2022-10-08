package com.airline.models;

import java.time.LocalDate;

public class TicketRoundTripModel extends TicketModel{

    public FlightModel goingFlight;
    public FlightModel returnFlight;

    public TicketRoundTripModel(String bookingEmail,LocalDate dateOfBooking, FlightModel goingFLight, FlightModel returnFlight) {
        super(bookingEmail,dateOfBooking);
        this.goingFlight = goingFLight;
        this.returnFlight = returnFlight;
    }
}
