package Schedulers;

import Model.Position;
import Venues.Venue;
import Venues.VenueA;

import java.util.ArrayList;

public class Scheduler implements IScheduler{

    public VenueA venue;


    public Scheduler(VenueA venue) {
        this.venue = venue;
    }


    //TODO- if status is changed, make sure the venue availability updates as well

    @Override
    public void processRequest(String userName, int ticketsWanted, int wantThemTogether) {

        //CHECK IF THOSE MANY SEATS ARE AVAILABLE


        //When a list of seats is returned, hold them and tell user he has 10 seconds otherwise he will have to
        //go back to the main menu and try again. The hold status should also assign the name of the person
        // as temporary owners of the tickets, and create timestamp. When confirmation, check that the hold was made
        //by that person and that it's within the allotted time.

        //if together try to get together


        //Else just return seats


    }

    //Return False if the number of seats wanted are not available in the venue
    @Override
    public boolean findIfNumberOfSeatsIsAvailable(int numberOfSeatsWanted) {
       if(venue.getAvailableSeats() < numberOfSeatsWanted) return false;
       else return true;
    }


    @Override
    public boolean findIfSeatsWantedAreTogether(int numberofSeatsWanted) {
        return false;
    }


    @Override
    public void holdTickets(ArrayList<Position> listOfTickets) {


    }

    @Override
    public void reserveTickets(ArrayList<Position> listOfTickets) {

        //Add reservation - name to lower case
    }
}
