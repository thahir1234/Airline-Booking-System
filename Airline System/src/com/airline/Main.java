package com.airline;

import com.airline.funtions.Admin;
import com.airline.funtions.AdminFunctions;
import com.airline.funtions.Customer;
import com.airline.funtions.CustomerFunctions;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static CustomerFunctions customerObject = new Customer();
    static AdminFunctions adminObject = new Admin();
    static DataClass dataClassObject = DataClass.getInstance();
    public static void main(String[] args) {
        int mainChoice;

        do {
            System.out.println("\n***** Welcome to Online Flight Booking *****");
            System.out.println("** Main Menu **");
            System.out.println("1.Register as a customer");
            System.out.println("2.Login as a customer");
            System.out.println("3.Login as a admin");
            System.out.println("4.Exit");
            System.out.println("Enter your choice:");
            mainChoice = sc.nextInt();

            System.out.println();
            switch (mainChoice)
            {
                case 1:
                    customerObject.register(dataClassObject);
                    break;
                case 2:
                    if(customerObject.login(dataClassObject))
                    {
                        customerMenu();
                    }
                    break;
                case 3:
                    if(adminObject.login(dataClassObject))
                    {
                        adminMenu();
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid choice!");

            }
        }while(true);
    }

    static void customerMenu()
    {
        int customerChoice;
        do {
            System.out.println("\n** Customer Menu **");
            System.out.println("1.Book Flight");
            System.out.println("2.View Tickets");
            System.out.println("3.Cancel Flight");
            System.out.println("4.Reschedule Flight");
            System.out.println("5.Logout");
            System.out.println("Enter your choice:");
            customerChoice = sc.nextInt();
            switch (customerChoice)
            {
                case 1:
                    customerObject.bookFlight(dataClassObject);
                    break;
                case 2:
                    customerObject.viewTickets(dataClassObject,dataClassObject.getCurrentUserEmail());
                    break;
                case 3:
                    customerObject.cancelFlight(dataClassObject,dataClassObject.getCurrentUserEmail());
                    break;
                case 4:
                    customerObject.rescheduleFlight(dataClassObject,dataClassObject.getCurrentUserEmail());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter a valid choice!");

            }
        }while(true);
    }

    static void adminMenu()
    {
        int customerChoice;
        do {
            System.out.println("\n** Admin Menu **");
            System.out.println("1.Add Flight");
            System.out.println("2.Edit Flight");
            System.out.println("3.Delete Flight");
            System.out.println("4.View all Tickets");
            System.out.println("5.Logout");
            System.out.println("Enter your choice:");
            customerChoice = sc.nextInt();
            switch (customerChoice)
            {
                case 1:
                    adminObject.addFlight(dataClassObject);
                    break;
                case 2:
                    adminObject.editFlight(dataClassObject);
                    break;
                case 3:
                    adminObject.cancelFLight(dataClassObject);
                    break;
                case 4:
                    adminObject.viewTickets(dataClassObject);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter a valid choice!");

            }
        }while(true);
    }
}
