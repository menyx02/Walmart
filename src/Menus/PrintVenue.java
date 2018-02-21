package Menus;

public class PrintVenue extends AMenu{

    public PrintVenue(int optionNumber) {
        super(optionNumber);
        super.optionDescription = "Print the current seats status of the Venue";
    }


    public void doMethod() {
        System.out.println("domethod print venue");
    }
}
