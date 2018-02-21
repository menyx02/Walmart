import Menus.*;
import Model.Position;
import Model.Seat;
import Schedulers.IScheduler;
import Schedulers.Scheduler;
import Utils.Tools;
import Venues.Venue;

import java.util.ArrayList;
import java.util.Scanner;


public class Driver {



    public static void main(String[] args) {

        //Initialize Menu
        AMenu printVenue = new PrintVenue(1);
        AMenu findNumberSeats = new FindNumberSeats(2);
        AMenu findSeats = new FindSeats(3);
        AMenu reserveSeats = new ReserveSeats(4);
        AMenu lookUpReservations = new LookUpReservations(5);
        AMenu exit = new Exit(6);

        ArrayList<AMenu> allMenus = new ArrayList<AMenu>();
        allMenus.add(printVenue);
        allMenus.add(findNumberSeats);
        allMenus.add(findSeats);
        allMenus.add(reserveSeats);
        allMenus.add(lookUpReservations);
        allMenus.add(exit);


        //Initialize Venue
        Venue venue = new Venue(9, 32);

        //Initialize Scheduler
        Scheduler scheudler = new Scheduler();

        //Enter Menu
        System.out.println("Welcome to ticket master!");

        int currentOption = 0;
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
                        allMenus.get(currentOption-1).doMethod();
                    }
                }

            }
            catch (Exception e) {
                Tools.printErrorMessage("Sorry, only integers corresponding to the options are allowed. The menu will be "+
                "reprinted, please try again\n\n\n");
                currentOption = 0;
                sc.next();
            }
        }


        //Terminate operations
        sc.close();
    }
}
