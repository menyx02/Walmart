package Menus;

public class FindSeats extends AMenu {

    public FindSeats(int optionNumber) {
        super(optionNumber);
        super.optionDescription = "Find available seats";
    }


    public void doMethod() {

        System.out.println("domethod find seats");

    }
}
