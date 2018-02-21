package Venues;

import Model.Position;
import Model.Reservation;
import Model.Seat;
import Utils.Tools;

import java.util.ArrayList;
import java.util.Collections;

public class Venue {

    private int totalSeats;
    private int availableSeats;
    private int numRows;
    private int numColumns;
    private Seat[][] seats;
    private ArrayList<Reservation> reservations;


    public Venue(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.totalSeats = numRows * numColumns;
        this.availableSeats = numRows * numColumns;
        seats = new Seat[numRows][numColumns];
        this.reservations = new ArrayList<Reservation>();
        this.initializeSeats();
    }


    //This method fills up the array with seats, and initializes each one of them
    private void initializeSeats() {
        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numColumns; c++) {
                int tempPrice = this.calculatePrice(r);
                Seat seat = new Seat(new Position(r,c), Seat.Status.AVAILABLE, tempPrice);
                seats[r][c] = seat;
            }
        }
    }

    //This method calculates the price of the seat based on the row (how close to the stage)
    //TODO - could be abstracted
    private int calculatePrice(int row) {
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

    //This method prints a representation of the venue
    public void prettyPrint() {
        StringBuilder sb = new StringBuilder();
        String stage = String.join("", Collections.nCopies(((numColumns/2)-1)," - "));
        sb.append(Tools.ANSI_CYAN + stage + "STAGE" + stage + "\n");
        sb.append(String.join("", Collections.nCopies(numColumns, " - ")) + "\n");
        sb.append(String.join("", Collections.nCopies(numColumns, " - ")) + "\n" + Tools.ANSI_RESET);

        //TODO - figure out a way to add row and column markers

        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numColumns; c++) {
                sb.append(seats[r][c].prettyPrintStatus());
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public Seat getSeatAt(int row, int column) {
        return seats[row][column];
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public int getAvailableSeats(){return availableSeats;}

    public int getTotalSeats() {return totalSeats;}

    public ArrayList<Reservation> getReservations() {return reservations;}
}
