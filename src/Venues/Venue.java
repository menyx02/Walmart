package Venues;

import Model.Position;
import Model.Reservation;
import Model.Seat;
import Utils.Tools;

import java.util.ArrayList;
import java.util.Collections;

public class Venue extends VenueA {


    public Venue(int numRows, int numColumns) {
        super(numRows, numColumns);
     }


    //This method will create a list with the row indexes of those rows that have enough
    //tickets to satisfy the number of tickets wanted. Don't have to be together, just
    //enough
    public ArrayList<Integer> getIndexesOfRowsThatHaveEnoughSeats(int numberTicketsWanted) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int row = 0; row < numRows; row++) {
            if( getAvailableSeatsAtRow(row) >= numberTicketsWanted) {
                list.add(row);
            }
        }
        return list;
    }

    private int getAvailableSeatsAtRow(int row) {
        int counterSeats = 0;
        for(int i = 0; i < numColumns; i++) {
            if(seats[row][i].checkStatus() == Seat.Status.AVAILABLE) counterSeats++;
        }

        return counterSeats;
    }

    //This method calculates the price of the seat based on the row (how close to the stage)
    public int calculatePriceOfSeat(int row) {
        if(row < 4) {
            return 250;
        }
        else if(row < 10) {
            return 175;
        }
        else {
            return 100;
        }
    }

    public int calculatePriceOfList(ArrayList<Position> listOfTickets){
        int sum = 0;
        for(Position temp: listOfTickets) {
            sum += this.calculatePriceOfSeat(temp.row);
        }
        return sum;
    }

    public void printSelectedTickets(ArrayList<Position> listOfTickets) {
        System.out.println("Number of tickets: " + listOfTickets.size());
        System.out.println("Total for this order $" + this.calculatePriceOfList(listOfTickets));
        System.out.println("Seats reserved:");

        for(Position temp: listOfTickets ){
            System.out.println("Row: " + temp.row + " Column: " + temp.column + " Price: $" + this.calculatePriceOfSeat(temp.row));
        }
        System.out.println();
    }

}
