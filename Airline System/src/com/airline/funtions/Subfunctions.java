package com.airline.funtions;

import com.airline.DataClass;
import com.airline.models.FlightModel;
import com.airline.models.TicketModel;
import com.airline.models.TicketOnewayModel;
import com.airline.models.TicketRoundTripModel;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Subfunctions {
    static Scanner sc = new Scanner(System.in);
    static void displayTicket(TicketModel ticketModelObject)
    {

        if(ticketModelObject instanceof TicketOnewayModel)
        {
            displayOnewayTicket(ticketModelObject);
        }
        else if(ticketModelObject instanceof TicketRoundTripModel)
        {
            displayRoundtripTicket(ticketModelObject);
        }
    }

    static void viewBookedTicket(DataClass dataClassObject, String currentEmail)
    {
        System.out.println("\n**Booked Flights**\n");
        System.out.printf("%-20s%-23s%-23s%-23s%-23s%-23s%-23s\n","S.No","PNR","Source","Destination","Date of Booking","Ticket Type","Status");
        int index=1;

        for (int i=0;i<dataClassObject.ticketList.size();i++)
        {
            if(dataClassObject.ticketList.get(i).getBookingEmail().equalsIgnoreCase(currentEmail) && !dataClassObject.ticketList.get(i).getStatus().equalsIgnoreCase("Cancelled") && !dataClassObject.ticketList.get(i).getStatus().equalsIgnoreCase("Cancelled by admin(Emergency)")) {
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
    }

    //display ticket sub functions
    static void displayOnewayTicket(TicketModel ticketModelObject)
    {
        TicketOnewayModel ticketOnewayModelObject = null;

        ticketOnewayModelObject = (TicketOnewayModel) ticketModelObject;
        System.out.printf("\n%76s","TICKET");
        System.out.printf("\n%-50s%80s\n\n","Ticket (One-way)","PNR : "+ticketOnewayModelObject.getPnr());
        System.out.println("Flight:-\n");
        System.out.printf("%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s\n","Flight Number","Flight Name","From","To","Date","Start Time","End Time","Price","Status");
        System.out.printf("%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s\n\n",ticketOnewayModelObject.flight.getFlightNumber(),ticketOnewayModelObject.flight.getFlightName(),ticketOnewayModelObject.flight.getFrom(),ticketOnewayModelObject.flight.getTo(),ticketOnewayModelObject.flight.getDate(),ticketOnewayModelObject.flight.getStartTime(),ticketOnewayModelObject.flight.getEndTime(),ticketOnewayModelObject.flight.getPrice(),ticketOnewayModelObject.getStatus());

        System.out.println("Passengers:-\n");

        System.out.printf("%-20s%23s%23s%23s\n","Passenger Name","Gender","Age","Ticket Number");

        for (int i=0;i<ticketOnewayModelObject.passengerList.size();i++)
        {
            System.out.printf("%-20s%23s%23d%23s\n",ticketOnewayModelObject.passengerList.get(i).getPassengerName(),ticketOnewayModelObject.passengerList.get(i).getGender(),ticketOnewayModelObject.passengerList.get(i).getAge(),ticketOnewayModelObject.passengerList.get(i).getTicketNumber());
        }
        String allSeats = "";
        for(int i=0;i<ticketOnewayModelObject.seats.size();i++)
        {
            if(i==ticketOnewayModelObject.seats.size()-1)
            {
                allSeats += ticketOnewayModelObject.seats.get(i);
            }
            else {
                allSeats += ticketOnewayModelObject.seats.get(i) + ",";
            }

        }
        System.out.printf("\n%-50s%80s\n","Seats : "+allSeats,"Total Price : "+ ticketOnewayModelObject.flight.getPrice());
    }

    static void displayRoundtripTicket(TicketModel ticketModelObject)
    {
        TicketRoundTripModel ticketRoundTripModelObject = null;

        ticketRoundTripModelObject = (TicketRoundTripModel) ticketModelObject;
        System.out.printf("\n%76s","TICKET");
        System.out.printf("\n%-50s%80s\n\n","Ticket (Round Trip)","PNR : "+ticketRoundTripModelObject.getPnr());
        System.out.println("Flights:-\n");
        System.out.printf("Going Flight:(%s--->%s)\n",ticketRoundTripModelObject.goingFlight.getFrom(),ticketRoundTripModelObject.goingFlight.getTo());
        System.out.printf("%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s\n","Flight Number","Flight Name","From","To","Date","Start Time","End Time","Price","Status");
        System.out.printf("%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s\n\n",ticketRoundTripModelObject.goingFlight.getFlightNumber(),ticketRoundTripModelObject.goingFlight.getFlightName(),ticketRoundTripModelObject.goingFlight.getFrom(),ticketRoundTripModelObject.goingFlight.getTo(),ticketRoundTripModelObject.goingFlight.getDate(),ticketRoundTripModelObject.goingFlight.getStartTime(),ticketRoundTripModelObject.goingFlight.getEndTime(),ticketRoundTripModelObject.goingFlight.getPrice(),ticketRoundTripModelObject.getStatus());

        System.out.printf("Return Flight:(%s--->%s)\n",ticketRoundTripModelObject.returnFlight.getFrom(),ticketRoundTripModelObject.returnFlight.getTo());
        System.out.printf("%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s\n","Flight Number","Flight Name","From","To","Date","Start Time","End Time","Price","Status");
        System.out.printf("%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s%-18s\n\n",ticketRoundTripModelObject.returnFlight.getFlightNumber(),ticketRoundTripModelObject.returnFlight.getFlightName(),ticketRoundTripModelObject.returnFlight.getFrom(),ticketRoundTripModelObject.returnFlight.getTo(),ticketRoundTripModelObject.returnFlight.getDate(),ticketRoundTripModelObject.returnFlight.getStartTime(),ticketRoundTripModelObject.returnFlight.getEndTime(),ticketRoundTripModelObject.returnFlight.getPrice(),ticketRoundTripModelObject.getStatus());

        System.out.println("Passengers:-\n");

        System.out.printf("%-20s%23s%23s%23s\n","Passenger Name","Gender","Age","Ticket Number");

        for (int i=0;i<ticketRoundTripModelObject.passengerList.size();i++)
        {
            System.out.printf("%-20s%23s%23d%23s\n",ticketRoundTripModelObject.passengerList.get(i).getPassengerName(),ticketRoundTripModelObject.passengerList.get(i).getGender(),ticketRoundTripModelObject.passengerList.get(i).getAge(),ticketRoundTripModelObject.passengerList.get(i).getTicketNumber());
        }
        String allSeatsGoingFlight = "";
        String allSeatsReturnFlight = "";
        for(int i=0;i<ticketRoundTripModelObject.seats.size();i++)
        {
            if(i<ticketRoundTripModelObject.seats.size()/2)
            {
                if(i==(ticketRoundTripModelObject.seats.size()/2)-1) {
                    allSeatsGoingFlight += ticketRoundTripModelObject.seats.get(i);
                }
                else {
                    allSeatsGoingFlight += ticketRoundTripModelObject.seats.get(i)+",";
                }
            }
            else {
                if(i==ticketRoundTripModelObject.seats.size()-1)
                {
                    allSeatsReturnFlight += ticketRoundTripModelObject.seats.get(i);
                }
                else {
                    allSeatsReturnFlight += ticketRoundTripModelObject.seats.get(i)+",";
                }
            }

        }
        System.out.printf("\n%-50s\n","Seats(Going Flight) : "+allSeatsGoingFlight);
        System.out.printf("\n%-50s%80s\n","Seats(Return Flight) : "+allSeatsReturnFlight,"Total Price : "+ (ticketRoundTripModelObject.goingFlight.getPrice()+ticketRoundTripModelObject.returnFlight.getPrice()));

    }

    //book ticket sub functions
    static void bookOnewayTicket(DataClass dataClassObject)
    {
        TicketModel ticketObject;
        System.out.print("From:");
        String from = sc.nextLine();
        System.out.print("To:");
        String to = sc.nextLine();
        System.out.print("Depature Date (Ex: 13-07-2023):");
        String date  = sc.nextLine();
        System.out.print("Passenger Count:");
        int passengerCount = sc.nextInt();
        sc.nextLine();
        LocalDate dateLocalDate = DataClass.stringToLocalDate(date);

        FlightModel flight = selectFlight(dataClassObject,from,to,dateLocalDate);

        if(getConfirmation()) {
            ticketObject = new TicketOnewayModel(dataClassObject.getCurrentUserEmail(),LocalDate.now(),flight);
        }
        else {
            return;
        }
        getPassengerDetails(passengerCount,ticketObject);
        System.out.println("\nSeat selection:\n");
        dataClassObject.viewFlightSeats(flight.Seats.mat);
        System.out.println("\n");

        seatSelection(dataClassObject,flight,passengerCount,ticketObject);
        ticketObject.setStatus("Booked");
        dataClassObject.ticketList.add(ticketObject);
        System.out.println("Booked Successfully😃");
    }
    static void bookRoundTripTicket(DataClass dataClassObject)
    {
        TicketModel ticketObject;
        System.out.print("From:");
        String from = sc.nextLine();
        System.out.print("To:");
        String to = sc.nextLine();
        System.out.print("Depature Date (Ex: 13-07-2022):");
        String depdate  = sc.nextLine();
        System.out.print("Return Date (Ex: 03-08-2022):");
        String retdate = sc.nextLine();
        System.out.print("Passenger Count:");
        int passengerCount = sc.nextInt();
        sc.nextLine();

        LocalDate depatureDate = DataClass.stringToLocalDate(depdate);
        LocalDate returnDate = DataClass.stringToLocalDate(retdate);


        FlightModel goingFlight = selectFlight(dataClassObject,from,to,depatureDate);

        FlightModel returnFlight = selectFlight(dataClassObject,to,from,returnDate);

        if(getConfirmation()) {
            ticketObject = new TicketRoundTripModel(dataClassObject.getCurrentUserEmail(), LocalDate.now(), goingFlight, returnFlight);
        }
        else {
            return;
        }
        getPassengerDetails(passengerCount,ticketObject);

        System.out.println("\nSeat selection for Going Flight:\n");
        System.out.println("From: "+from+"\t\t\tTo: "+to+"\t\t\tDate: "+depdate);
        dataClassObject.viewFlightSeats(goingFlight.Seats.mat);
        System.out.println("\n");

        seatSelection(dataClassObject,goingFlight,passengerCount,ticketObject);

        System.out.println("\nSeat selection for Returning Flight:\n");
        System.out.println("From: "+to+"\t\t\tTo: "+from+"\t\t\tDate: "+retdate);
        dataClassObject.viewFlightSeats(returnFlight.Seats.mat);
        System.out.println("\n");

        seatSelection(dataClassObject,returnFlight,passengerCount,ticketObject);
        ticketObject.setStatus("Booked");
        dataClassObject.ticketList.add(ticketObject);
        System.out.println("Booked Successfully😃");
    }
    static FlightModel selectFlight(DataClass dataClassObject,String from,String to,LocalDate dateLocalDate)
    {
        System.out.println("\nFLight available:");
        System.out.println("From: "+from+"\t\t\tTo: "+to+"\t\t\tDate: "+dateLocalDate+"\n");
        dataClassObject.viewFlights(from,to,dateLocalDate);

        FlightModel flight;
        do {
            System.out.print("\nEnter 'Flight Number' (To be booked):");
            String fnum = sc.nextLine();
            flight = dataClassObject.getFlightById(fnum);
            if(Objects.isNull(flight))
            {
                System.out.println("\nEnter a valid flight number!\n");
            }
            else {

                break;
            }
        }while(true);

        System.out.println("\nFlight Details:\n");
        System.out.println("Flight Number : "+ flight.getFlightNumber());
        System.out.println("Flight Name : "+flight.getFlightName());
        System.out.println("Flight Source : "+flight.getFrom());
        System.out.println("Flight Destination : "+flight.getTo());
        System.out.println("Depature Date : "+flight.getDate());
        System.out.println("Start Time : "+flight.getStartTime());
        System.out.println("Reach Time : "+flight.getEndTime());
        System.out.println("Price : "+flight.getPrice()+"\n");

        return flight;
    }
    static void getPassengerDetails(int passengerCount,TicketModel ticketObject)
    {
        System.out.println("Enter Passenger Details:");
        for(int i=1;i<=passengerCount;i++)
        {
            System.out.println("Passenger "+i+" :");
            System.out.println("Enter name :");
            String passengerName = sc.nextLine();
            String passengerGender;
            do {
                System.out.println("Enter gender(M/F) :");
                char passengerGenderChar = sc.nextLine().charAt(0);
                if(passengerGenderChar=='m' || passengerGenderChar=='M')
                {
                    passengerGender = "Male";
                    break;
                }
                else if (passengerGenderChar=='f' || passengerGenderChar=='F') {
                    passengerGender = "Female";
                    break;
                }
                else {
                    System.out.println("Enter a valid gender!");
                }
            }while (true);
            System.out.println("Enter age :");
            int passengerAge = sc.nextInt();
            sc.nextLine();
            TicketModel.Passenger passenger = new TicketModel.Passenger(passengerName,passengerGender,passengerAge);

            ticketObject.addPassenger(passenger);
        }
    }
    static boolean getConfirmation()
    {
        System.out.println("Are you sure (y - continue /n - exit booking) :");
        char confirmationChoice = sc.nextLine().charAt(0);
        if(confirmationChoice == 'n' || confirmationChoice == 'N')
        {
            System.out.println("\nBooking failed!\n");
            return false;
        }
        else if(confirmationChoice == 'y' || confirmationChoice == 'Y')
        {
            return true;
        }
        else {
            getConfirmation();
        }

        return false;
    }
    static void seatSelection(DataClass dataClassObject,FlightModel flight,int passengerCount,TicketModel ticketObject)
    {
        for(int i=0;i<passengerCount;i++)
        {
            System.out.println("Enter seat No. (Ex:A03 or A11) :");
            String seat = sc.nextLine();
            int indi = (int)seat.charAt(0)-65;
            int indj = Integer.parseInt(String.valueOf(seat.charAt(1))+ seat.charAt(2))-1;
            flight.Seats.mat[indi][indj] = '-';
            ticketObject.addSeat(seat);
        }
    }

    //cancell ticket sub functions
    static TicketModel getPnrFromUser(DataClass dataClassObject,String stmt)
    {
        String pnr;

        System.out.printf("\nEnter PNR (To be %s) (type \"nil\" - exit):",stmt);
        pnr = sc.nextLine();
        if(pnr.trim().equalsIgnoreCase("nil")) {
            System.out.println("Process stopped!");
            return null;
        }
        TicketModel ticketModelObject = dataClassObject.getTicketByPnr(pnr);
        if(Objects.isNull(ticketModelObject))
        {
            System.out.println("\nEnter a valid PNR number!");
            getPnrFromUser(dataClassObject,stmt);
        }
        else {
            return ticketModelObject;
        }
        return ticketModelObject;
    }

    static FlightModel getFlightNumberToBeRescheduled(DataClass dataClassObject)
    {
        FlightModel flight;
        do {
            System.out.print("\nEnter 'Flight Number' (To be rescheduled):");
            String fnum = sc.nextLine();
            flight = dataClassObject.getFlightById(fnum);
            if(Objects.isNull(flight))
            {
                System.out.println("\nEnter a valid flight number!\n");
            }
            else {

                break;
            }
        }while(true);
        return flight;
    }

    static void retainBookedSeatsOneway(TicketOnewayModel ticketOnewayModelObject)
    {
        for (String seat : ticketOnewayModelObject.seats) {
            int indi = (int)seat.charAt(0)-65;
            int indj = Integer.parseInt(String.valueOf(seat.charAt(1))+ seat.charAt(2))-1;
            ticketOnewayModelObject.flight.Seats.mat[indi][indj] = '+';
            //ticketOnewayModelObject.seats.remove(seat);
        }
    }

    static void retainBookedSeatsRoundtrip(TicketRoundTripModel ticketRoundTripModelObject)
    {
        for (int i=0;i<ticketRoundTripModelObject.seats.size();i++) {
            if(i<ticketRoundTripModelObject.seats.size()/2) {
                String seat = ticketRoundTripModelObject.seats.get(i);
                int indi = (int) seat.charAt(0) - 65;
                int indj = Integer.parseInt(String.valueOf(seat.charAt(1)) + seat.charAt(2)) - 1;
                ticketRoundTripModelObject.goingFlight.Seats.mat[indi][indj] = '+';
            }
            else
            {
                String seat = ticketRoundTripModelObject.seats.get(i);
                int indi = (int) seat.charAt(0) - 65;
                int indj = Integer.parseInt(String.valueOf(seat.charAt(1)) + seat.charAt(2)) - 1;
                ticketRoundTripModelObject.returnFlight.Seats.mat[indi][indj] = '+';
            }
            //ticketOnewayModelObject.seats.remove(seat);
        }
    }

    //reschedule sub functions

    static void rescheduleOnewayTicket(DataClass dataClassObject,TicketModel ticketModelObject)
    {
        TicketOnewayModel ticketOnewayModelObject = null;


        ticketOnewayModelObject = (TicketOnewayModel) ticketModelObject;
        String alreadyFlight=ticketOnewayModelObject.flight.getFlightNumber();
        System.out.println("\nFlights available for rescheduling:");
        System.out.printf("%s --------> %s\n\n",ticketOnewayModelObject.flight.getFrom(),ticketOnewayModelObject.flight.getTo());

        dataClassObject.viewFlights(ticketOnewayModelObject.flight.getFrom(),ticketOnewayModelObject.flight.getTo(),ticketOnewayModelObject.flight.getFlightNumber());
        //System.out.println("Alternate Flight number :");
        FlightModel flight = getFlightNumberToBeRescheduled(dataClassObject);

        retainBookedSeatsOneway(ticketOnewayModelObject);
        ticketOnewayModelObject.seats.clear();
        ticketOnewayModelObject.flight=flight;

        System.out.println("\nSeat selection for rescheduled flight:\n");
        dataClassObject.viewFlightSeats(ticketOnewayModelObject.flight.Seats.mat);
        System.out.println("\n");
        seatSelection(dataClassObject,ticketOnewayModelObject.flight,ticketOnewayModelObject.passengerList.size(),ticketOnewayModelObject);

        ticketOnewayModelObject.setStatus("Rescheduled("+alreadyFlight+"-->"+flight.getFlightNumber()+")");
        System.out.println("Rescheduled Successfully😃");
    }

    static void rescheduleRoundtripTicket(DataClass dataClassObject,TicketModel ticketModelObject)
    {
        TicketRoundTripModel ticketRoundTripModelObject = null;

        ticketRoundTripModelObject = (TicketRoundTripModel) ticketModelObject;
        retainBookedSeatsRoundtrip(ticketRoundTripModelObject);
        ticketRoundTripModelObject.seats.clear();

        System.out.println("\nFlights available for rescheduling:");
        System.out.printf("Going flight : %s --------> %s\n\n",ticketRoundTripModelObject.goingFlight.getFrom(),ticketRoundTripModelObject.goingFlight.getTo());
        dataClassObject.viewFlights(ticketRoundTripModelObject.goingFlight.getFrom(),ticketRoundTripModelObject.goingFlight.getTo(),ticketRoundTripModelObject.goingFlight.getFlightNumber());
        //System.out.println("Alternate Flight number :");

        FlightModel flight = getFlightNumberToBeRescheduled(dataClassObject);

        ticketRoundTripModelObject.goingFlight=flight;
        System.out.println("\nSeat selection for rescheduled going flight:\n");
        dataClassObject.viewFlightSeats(ticketRoundTripModelObject.goingFlight.Seats.mat);
        System.out.println("\n");

        seatSelection(dataClassObject,ticketRoundTripModelObject.goingFlight,ticketRoundTripModelObject.passengerList.size(),ticketRoundTripModelObject);


        System.out.println("\nFlights available for rescheduling:");
        System.out.printf("Return flight : %s --------> %s\n\n",ticketRoundTripModelObject.returnFlight.getFrom(),ticketRoundTripModelObject.returnFlight.getTo());
        dataClassObject.viewFlights(ticketRoundTripModelObject.returnFlight.getFrom(),ticketRoundTripModelObject.returnFlight.getTo(),ticketRoundTripModelObject.returnFlight.getFlightNumber());
        //System.out.println("Alternate Flight number :");
        FlightModel returnFlight = getFlightNumberToBeRescheduled(dataClassObject);


        ticketRoundTripModelObject.returnFlight=returnFlight;
        System.out.println("\nSeat selection for rescheduled return flight:\n");
        dataClassObject.viewFlightSeats(ticketRoundTripModelObject.returnFlight.Seats.mat);
        System.out.println("\n");
        seatSelection(dataClassObject,ticketRoundTripModelObject.returnFlight,ticketRoundTripModelObject.passengerList.size(),ticketRoundTripModelObject);

        ticketRoundTripModelObject.setStatus("Rescheduled");
        System.out.println("Rescheduled Successfully😃");
    }
}
