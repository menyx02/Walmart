package Menus;

import Schedulers.Scheduler;
import Venues.Venue;

public class PrintVenue extends AMenu{

    public PrintVenue(int optionNumber, Scheduler scheduler) {
        super(optionNumber, scheduler);
        super.optionDescription = "Print the current seats status of the Venue";
    }


    public void doMethod() {
        super.scheduler.venue.prettyPrint();
    }
}
