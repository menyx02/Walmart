package Menus;

import Schedulers.Scheduler;
import Utils.Tools;
import Venues.Venue;

public class FindNumberSeats extends AMenu {


    public FindNumberSeats(int optionNumber, Scheduler scheduler) {
        super(optionNumber, scheduler);
        super.optionDescription = "Find the number of seats available in the Venue";
    }

    public void doMethod() {
        System.out.print(Tools.ANSI_GREEN + "Available seats in the venue: " + scheduler.venue.getAvailableSeats() + "/");
        System.out.println(scheduler.venue.getTotalSeats() + Tools.ANSI_RESET + "\n\n");
    }

}
