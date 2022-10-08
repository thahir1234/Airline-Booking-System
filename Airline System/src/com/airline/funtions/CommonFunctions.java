package com.airline.funtions;

import com.airline.DataClass;
import com.airline.models.TicketModel;
import com.airline.models.TicketOnewayModel;
import com.airline.models.TicketRoundTripModel;

import java.util.Objects;
import java.util.Scanner;

public interface CommonFunctions {
    Scanner sc = new Scanner(System.in);
    default boolean login(DataClass dataClassObject)
    {
        System.out.print("Enter email:");
        String email = sc.nextLine();
        System.out.print("Enter password:");
        String password = sc.nextLine();
        if(this instanceof Customer)
        {

            if(dataClassObject.isUserAuthorized(email,password))
            {
                System.out.println("\nLogin successful");
                return true;
            }
            else
            {
                System.out.println("\nLogin failed!");
                return false;
            }
        }
        else {

            if(email.equals("admin123@gmail.com") && password.equals("admin123"))
            {
                System.out.println("\nLogin successful");
                return true;
            }
            else
            {

                System.out.println("\nLogin failed!");
                return false;
            }
        }
    }
    default void viewTickets(DataClass dataClassObject)
    {
        System.out.println("\n**All Tickets**\n");
        System.out.printf("%-20s%-23s%-23s%-23s%-23s%-23s%-23s\n","S.No","PNR","Source","Destination","Date of Booking","Ticket Type","Status");
        int index=1;

        for (int i=0;i<dataClassObject.ticketList.size();i++)
        {
            TicketOnewayModel ticketOnewayModelObject = null;
            TicketRoundTripModel ticketRoundTripModelObject = null;
            if (dataClassObject.ticketList.get(i) instanceof TicketOnewayModel) {
                ticketOnewayModelObject = (TicketOnewayModel) dataClassObject.ticketList.get(i);
                System.out.printf("%-20d%-23s%-23s%-23s%-23s%-23s%-23s\n",index,ticketOnewayModelObject.getPnr(),ticketOnewayModelObject.flight.getFrom(),ticketOnewayModelObject.flight.getTo(),ticketOnewayModelObject.getDateOfBooking().toString(),"One-way",ticketOnewayModelObject.getStatus());

            }
            else if(dataClassObject.ticketList.get(i) instanceof TicketRoundTripModel)
            {
                ticketRoundTripModelObject = (TicketRoundTripModel) dataClassObject.ticketList.get(i);
                System.out.printf("%-20d%-23s%-23s%-23s%-23s%-23s%-23s\n",index,ticketRoundTripModelObject.getPnr(),ticketRoundTripModelObject.goingFlight.getFrom(),ticketRoundTripModelObject.goingFlight.getTo(),ticketRoundTripModelObject.getDateOfBooking().toString(),"Round Trip",ticketRoundTripModelObject.getStatus());
            }
            //System.out.printf("%-18s%-22s%-22s%-22s%-22s");
        }
        TicketModel ticketModelObject= Subfunctions.getPnrFromUser(dataClassObject,"Viewed");

        Subfunctions.displayTicket(ticketModelObject);
    }
    default void viewTickets(DataClass dataClassObject,String email)
    {
        System.out.println("\n**Your Tickets**\n");
        System.out.printf("%-20s%-23s%-23s%-23s%-23s%-23s%-23s\n","S.No","PNR","Source","Destination","Date of Booking","Ticket Type","Status");
        int index=1;

        for (int i=0;i<dataClassObject.ticketList.size();i++)
        {
            if(dataClassObject.ticketList.get(i).getBookingEmail().equalsIgnoreCase(email)) {
                TicketOnewayModel ticketOnewayModelObject = null;
                TicketRoundTripModel ticketRoundTripModelObject = null;
                if (dataClassObject.ticketList.get(i) instanceof TicketOnewayModel) {
                    ticketOnewayModelObject = (TicketOnewayModel) dataClassObject.ticketList.get(i);
                    System.out.printf("%-20d%-23s%-23s%-23s%-23s%-23s%-23s\n",index,ticketOnewayModelObject.getPnr(),ticketOnewayModelObject.flight.getFrom(),ticketOnewayModelObject.flight.getTo(),ticketOnewayModelObject.getDateOfBooking().toString(),"One-way",ticketOnewayModelObject.getStatus());

                }
                else if(dataClassObject.ticketList.get(i) instanceof TicketRoundTripModel)
                {
                    ticketRoundTripModelObject = (TicketRoundTripModel) dataClassObject.ticketList.get(i);
                    System.out.printf("%-20d%-23s%-23s%-23s%-23s%-23s%-23s\n",index,ticketRoundTripModelObject.getPnr(),ticketRoundTripModelObject.goingFlight.getFrom(),ticketRoundTripModelObject.goingFlight.getTo(),ticketRoundTripModelObject.getDateOfBooking().toString(),"Round Trip",ticketRoundTripModelObject.getStatus());
                }
                //System.out.printf("%-18s%-22s%-22s%-22s%-22s");
            }
        }
        TicketModel ticketModelObject = Subfunctions.getPnrFromUser(dataClassObject,"viewed");

        Subfunctions.displayTicket(ticketModelObject);
    }


}
