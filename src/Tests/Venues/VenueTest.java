package Tests.Venues;

import Model.Position;
import Model.Reservation;
import Model.Seat;
import Venues.Venue;
import Venues.VenueA;

import java.util.ArrayList;
import java.util.Random;

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

    @org.junit.Test
    public void calculatePriceOfList() throws Exception {
        ArrayList<Position> list = new ArrayList<Position>();

        //Price of 0, no tickets selected.
        assertEquals(0, venue.calculatePriceOfList(list));

        //Price of 1 expensive ticket
        list.add(new Position(0,0));
        assertEquals(250, venue.calculatePriceOfList(list));

        //Price of 1 of each
        list.add(new Position(7,7));
        list.add(new Position(11, 11));
        assertEquals(525, venue.calculatePriceOfList(list));
    }

    @org.junit.Test
    public void initializeSeats() throws Exception {
        venue = new Venue(numRows, numColumns);
        Random rand = new Random();

        //Test random numbers to make sure all seats are initialized
        for(int i = 0; i < 40; i++) {
            int random = rand.nextInt(numRows);
            assertNotEquals(null, venue.getSeatAt(random,random));
        }
    }

    @org.junit.Test
    public void getReservationByName() throws Exception {

        //No reservation with that name
        assertEquals(0, venue.getReservationByName("Manuel").size());

        //1 reservation with that name
        Seat[] seats = new Seat[1];
        seats[0] = new Seat(new Position(0,0), Seat.Status.AVAILABLE, 100);
        Reservation res1 = new Reservation("manuel", 3, seats);
        venue.addReservation(res1);

        assertEquals(1, venue.getReservationByName("Manuel").size());


        //multiple reservations with that name;
        Reservation res2 = new Reservation("manuel", 3, seats);
        Reservation res3 = new Reservation("manuel",3, seats);
        Reservation res4 = new Reservation("manuel", 3, seats);
        venue.addReservation(res2);
        venue.addReservation(res3);
        venue.addReservation(res4);

        assertEquals(4, venue.getReservationByName("manuel").size());

    }

    @org.junit.Test
    public void getSeats() throws Exception {
        Position pos1 = new Position(0,0);
        Seat seat1 = new Seat(pos1, Seat.Status.AVAILABLE, 250);
        ArrayList<Position> list = new ArrayList<Position>();

        //0 tickets to return
        assertEquals(0, venue.getSeats(list).length);

        //1 ticket to return
        list.add(pos1);
        assertEquals(1, venue.getSeats(list).length);
        assertEquals(seat1, venue.getSeats(list)[0]);

        //multiple tickets to return
        Position pos2 = new Position(8,8);
        Seat seat2 = new Seat(pos2, Seat.Status.AVAILABLE, 175);
        list.add(pos2);

        Position pos3 = new Position( 15,15);
        Seat seat3 = new Seat(pos3, Seat.Status.AVAILABLE, 100);
        list.add(pos3);
        Seat[] seatArray = new Seat[3];
        seatArray[0] = seat1;
        seatArray[1] = seat2;
        seatArray[2] = seat3;

        assertEquals(3, venue.getSeats(list).length);
        assertEquals(seatArray, venue.getSeats(list));

    }


}