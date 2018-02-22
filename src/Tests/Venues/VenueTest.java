package Tests.Venues;

import Model.Seat;
import Venues.Venue;
import Venues.VenueA;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VenueTest {
    VenueA venue;
    int numRows;
    int numColumns;

    @org.junit.Before
    public void setUp() throws Exception {
        this.numColumns = 40;
        this.numRows = 40;
        venue = new Venue(numRows,numColumns);
    }

    @org.junit.Test
    public void getIndexesOfRowsThatHaveEnoughSeats() throws Exception {
        //No seats are taken so every row should return true
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i = 0; i < numRows; i++) {
            list.add(i);
        }
        assertEquals(list, venue.getIndexesOfRowsThatHaveEnoughSeats(40));

        //Only one row has available seats
        for(int row = 0; row < numRows; row++) {
            for(int column = 0; column < numColumns; column++) {
                if(row != 2) {
                    venue.getSeatAt(row,column).changeStatus(Seat.Status.RESERVED, "");
                }
            }
        }
        list.clear();
        list.add(2);
        assertEquals(list, venue.getIndexesOfRowsThatHaveEnoughSeats(40));

        //No rows have available seats
        for(int column = 0; column < numRows; column++) {
            venue.getSeatAt(2,column).changeStatus(Seat.Status.RESERVED, "");
        }
        list.clear();
        assertEquals(list, venue.getIndexesOfRowsThatHaveEnoughSeats(40));



        //Prepare the class for new testing
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                venue.getSeatAt(i,j).changeStatus(Seat.Status.AVAILABLE, "");

            }
        }

    }

}