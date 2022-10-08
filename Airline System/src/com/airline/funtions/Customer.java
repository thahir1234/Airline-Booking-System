package com.airline.funtions;

import com.airline.DataClass;
import com.airline.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Customer implements CustomerFunctions{
    static Scanner sc = new Scanner(System.in);
    @Override
    public void register(DataClass dataClassObject) {
        System.out.print("Enter your name:");
        String name = sc.nextLine();
        System.out.print("Enter your email:");
        String email = sc.nextLine();
        System.out.print("Enter your password:");
        String password = sc.next();
        sc.nextLine();
        if(!dataClassObject.isUserRegistered(email))
        {
            CustomerModel customerDetails = new CustomerModel(name,email,password);
            dataClassObject.customerList.add(customerDetails);
            System.out.println("\nRegistered successfully");
        }
        else
        {
            System.out.println("\nThis email has been already registered!");
        }

    }
    @Override
    public void bookFlight(DataClass dataClassObject) {

        System.out.println("\nSelect a booking type:");
        System.out.println("1.One-way");
        System.out.println("2.Round trip");
        System.out.print("Enter a choice:");
        int bookingChoice = sc.nextInt();
        sc.nextLine();
        System.out.println();
        if(bookingChoice==1)
        {
            Subfunctions.bookOnewayTicket(dataClassObject);
            //System.out.println(from+" "+to+" "+date+" "+passengerCount);
        }
        else if(bookingChoice==2)
        {
            Subfunctions.bookRoundTripTicket(dataClassObject);
        }
        else {
            System.out.println("Enter a valid choice!");
            bookFlight(dataClassObject);
        }
    }
    @Override
    public void cancelFlight(DataClass dataClassObject,String currentEmail) {
        Subfunctions.viewBookedTicket(dataClassObject,currentEmail);
        TicketModel ticketModelObject;
        ticketModelObject = Subfunctions.getPnrFromUser(dataClassObject,"cancelled");

        Subfunctions.displayTicket(ticketModelObject);
        System.out.print("\nDo you want to cancel this ticket(y/n):");
        char cancelChoice = sc.nextLine().charAt(0);
        if(cancelChoice=='y' || cancelChoice=='Y')
        {
            if(!ticketModelObject.getStatus().equalsIgnoreCase("Cancelled")) {
                TicketOnewayModel ticketOnewayModelObject = null;
                TicketRoundTripModel ticketRoundTripModelObject = null;
                System.out.println("\nYour ticket (PNR:"+ticketModelObject.getPnr()+") has been cancelled successfully.");
                ticketModelObject.setStatus("Cancelled");
                if(ticketModelObject instanceof TicketOnewayModel) {
                    ticketOnewayModelObject = (TicketOnewayModel) ticketModelObject;
                    Subfunctions.retainBookedSeatsOneway(ticketOnewayModelObject);
                }
                else {
                    ticketRoundTripModelObject = (TicketRoundTripModel) ticketModelObject;
                    Subfunctions.retainBookedSeatsRoundtrip(ticketRoundTripModelObject);
                }
            }
            else
            {
                System.out.println("Ticket has been already cancelled!\n");
            }
        }
        System.out.println();
    }
    @Override
    public void rescheduleFlight(DataClass dataClassObject,String currentEmail) {
        Subfunctions.viewBookedTicket(dataClassObject,currentEmail);
        TicketModel ticketModelObject;
        String alreadyFlight;
        ticketModelObject = Subfunctions.getPnrFromUser(dataClassObject,"rescheduled");
        if(Objects.isNull(ticketModelObject))
        {
            return;
        }
        Subfunctions.displayTicket(ticketModelObject);
        System.out.print("\nDo you want to reschedule this ticket (y/n) :");
        char rescheduleChoice = sc.nextLine().charAt(0);
        if(rescheduleChoice=='y' || rescheduleChoice=='Y')
        {
            if(ticketModelObject instanceof TicketOnewayModel)
            {
                Subfunctions.rescheduleOnewayTicket(dataClassObject,ticketModelObject);
            }
            else {
                Subfunctions.rescheduleRoundtripTicket(dataClassObject,ticketModelObject);
            }
        }
    }


}
