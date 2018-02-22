package Schedulers;

import Model.Position;
import Model.Reservation;
import Model.Seat;
import Utils.Tools;
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
        if(findIfNumberOfSeatsIsAvailable(ticketsWanted) == false) {
            Tools.printErrorMessage("Sorry, we do not have those many seats available. Check the current availability" +
            " on the main menu and then try again!\n\n");
            return;
        }


        //When a list of seats is returned, hold them and tell user he has 10 seconds otherwise he will have to
        //go back to the main menu and try again. The hold status should also assign the name of the person
        // as temporary owners of the tickets, and create timestamp. When confirmation, check that the hold was made
        //by that person and that it's within the allotted time.

        //if together try to get together
        if(wantThemTogether == 1) {

        }
        //
        else if(wantThemTogether == 0) {

        }

    }


    @Override
    public boolean findIfNumberOfSeatsIsAvailable(int numberOfSeatsWanted) {
       if(venue.getAvailableSeats() < numberOfSeatsWanted) return false;
       else return true;
    }


    @Override
    public boolean findIfSeatsWantedAreTogether(int numberOfSeatsWanted) {
        return false;
    }


    @Override
    public void holdTickets(ArrayList<Position> listOfTickets, String userName) {
        //User is thinking about getting this tickets, hold them.
        for(Position temp : listOfTickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.HELD, userName);
        }

        //Update availability of venue
        venue.decreaseAvailableTicketsBy(listOfTickets.size());
    }

    @Override
    public boolean reserveTickets(ArrayList<Position> listOfTickets, String userName) {
        if(checkIfTicketsHaveExpired(listOfTickets) == true) {
            //Reservation can not be made because the user took too long
            updateStatusAfterExpiring(listOfTickets);
            return false;
        }

        //Update the seats status with the Venue
        for(Position temp: listOfTickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.RESERVED, userName);
        }

        //Add the reservation to the venue
        Reservation reservation = new Reservation(userName, listOfTickets.size(), venue.getSeats(listOfTickets));
        venue.addReservation(reservation);


        return true;
    }

    @Override
    public boolean checkIfTicketsHaveExpired(ArrayList<Position> listOfTickets) {
        for(Position temp: listOfTickets) {
            long lastModified = venue.getSeatAt(temp.row, temp.column).getLastModified();
            double difference = (System.currentTimeMillis() - lastModified);

            if(difference > TIME_ALLOWANCE) {
                return true;
            }
        }
        //All tickets are still valid
        return false;
    }

    @Override
    public void updateStatusAfterExpiring(ArrayList<Position> listOfTickets) {
        for(Position temp: listOfTickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.AVAILABLE, "");
        }

        //Add tickets back to the total availability
        venue.increaseAvailableTicketsBy(listOfTickets.size());
    }
}
