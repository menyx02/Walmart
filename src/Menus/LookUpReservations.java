package Menus;

import Model.Reservation;
import Schedulers.Scheduler;
import Utils.Tools;
import Venues.Venue;

import java.util.Scanner;

public class LookUpReservations extends AMenu{

    public LookUpReservations(int optionNumber, Scheduler scheduler) {
        super(optionNumber, scheduler);
        super.optionDescription = "Look up reservations";
    }


    public void doMethod() {
        System.out.println("domethod look up reservations ");

        System.out.println("Please provide your name to look for your reservation\n");
        Scanner sc = new Scanner(System.in);
        String nameQuery = sc.nextLine();
        for(Reservation temp : scheduler.venue.getReservations()) {
            if(nameQuery.toLowerCase().equals(temp.getOwner())) {
                System.out.println("We found the following reservation under the name " + nameQuery);
                temp.prettyPrintReservation();
                return;
            }
        }

        Tools.printErrorMessage("We are sorry, no reservation matches the name that you provided\n\n");
    }
}
