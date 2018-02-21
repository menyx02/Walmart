package Menus;

import Schedulers.Scheduler;
import Venues.Venue;

public class Exit extends AMenu {



    public Exit(int optionNumber, Scheduler scheduler) {
        super(optionNumber, scheduler);
        super.optionDescription = "Exit program";
    }

    public void doMethod() {
        System.out.println("domethod exit");

    }
}
