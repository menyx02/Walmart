package Utils;

import Model.Position;

import java.util.ArrayList;
import java.util.Scanner;

public class Tools {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static void printErrorMessage(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }


    public static void resetColor() {
        System.out.print(ANSI_RESET);
    }

    public static ArrayList<Position> convertUserInputToPositions(int numberOfTickets) {
        ArrayList<Position> listOfTickets = new ArrayList<Position>();

        System.out.println("Please look at the map and type in the seats that you want to hold using "+
                "the following format \"row #,column #\", like this: 1,1\n Type in one seat at a time, and press enter " +
                        "between every entry. Base Index for stage map starts at 0");

        //Scanner sc = new Scanner(System.in);
        int numberInputReceived = 0;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(",");
        while(numberInputReceived < numberOfTickets) {

            try {

                String input = sc.nextLine();

                Position extract = checkFormatInputTickets(input);
                if(extract == null) {
                    throw new Exception();
                }


                //Dont allow duplicates
                if(listOfTickets.contains(extract) == true) {
                    Tools.printErrorMessage("Sorry, that was a duplicate entry. Try again");
                    continue;
                }

                listOfTickets.add(extract);
                numberInputReceived++;
            }
            catch(Exception e) {
                Tools.printErrorMessage("Sorry, that input didn't follow the specified format");
                Tools.printErrorMessage("The entry was not recorded, try again");
            }
        }

        return listOfTickets;
    }

    //Returns false if any of the format requirements fails
    public static Position checkFormatInputTickets(String input) {
        if(!input.contains(",")) return null;
        else if(input.equals("")) return null;

        Scanner sc = new Scanner(input);
        sc.useDelimiter(",");


        try {
            int firstNumber = sc.nextInt();
            int secondNumber = sc.nextInt();

            Position extract = new Position(firstNumber, secondNumber);
            return extract;

        }
        catch (Exception e) {
            return null;
        }

    }


}
