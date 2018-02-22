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
