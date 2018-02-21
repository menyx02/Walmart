package Menus;

import Schedulers.Scheduler;
import Venues.Venue;

public class FindSeats extends AMenu {

    public FindSeats(int optionNumber, Scheduler scheduler) {
        super(optionNumber, scheduler);
        super.optionDescription = "Find available seats";
    }


    public void doMethod() {

        System.out.println("domethod find seats");

    }
}
