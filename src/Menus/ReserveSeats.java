package Menus;

import Schedulers.Scheduler;
import Venues.Venue;

public class ReserveSeats extends AMenu{

    public ReserveSeats(int optionNumber, Scheduler scheduler) {
        super(optionNumber, scheduler);
        super.optionDescription = "Reserve seats";
    }


    public void doMethod() {
        System.out.println("domethod Reserve seats");
    }
}
