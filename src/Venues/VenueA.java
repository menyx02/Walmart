package Venues;

import Model.Reservation;
import Model.Seat;

import java.util.ArrayList;

public abstract class VenueA {

    private int totalSeats;
    private int availableSeats;
    private Seat[][] seats;
    private ArrayList<Reservation> reservations;


    public VenueA() {
        this.totalSeats = 0;
        this.availableSeats = 0;
        this.reservations = new ArrayList<Reservation>();
        this.initializeSeats();
    }


    //This method fills up the array with seats, and initializes each one of them
    private void initializeSeats() {

    }

    //This method prints a representation of the venue
    public void prettyPrint() {

    }

    public Seat getSeatAt(int row, int column) {
        return seats[row][column];
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }


}
