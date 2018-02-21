package Menus;

public abstract class AMenu {

    protected int optionNumber;
    protected String optionDescription;

    public AMenu(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    public int getOptionNumber() {
        return this.optionNumber;
    }

    //Method to print the option description out to the console
    abstract void printOption();

    //If the menu is selected, then the doMethod performs the corresponding action
    abstract void doMethod();

}
