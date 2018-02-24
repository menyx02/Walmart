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

    public static final Scanner sc = new Scanner(System.in);

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
                        "between every entry. Base Index starts at 1");

        //Scanner sc = new Scanner(System.in);
        int numberInputReceived = 0;
        while(numberInputReceived < numberOfTickets) {

            try {

                String input = Tools.sc.nextLine();
                Thread.sleep(2000);
                if(checkFormatInputTickets(input) == false) throw new Exception();

                int row = input.charAt(0);
                int column = input.charAt(2);
                Position x = new Position(row, column);
                listOfTickets.add(x);
                numberInputReceived++;
            }
            catch(Exception e) {
                Tools.printErrorMessage("Sorry, that input didn't follow the specified format");
                Tools.printErrorMessage("The entry was not recorded, try again");
            }
        }
        //sc.close();

        return listOfTickets;
    }

    //Returns false if any of the format requirements fails
    public static boolean checkFormatInputTickets(String input) {
        if(input.length() > 3) return false;
        else if(input.equals("")) return false;
        else if(Character.isDigit(input.charAt(0)) == false) return false;
        else if(input.charAt(1) != ',') return false;
        else if(Character.isDigit(input.charAt(2)) == false ) return false;
        else return true;
    }


}
