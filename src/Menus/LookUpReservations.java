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

        System.out.println("Please provide your name to look for your reservation");
        Scanner sc = new Scanner(System.in);
        String nameQuery = sc.nextLine();

        Reservation queryReservation = scheduler.venue.getReservationByName(nameQuery);
        if(queryReservation == null) {
            Tools.printErrorMessage("We are sorry, no reservation matches the name that you provided\n\n");
            return;
        }
        else {
            System.out.println("We found the following reservation under the name " + nameQuery);
            queryReservation.prettyPrintReservation();
            return;
        }
    }
}
