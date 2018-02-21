package Menus;

import Schedulers.Scheduler;
import Venues.Venue;

public class PrintVenue extends AMenu{

    public PrintVenue(int optionNumber, Venue venue, Scheduler scheduler) {
        super(optionNumber, venue, scheduler);
        super.optionDescription = "Print the current seats status of the Venue";
    }


    public void doMethod() {
        System.out.println("domethod print venue");

    }
}
