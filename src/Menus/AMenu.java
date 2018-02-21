package Menus;

import Schedulers.Scheduler;
import Venues.Venue;

public abstract class AMenu {

    protected int optionNumber;
    protected String optionDescription;
    protected Scheduler scheduler;

    public AMenu(int optionNumber, Scheduler scheduler) {
        this.optionNumber = optionNumber;
        this.scheduler = scheduler;
    }

    public int getOptionNumber() {
        return this.optionNumber;
    }

    //Method to print the option description out to the console
    public void printOption() {
        System.out.println("Option " + this.optionNumber + ": " + this.optionDescription);
    }

    //If the menu is selected, then the doMethod performs the corresponding action
    public abstract void doMethod();

}
