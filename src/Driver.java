import Menus.*;
import Schedulers.Scheduler;
import Utils.Tools;
import Venues.Venue;
import Venues.VenueA;

import java.util.ArrayList;
import java.util.Scanner;


public class Driver {



    public static void main(String[] args) {
        //Initialize Venue
        VenueA venue = new Venue(5, 5);

        //Initialize Scheduler
        Scheduler scheduler = new Scheduler(venue);
        //TODO-potentially make the scheduler a singleton

        //Initialize Menu
        AMenu printVenue = new PrintVenue(1, scheduler);
        AMenu findNumberSeats = new FindNumberSeats(2, scheduler);
        AMenu reserveSeats = new ReserveSeats(3, scheduler);
        AMenu lookUpReservations = new LookUpReservations(4, scheduler);
        AMenu exit = new Exit(5, scheduler);

        ArrayList<AMenu> allMenus = new ArrayList<AMenu>();
        allMenus.add(printVenue);
        allMenus.add(findNumberSeats);
        allMenus.add(reserveSeats);
        allMenus.add(lookUpReservations);
        allMenus.add(exit);


        //Enter Menu
        System.out.println("Welcome to ticket master!");
        Scanner sc = new Scanner(System.in);
        int currentOption = -1;
        while(currentOption != exit.getOptionNumber()) {
            System.out.println("MAIN MENU:\nPlease select one of the following options by typing the option #" );
            for(AMenu temp: allMenus) {
                temp.printOption();
            }

            menuChecker: try {
                String input = sc.nextLine();
                currentOption = Integer.parseInt(input);

                //If the option is to exit, no need to do anything else. Exit the try clause and proceed
                if(currentOption == exit.getOptionNumber()) {
                    exit.doMethod();
                    break menuChecker;
                }

                //If other option is selected, look for it in the menu list. Perform operation and go back to main loop
                for(AMenu temp : allMenus) {
                    if(temp.getOptionNumber() == currentOption) {
                        //allMenus.get(currentOption-1).doMethod();
                        break menuChecker;
                    }
                }

                //If we got here, then none of the menus were selected and it's an error
                throw new Exception("Int but not on the menu");

            }
            catch (Exception e) {
                Tools.printErrorMessage("Sorry, only integers corresponding to the options are allowed. The menu will be "+
                "reprinted, please try again\n\n\n");
                currentOption = -1;

                //If the Exception was thrown by me, then we don't want to read in again from the scanner until the user
                //is prompted with the menu again. If the exception was thrown because the input wasn't an int, then
                //we want to clear the buffer
                if(e.getMessage() == null) {
                    sc.nextLine();
                }
            }
        }


        //Terminate operations
        sc.close();
    }
}
