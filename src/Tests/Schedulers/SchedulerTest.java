package Tests.Schedulers;

import Model.Position;
import Model.Seat;
import Schedulers.Scheduler;
import Venues.Venue;
import Venues.VenueA;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SchedulerTest {
    VenueA venue;
    Scheduler scheduler;

    @Before
    public void setUp() throws Exception {
        venue = new Venue(5,5);
        scheduler = new Scheduler(venue);
    }

    @Test
    public void processRequest() throws Exception {
    }

    @Test
    public void findIfNumberOfSeatsIsAvailable() throws Exception {
    }

    @Test
    public void findIfSeatsWantedAreTogether() throws Exception {
    }

    @Test
    public void holdTickets() throws Exception {
    }

    @Test
    public void reserveTickets() throws Exception {
    }

    @Test
    public void checkIfTicketsHaveExpired() throws Exception {
        ArrayList<Position> tickets = new ArrayList<Position>();
        tickets.add(new Position(0,0));
        tickets.add(new Position(1,1));
        tickets.add(new Position(2,2));
        tickets.add(new Position(3,3));
        tickets.add(new Position(4,4));


        //All tickets should be good
        for(Position temp : tickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.HELD, "");
        }
        assertEquals(false, scheduler.checkIfTicketsHaveExpired(tickets));

        //One ticket should be expired
        venue.getSeatAt(tickets.get(0).row, tickets.get(0).column).changeStatus(Seat.Status.HELD, "");
        Thread.sleep(6000);
        venue.getSeatAt(tickets.get(1).row, tickets.get(1).column).changeStatus(Seat.Status.HELD, "");
        venue.getSeatAt(tickets.get(2).row, tickets.get(2).column).changeStatus(Seat.Status.HELD, "");
        venue.getSeatAt(tickets.get(3).row, tickets.get(3).column).changeStatus(Seat.Status.HELD, "");
        venue.getSeatAt(tickets.get(4).row, tickets.get(4).column).changeStatus(Seat.Status.HELD, "");
        assertEquals(true, scheduler.checkIfTicketsHaveExpired(tickets));

        //All tickets should be expired
        for(Position temp: tickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.HELD, "");
        }
        Thread.sleep(7000);
        assertEquals(true, scheduler.checkIfTicketsHaveExpired(tickets));

    }

    @Test
    public void updateStatusAfterExpiring() throws Exception {
    }

}