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
        VenueA venue = new Venue(9, 31);

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

        int currentOption = -1;
        Scanner sc = new Scanner(System.in);
        while(currentOption != exit.getOptionNumber()) {
            System.out.println("Please select one of the following options by typing the option #" );
            for(AMenu temp: allMenus) {
                temp.printOption();
            }

            try {
                currentOption = sc.nextInt();


                for(AMenu temp : allMenus) {
                    if(temp.getOptionNumber() == currentOption) {
                        System.out.println("Current option: "  + currentOption);
                        allMenus.get(currentOption-1).doMethod();
                    }
                }
            }
            catch (Exception e) {
                Tools.printErrorMessage("Sorry, only integers corresponding to the options are allowed. The menu will be "+
                "reprinted, please try again\n\n\n");
                currentOption = -1;
                sc.next();
            }
        }


        //Terminate operations
        sc.close();
    }
}
