package com.airline.models;

import java.time.LocalDate;
import java.util.Random;

public class FlightModel {
    private final String flightNumber;
    private String flightName;
    private String from;
    private String to;
    private LocalDate date;
    private String startTime;
    private String endTime;
    private int price;

    public final SeatModel Seats;

    Random rnd = new Random();
    public FlightModel(String flightName, String from, String to, LocalDate date, String startTime, String endTime, int price) {
        this.Seats = new SeatModel();
        this.flightName = flightName;
        this.from = from;
        this.to = to;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.flightNumber = String.valueOf((char) ('A' + rnd.nextInt(26)))+ (char) ('A' + rnd.nextInt(26)) + (rnd.nextInt(900) + 100);
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this)
        {
            return true;
        }
        if(!(obj instanceof FlightModel))
        {
            return false;
        }

        FlightModel newFlight = (FlightModel) obj;
        if(newFlight.getFlightName().equalsIgnoreCase(this.flightNumber) && newFlight.getFrom().equalsIgnoreCase(this.from) && newFlight.getTo().equalsIgnoreCase(this.to) && newFlight.getDate().isEqual(this.date) && newFlight.getStartTime().equalsIgnoreCase(this.startTime) && newFlight.getEndTime().equalsIgnoreCase(this.endTime))
        {
            return true;
        }
        return false;
    }

    public String getFlightNumber() {
        return flightNumber;
    }


    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
