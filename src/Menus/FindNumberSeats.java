package Menus;

public class FindNumberSeats extends AMenu {


    public FindNumberSeats(int optionNumber) {
        super(optionNumber);
        super.optionDescription = "Find the number of seats available in the Venue";
    }

    public void doMethod() {
        System.out.println("domethod find number seats");

    }

}
