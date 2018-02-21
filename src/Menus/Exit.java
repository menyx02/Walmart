package Menus;

public class Exit extends AMenu {



    public Exit(int optionNumber) {
        super(optionNumber);
        super.optionDescription = "Exit program";
    }

    public void doMethod() {
        System.out.println("domethod exit");

    }
}
