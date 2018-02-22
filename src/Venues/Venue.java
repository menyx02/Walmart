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

    public int getAvailableSeatsAtRow(int row) {
        int counterSeats = 0;
        for(int i = 0; i < numColumns; i++) {
            if(seats[row][i].checkStatus() == Seat.Status.AVAILABLE) counterSeats++;
        }

        return counterSeats;
    }


}
