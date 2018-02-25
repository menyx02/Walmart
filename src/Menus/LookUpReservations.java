package Menus;

import Model.Reservation;
import Schedulers.Scheduler;
import Utils.Tools;
import Venues.Venue;

import java.util.ArrayList;
import java.util.Scanner;

public class LookUpReservations extends AMenu{

    public LookUpReservations(int optionNumber, Scheduler scheduler) {
        super(optionNumber, scheduler);
        super.optionDescription = "Look up reservations";
    }


    public void doMethod() {
        System.out.println("Please provide your name to look for your reservation");
        Scanner sc = new Scanner(System.in);
        String nameQuery = sc.nextLine();

        ArrayList<Reservation> reservations = scheduler.venue.getReservationByName(nameQuery);
        if(reservations.size() == 0) {
            Tools.printErrorMessage("We are sorry, no reservation matches the name that you provided\n\n");
            return;
        }
        else {

            System.out.println("We found the following reservation under the name " + nameQuery);
            for(Reservation temp : reservations) {
                temp.prettyPrintReservation();
            }
        }
    }
}
