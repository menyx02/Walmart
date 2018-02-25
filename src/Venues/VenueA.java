package Venues;

import Model.Position;
import Model.Reservation;
import Model.Seat;
import Utils.Tools;

import java.util.ArrayList;
import java.util.Collections;

public abstract class VenueA {

    protected int totalSeats;
    protected int availableSeats;
    protected int numRows;
    protected int numColumns;
    protected Seat[][] seats;
    protected ArrayList<Reservation> reservations;


    public VenueA(int numRows, int numColumns) {
        seats = new Seat[numRows][numColumns];
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.totalSeats = numRows * numColumns;
        this.availableSeats = numRows * numColumns;
        this.reservations = new ArrayList<Reservation>();
        this.initializeSeats();
    }


    //This method fills up the array with seats, and initializes each one of them
    private void initializeSeats() {
        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numColumns; c++) {
                int tempPrice = this.calculatePriceOfSeat(r);
                Seat seat = new Seat(new Position(r,c), Seat.Status.AVAILABLE, tempPrice);
                seats[r][c] = seat;
            }
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

        sb.append(Seat.getStatusCoding());

        System.out.println(sb.toString());
    }

    public Seat[] getSeats(ArrayList<Position> listOfTickets) {
        Seat[] seats = new Seat[listOfTickets.size()];


        for(int i = 0; i < listOfTickets.size(); i++) {
            int row = listOfTickets.get(i).row;
            int column = listOfTickets.get(i).column;
            seats[i] = this.getSeatAt(row, column);
        }

        return seats;
    }

    public void decreaseAvailableTicketsBy(int ticketsTaken) {
        this.availableSeats = availableSeats - ticketsTaken;
    }

    public void increaseAvailableTicketsBy(int ticketsReturned) {
        this.availableSeats = availableSeats + ticketsReturned;
    }

    public ArrayList<Reservation> getReservationByName(String owner) {
        ArrayList<Reservation> reservationsForOwner = new ArrayList<Reservation>();

        for(Reservation temp: reservations) {
            if(owner.toLowerCase().equals(temp.getOwner())) {
                reservationsForOwner.add(temp);
            }
        }

        return reservationsForOwner;
    }

    public Seat getSeatAt(int row, int column) {
        return seats[row][column];
    }

    public int getNumRows(){return numRows;}

    public int getNumColumns() {return numColumns;}

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public int getAvailableSeats(){return availableSeats;}

    public int getTotalSeats() {return totalSeats;}

    public ArrayList<Reservation> getAllReservations() {return reservations;}

    public abstract ArrayList<Integer> getIndexesOfRowsThatHaveEnoughSeats(int numberTicketsWanted);

    public abstract int calculatePriceOfSeat(int row);

    public abstract int calculatePriceOfList(ArrayList<Position> listOfTickets);

    public abstract void printSelectedTickets(ArrayList<Position> listOfTickets);

}
