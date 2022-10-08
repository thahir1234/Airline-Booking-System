package com.airline.funtions;

import com.airline.DataClass;
import com.airline.models.FlightModel;
import com.airline.models.TicketOnewayModel;
import com.airline.models.TicketRoundTripModel;

import java.time.LocalDate;
import java.util.Objects;

public class Admin implements AdminFunctions{
    @Override
    public void addFlight(DataClass dataClassObject) {
        System.out.println("\nEnter the details of flight to be added:\n");
        System.out.print("Flight Name :");
        String flightName = sc.nextLine();
        System.out.print("From :");
        String flightFrom = sc.nextLine();
        System.out.print("To :");
        String flightTo = sc.nextLine();
        LocalDate flightDate;
        do {
            System.out.print("Departure Date (Format : DD-MM-YYYY) :");
            String fd = sc.nextLine();

            flightDate = DataClass.stringToLocalDate(fd);
            if (!flightDate.isAfter(LocalDate.now())) {
                System.out.println("\nEnter a future date!\n");
            } else {
                break;
            }
        }while(true);
        System.out.print("Start time (Format : HH:MM ):");
        String flightStartTime = sc.nextLine();
        System.out.print("Reach time (Format : HH:MM ) :");
        String flightReachTime = sc.nextLine();
        System.out.print("Price :");
        int flightPrice  = sc.nextInt();

        FlightModel newFLight = new FlightModel(flightName,flightFrom,flightTo,flightDate,flightStartTime,flightReachTime,flightPrice);

        if(!dataClassObject.isFlightAlreadyPresent(newFLight))
        {
            dataClassObject.flightList.add(newFLight);
            dataClassObject.viewFlights();
        }
        else {
            System.out.println("Flight already present in database!\n");
        }
    }

    @Override
    public void editFlight(DataClass dataClassObject) {
        dataClassObject.viewFlights();
        FlightModel flight;
        String fnum;
        do {
            System.out.print("\nEnter 'Flight Number' (To be edited):");
            fnum = sc.nextLine();
            flight = dataClassObject.getFlightById(fnum);
            if(Objects.isNull(flight))
            {
                System.out.println("\nEnter a valid flight number!\n");
            }
            else {

                break;
            }
        }while(true);
        do {
            System.out.println("\nWhich field to be edited :");
            System.out.println("1.Flight timing");
            System.out.println("2.Flight date");
            System.out.print("Enter your choice :");
            int editChoice = sc.nextInt();
            sc.nextLine();
            if (editChoice == 1) {
                System.out.print("\nEnter a new departure time (Format HH:MM):");
                String newStartTime = sc.nextLine();
                System.out.print("Enter a new arrival time (Format HH:MM):");
                String newReachtime = sc.nextLine();
                flight.setStartTime(newStartTime);
                flight.setEndTime(newReachtime);
                updateTicket(dataClassObject,fnum,"Timings");
                break;
            } else if (editChoice == 2) {
                LocalDate newDate;
                do {
                    System.out.print("\nEnter a new date (Format: DD-MM-YYYY) :");
                    String nd = sc.nextLine();
                    newDate = DataClass.stringToLocalDate(nd);
                    if (!newDate.isAfter(LocalDate.now())) {
                        System.out.println("\nEnter a valid date!");
                    } else {
                        break;
                    }
                } while (true);
                flight.setDate(newDate);
                updateTicket(dataClassObject,fnum,"Dates");
                break;
            } else {
                System.out.println("\nEnter a valid choice!");
            }
        }while (true);
        System.out.println("\n Flight edited successfully");
    }

    @Override
    public void cancelFLight(DataClass dataClassObject) {
        dataClassObject.viewFlights();
        FlightModel flight;
        String fnum;
        do {
            System.out.print("\nEnter 'Flight Number' (To be cancelled):");
            fnum = sc.nextLine();
            flight = dataClassObject.getFlightById(fnum);
            if (Objects.isNull(flight)) {
                System.out.println("\nEnter a valid flight number!\n");
            } else {

                break;
            }
        } while (true);
        System.out.println("\nFlight Details:\n");
        System.out.println("Flight Number : " + flight.getFlightNumber());
        System.out.println("Flight Name : " + flight.getFlightName());
        System.out.println("Flight Source : " + flight.getFrom());
        System.out.println("Flight Destination : " + flight.getTo());
        System.out.println("Depature Date : " + flight.getDate());
        System.out.println("Start Time : " + flight.getStartTime());
        System.out.println("Reach Time : " + flight.getEndTime());
        System.out.println("Price : " + flight.getPrice() + "\n");
        do {
            System.out.println("\nDo you want to delete this flight (y/n) :");
            char deleteChoice = sc.nextLine().charAt(0);
            if (deleteChoice == 'y' || deleteChoice == 'Y') {
                for (int i = 0; i < dataClassObject.ticketList.size(); i++) {
                    TicketOnewayModel ticketOnewayModelObject = null;
                    TicketRoundTripModel ticketRoundTripModelObject = null;
                    if (dataClassObject.ticketList.get(i) instanceof TicketOnewayModel) {
                        ticketOnewayModelObject = (TicketOnewayModel) dataClassObject.ticketList.get(i);
                        if (ticketOnewayModelObject.flight.getFlightNumber().equalsIgnoreCase(fnum)) {
                            ticketOnewayModelObject.setStatus("Cancelled by admin(Emergency)");
                        }
                    } else if (dataClassObject.ticketList.get(i) instanceof TicketRoundTripModel) {
                        ticketRoundTripModelObject = (TicketRoundTripModel) dataClassObject.ticketList.get(i);
                        if (ticketRoundTripModelObject.goingFlight.getFlightNumber().equalsIgnoreCase(fnum) || ticketRoundTripModelObject.returnFlight.getFlightNumber().equalsIgnoreCase(fnum)) {
                            ticketRoundTripModelObject.setStatus("Cancelled by admin(Emergency)");
                        }
                    }
                    //System.out.printf("%-18s%-22s%-22s%-22s%-22s");
                }
                dataClassObject.flightList.remove(flight);

                System.out.println("\nFlight deleted successfully");
                break;
            } else if (deleteChoice == 'n' || deleteChoice == 'N') {
                System.out.println("\nProcess stopped!\n");
                break;
            } else {
                System.out.println("\nEnter a valid choice!\n");
            }

        } while (true);
    }

    static void updateTicket(DataClass dataClassObject,String fnum,String field) {
        for (int i=0;i<dataClassObject.ticketList.size();i++)
        {
            TicketOnewayModel ticketOnewayModelObject = null;
            TicketRoundTripModel ticketRoundTripModelObject = null;
            if (dataClassObject.ticketList.get(i) instanceof TicketOnewayModel) {
                ticketOnewayModelObject = (TicketOnewayModel) dataClassObject.ticketList.get(i);
                if(ticketOnewayModelObject.flight.getFlightNumber().equalsIgnoreCase(fnum))
                {
                    ticketOnewayModelObject.setStatus("Rescheduled("+field+")");
                }
            }
            else if(dataClassObject.ticketList.get(i) instanceof TicketRoundTripModel)
            {
                ticketRoundTripModelObject = (TicketRoundTripModel) dataClassObject.ticketList.get(i);
                if(ticketRoundTripModelObject.goingFlight.getFlightNumber().equalsIgnoreCase(fnum))
                {
                    ticketRoundTripModelObject.setStatus("Rescheduled(Going flight's "+field+")");
                }
                else if(ticketRoundTripModelObject.returnFlight.getFlightNumber().equalsIgnoreCase(fnum))
                {
                    ticketRoundTripModelObject.setStatus("Rescheduled(Return flight's "+field+")");
                }
            }
            //System.out.printf("%-18s%-22s%-22s%-22s%-22s");
        }
    }
}
