package com.airline.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class TicketModel {
    private String pnr;
    private LocalDate dateOfBooking;

    private String bookingEmail;
    private String status;


    public ArrayList<Passenger> passengerList = new ArrayList<>();

    public ArrayList<String> seats = new ArrayList<>();
    static Random rnd = new Random();
    public TicketModel(String bookingEmail,LocalDate dateOfBooking) {
        pnr = String.valueOf(rnd.nextInt(1,10));
        for(int i=0;i<9;i++)
        {
            pnr+=String.valueOf(rnd.nextInt(10));
        }
        //pnr = String.valueOf(rnd.nextLong(999999999)+10000);
        this.dateOfBooking = dateOfBooking;
        this.bookingEmail = bookingEmail;
    }
    public static class Passenger
    {
        String passengerName;
        String gender;
        int age;

        String ticketNumber="";

        public Passenger(String passengerName, String gender, int age) {
            ticketNumber+=(char) ('A' + rnd.nextInt(26));
            for(int i=0;i<9;i++)
            {
                ticketNumber+=String.valueOf(rnd.nextInt(10));
            }
            this.passengerName = passengerName;
            this.gender = gender;
            this.age = age;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public void setPassengerName(String passengerName) {
            this.passengerName = passengerName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getTicketNumber() {
            return ticketNumber;
        }

        public void setTicketNumber(String ticketNumber) {
            this.ticketNumber = ticketNumber;
        }
    }

    public void addPassenger(Passenger p)
    {
        passengerList.add(p);
    }

    public void addSeat(String seat)
    {
        seats.add(seat);
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public String getBookingEmail() {
        return bookingEmail;
    }

    public void setBookingEmail(String bookingEmail) {
        this.bookingEmail = bookingEmail;
    }
}
