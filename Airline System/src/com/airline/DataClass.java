package com.airline;

import com.airline.models.CustomerModel;
import com.airline.models.FlightModel;
import com.airline.models.TicketModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class DataClass {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    private static DataClass singleObj=null;
    private String currentUserEmail;
    public ArrayList<CustomerModel> customerList = new ArrayList<>();
    public ArrayList<FlightModel> flightList = new ArrayList<>();
    public ArrayList<TicketModel> ticketList = new ArrayList<>();

    private DataClass()
    {
        FlightModel f1 = new FlightModel("Indigo","Delhi","Chennai",stringToLocalDate("08-11-2022"),"17:00","20:00",9000);
        flightList.add(f1);
        FlightModel f2 = new FlightModel("Indigo","Delhi","Chennai",stringToLocalDate("11-11-2022"),"16:00","19:00",9000);
        flightList.add(f2);
        FlightModel f3 = new FlightModel("AirIndia","Delhi","Chennai",stringToLocalDate("08-11-2022"),"12:00","15:00",8500);
        flightList.add(f3);
        FlightModel f4 = new FlightModel("Jetways","Delhi","Chennai",stringToLocalDate("08-11-2022"),"3:00","6:00",8775);
        flightList.add(f4);
        FlightModel f5 = new FlightModel("Jetways","Chennai","Delhi",stringToLocalDate("06-11-2022"),"12:00","17:00",8775);
        flightList.add(f5);
        FlightModel f6 = new FlightModel("IndianAirways","Chennai","Delhi",stringToLocalDate("12-11-2022"),"12:00","17:00",8775);
        flightList.add(f6);

    }

    public static DataClass getInstance()
    {
        if(singleObj==null)
        {
            singleObj = new DataClass();
        }
        return singleObj;
    }

    public boolean isUserRegistered(String email)
    {
        for(int i=0;i<customerList.size();i++)
        {
            if(customerList.get(i).getEmail().equals(email))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isUserAuthorized(String email,String password)
    {
        for(int i=0;i<customerList.size();i++)
        {
            if(customerList.get(i).getEmail().equals(email) && customerList.get(i).getPassword().equals(password))
            {
                currentUserEmail = email;
                return true;
            }
        }

        return false;
    }

    public static LocalDate stringToLocalDate(String stringDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatter = formatter.withLocale( Locale.ENGLISH );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse(stringDate, formatter);

        return date;
    }

    public void viewFlights()
    {
        System.out.printf("%-18s%-22s%-22s%-22s%-22s%-22s%-22s%-22s\n\n","Flight Number","Flight Name","From","To","Date","Start Time","End Time","Price");
        for (int i=0;i<flightList.size();i++)
        {
            //System.out.println(String.valueOf(flightList.get(0).getDate()));
            System.out.printf("%-18s%-22s%-22s%-22s%-22s%-22s%-22s%-22s\n",flightList.get(i).getFlightNumber(),flightList.get(i).getFlightName(),flightList.get(i).getFrom(),flightList.get(i).getTo(),flightList.get(i).getDate(),flightList.get(i).getStartTime(),flightList.get(i).getEndTime(),flightList.get(i).getPrice());
        }
    }

    public void viewFlights(String from,String to,LocalDate date)
    {

        System.out.printf("%-18s%-22s%-22s%-22s%-22s\n\n","Flight Number","Flight Name","Start Time","End Time","Price");
        for (int i=0;i<flightList.size();i++)
        {
            if(flightList.get(i).getFrom().equalsIgnoreCase(from) && flightList.get(i).getTo().equalsIgnoreCase(to) && flightList.get(i).getDate().equals(date))
            {
                System.out.printf("%-18s%-22s%-22s%-22s%-22s\n",flightList.get(i).getFlightNumber(),flightList.get(i).getFlightName(),flightList.get(i).getStartTime(),flightList.get(i).getEndTime(),flightList.get(i).getPrice());
            }
            //System.out.println(String.valueOf(flightList.get(0).getDate()));
        }
    }

    public void viewFlights(String from,String to,String fnum)
    {

        System.out.printf("%-18s%-22s%-22s%-22s%-22s%-22s\n\n","Flight Number","Flight Name","Date","Start Time","End Time","Price");
        for (int i=0;i<flightList.size();i++)
        {
            if(flightList.get(i).getFrom().equalsIgnoreCase(from) && flightList.get(i).getTo().equalsIgnoreCase(to) && flightList.get(i).getDate().isAfter(LocalDate.now()) && !flightList.get(i).getFlightNumber().equalsIgnoreCase(fnum))
            {
                System.out.printf("%-18s%-22s%-22s%-22s%-22s%-22s\n",flightList.get(i).getFlightNumber(),flightList.get(i).getFlightName(),flightList.get(i).getDate(),flightList.get(i).getStartTime(),flightList.get(i).getEndTime(),flightList.get(i).getPrice());
            }
            //System.out.println(String.valueOf(flightList.get(0).getDate()));
        }
    }

    public FlightModel getFlightById(String flightNumber)
    {
        for(int i=0;i< flightList.size();i++)
        {
            if(flightList.get(i).getFlightNumber().equalsIgnoreCase(flightNumber))
            {
                return flightList.get(i);
            }
        }
        return null;
    }

    public void viewFlightSeats(char[][] mat)
    {
        int r=6,col=20;
        for(int i=0;i<r;i++)
        {
            System.out.print((char)(65+i)+"    ");
            for(int j=0;j<col;j++)
            {
                if(j==(col/2)-1)
                {
                    if(mat[i][j]=='+') {
                        System.out.printf("%-6s\t\t\t", ANSI_GREEN + mat[i][j] + ANSI_RESET);
                    }
                    else {
                        System.out.printf("%-6s\t\t\t", ANSI_RED + mat[i][j] + ANSI_RESET);

                    }
                }
                else {
                    if(mat[i][j]=='+') {
                        System.out.printf("%-15s",ANSI_GREEN+mat[i][j]+ANSI_RESET);                    }
                    else {
                        System.out.printf("%-15s",ANSI_RED+mat[i][j]+ANSI_RESET);                    }

                    }
                //System.out.print(mat[i][j] + "    ");

            }
            if(i==(r/2)-1)
            {
                System.out.println();

                System.out.print("     ");
                for(int k = 1; k<=col; k++)
                {
                    if(k==(col/2))
                    {
                        System.out.printf("%-6d\t\t",k);
                    }
                    else {
                        System.out.printf("%-6d", k);
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Note: (+ => Available               - => Not Available ) ");
    }

    public String getCurrentUserEmail()
    {
        return currentUserEmail;
    }

    public TicketModel getTicketByPnr(String pnr)
    {
        for (int i=0;i< ticketList.size();i++)
        {
            if(ticketList.get(i).getPnr().equalsIgnoreCase(pnr))
            {
                return ticketList.get(i);
            }
        }
        return null;
    }

    public boolean isFlightAlreadyPresent(FlightModel flightModelObject)
    {
        for (FlightModel flightModel : flightList) {
            if(flightModel.equals(flightModelObject))
            {
                return true;
            }
        }
        return false;
    }
}
