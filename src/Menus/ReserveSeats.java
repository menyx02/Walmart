package Menus;

import Schedulers.Scheduler;
import Utils.Tools;
import Venues.Venue;
import org.w3c.dom.events.EventException;

import java.util.Scanner;

public class ReserveSeats extends AMenu{

    public ReserveSeats(int optionNumber, Scheduler scheduler) {
        super(optionNumber, scheduler);
        super.optionDescription = "Reserve seats";
    }


    public void doMethod() {
        int numberTicketsWanted = 0;
        String userName = "";
        Scanner sc = new Scanner(System.in);
        try {

            System.out.println("\nWelcome to our scheduler. We will get you the best tickets available");
            System.out.println("What name should we make the confirmation to:");
            userName = sc.nextLine();

            System.out.println("Thank you " + userName + ". Please indicate the number of tickets that you want:");
            numberTicketsWanted = sc.nextInt();

            if(numberTicketsWanted < 1) throw new Exception();

            scheduler.processRequest(userName, numberTicketsWanted);

        }
        catch (Exception e) {
            Tools.printErrorMessage("Sorry, that is not a valid option. You will be sent back to the main menu\n\n");
            return;
        }

    }
}
