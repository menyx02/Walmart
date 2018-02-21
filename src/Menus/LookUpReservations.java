package Menus;

public class LookUpReservations extends AMenu{

    public LookUpReservations(int optionNumber) {
        super(optionNumber);
        super.optionDescription = "Reserve seats";
    }


    public void doMethod() {
        System.out.println("domethod look up reservations ");
    }
}
