package Menus;

public class ReserveSeats extends AMenu{

    public ReserveSeats(int optionNumber) {
        super(optionNumber);
        super.optionDescription = "Look up reservations";
    }


    public void doMethod() {
        System.out.println("domethod Reserve seats");
    }
}
