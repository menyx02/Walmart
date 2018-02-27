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
        //This will be tested through individual methods since junit doesn't accept terminal input
    }

    @Test
    public void findIfNumberOfSeatsIsAvailable() throws Exception {
        //Not Enough seats
        assertEquals(false, scheduler.findIfNumberOfSeatsIsAvailable(30));
        assertEquals(false, scheduler.findIfNumberOfSeatsIsAvailable(26));

        //Enough seats
        assertEquals(true, scheduler.findIfNumberOfSeatsIsAvailable(0));
        assertEquals(true, scheduler.findIfNumberOfSeatsIsAvailable(1));
        assertEquals(true, scheduler.findIfNumberOfSeatsIsAvailable(25));

    }

    @Test
    public void checkIfListTicketsIsAvailable() throws Exception {
        //All available
        ArrayList<Position> list = new ArrayList<Position>();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(2,2);
        Position pos3 = new Position(1,4);
        Position pos4 = new Position(4,4);
        Position pos5 = new Position(3,0);
        list.add(pos1);
        list.add(pos2);
        list.add(pos3);
        list.add(pos4);
        list.add(pos5);

        assertEquals(true, scheduler.checkIfListTicketsIsAvailable(list));


        //1 available
        scheduler.venue.getSeatAt(0,0).changeStatus(Seat.Status.RESERVED,"");
        scheduler.venue.getSeatAt(2,2).changeStatus(Seat.Status.RESERVED, "");
        scheduler.venue.getSeatAt(1,4).changeStatus(Seat.Status.RESERVED,"");
        scheduler.venue.getSeatAt(4,4).changeStatus(Seat.Status.RESERVED, "");
        assertEquals(false, scheduler.checkIfListTicketsIsAvailable(list));


        //None available
        scheduler.venue.getSeatAt(3,0).changeStatus(Seat.Status.RESERVED, "");
        assertEquals(false, scheduler.checkIfListTicketsIsAvailable(list));

    }

    @Test
    public void checkIfPositionsAreValidForThisVenue() throws Exception {
        ArrayList<Position> positions = new ArrayList<Position>();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(5,5);
        Position pos3 = new Position(6,6);

        //Valid position
        positions.add(pos1);
        assertEquals(true, scheduler.checkIfPositionsAreValidForThisVenue(positions));


        //Not valid position
        positions.add(pos2);
        positions.add(pos3);
        assertEquals(false, scheduler.checkIfPositionsAreValidForThisVenue(positions));

    }

    @Test
    public void selectTickets() throws Exception {
        scheduler.venue = new Venue(5,5);
        ArrayList<Position> tickets = new ArrayList<Position>();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,1);

        //Tickets are available and were selected
        tickets.add(pos1);
        tickets.add(pos2);

        scheduler.selectTickets(tickets);
        assertEquals(Seat.Status.SELECTED, scheduler.venue.getSeatAt(0,0).checkStatus());
        assertEquals(Seat.Status.SELECTED, scheduler.venue.getSeatAt(1,1).checkStatus());
    }

    @Test
    public void unselectTickets() throws Exception {
        scheduler.venue = new Venue(5,5);
        ArrayList<Position> tickets = new ArrayList<Position>();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,1);

        //Tickets are available and were selected
        tickets.add(pos1);
        tickets.add(pos2);

        scheduler.selectTickets(tickets);
        assertEquals(Seat.Status.SELECTED, scheduler.venue.getSeatAt(0,0).checkStatus());
        assertEquals(Seat.Status.SELECTED, scheduler.venue.getSeatAt(1,1).checkStatus());

        //Now unselect
        scheduler.unselectTickets(tickets);
        assertEquals(Seat.Status.AVAILABLE, scheduler.venue.getSeatAt(0,0).checkStatus());
        assertEquals(Seat.Status.AVAILABLE, scheduler.venue.getSeatAt(1,1).checkStatus());

    }

    @Test
    public void holdTickets() throws Exception {
        scheduler.venue = new Venue(5,5);
        ArrayList<Position> tickets = new ArrayList<Position>();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,1);

        //Tickets are available and were selected
        tickets.add(pos1);
        tickets.add(pos2);

        scheduler.holdTickets(tickets, "");
        assertEquals(Seat.Status.HELD, scheduler.venue.getSeatAt(0,0).checkStatus());
        assertEquals(Seat.Status.HELD, scheduler.venue.getSeatAt(1,1).checkStatus());
    }

    @Test
    public void unholdTickets() throws Exception {
        scheduler.venue = new Venue(5,5);
        ArrayList<Position> tickets = new ArrayList<Position>();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,1);

        //Tickets are available and were selected
        tickets.add(pos1);
        tickets.add(pos2);

        scheduler.unholdTickets(tickets);
        assertEquals(Seat.Status.AVAILABLE, scheduler.venue.getSeatAt(0,0).checkStatus());
        assertEquals(Seat.Status.AVAILABLE, scheduler.venue.getSeatAt(1,1).checkStatus());
    }

    @Test
    public void reserveTickets() throws Exception {
        scheduler.venue = new Venue(5,5);
        ArrayList<Position> tickets = new ArrayList<Position>();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,1);

        //Tickets are available and were selected
        tickets.add(pos1);
        tickets.add(pos2);
        assertEquals(true, scheduler.reserveTickets(tickets, ""));
        assertEquals(Seat.Status.RESERVED, scheduler.venue.getSeatAt(0,0).checkStatus());
        assertEquals(Seat.Status.RESERVED, scheduler.venue.getSeatAt(1,1).checkStatus());

        //Ticket is not available
        scheduler.venue.getSeatAt(1,1).changeStatus(Seat.Status.RESERVED,"");
        assertEquals(false, scheduler.selectTickets(tickets));

        scheduler.venue.getSeatAt(1,1).changeStatus(Seat.Status.HELD, "");
        assertEquals(false, scheduler.selectTickets(tickets));

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
        Thread.sleep(scheduler.TIME_ALLOWANCE + 1000);
        venue.getSeatAt(tickets.get(1).row, tickets.get(1).column).changeStatus(Seat.Status.HELD, "");
        venue.getSeatAt(tickets.get(2).row, tickets.get(2).column).changeStatus(Seat.Status.HELD, "");
        venue.getSeatAt(tickets.get(3).row, tickets.get(3).column).changeStatus(Seat.Status.HELD, "");
        venue.getSeatAt(tickets.get(4).row, tickets.get(4).column).changeStatus(Seat.Status.HELD, "");
        assertEquals(true, scheduler.checkIfTicketsHaveExpired(tickets));

        //All tickets should be expired
        for(Position temp: tickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.HELD, "");
        }
        Thread.sleep(scheduler.TIME_ALLOWANCE + 1000);
        assertEquals(true, scheduler.checkIfTicketsHaveExpired(tickets));

    }

    @Test
    public void updateStatusAfterExpiring() throws Exception {
        scheduler.venue = new Venue(5,5);
        ArrayList<Position> tickets = new ArrayList<Position>();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,1);

        tickets.add(pos1);
        tickets.add(pos2);
        scheduler.holdTickets(tickets, "");

        Thread.sleep(scheduler.TIME_ALLOWANCE + 1000);

        scheduler.updateStatusAfterExpiring(tickets);

        assertEquals(Seat.Status.AVAILABLE, scheduler.venue.getSeatAt(0,0).checkStatus());
        assertEquals(Seat.Status.AVAILABLE, scheduler.venue.getSeatAt(1,1).checkStatus());



    }

}