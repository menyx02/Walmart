package Schedulers;

import Model.Position;
import Venues.Venue;

import java.util.ArrayList;

public interface IScheduler {

    final int TIME_ALLOWANCE = 5000;

    void processRequest(String userName, int ticketsWanted, int wantThemTogether);

    //Return False if the number of seats wanted are not available in the venue
    boolean findIfNumberOfSeatsIsAvailable(int numberOfSeatsWanted);

    boolean findIfSeatsWantedAreTogether(int numberofSeatsWanted);

    void holdTickets(ArrayList<Position> listOfTickets, String userName);

    //Returns false if the tickets have expired, and reservation can't be made
    boolean reserveTickets(ArrayList<Position> listOfTickets, String userName);

    //Returns true if at least one of the tickets has expired
    boolean checkIfTicketsHaveExpired(ArrayList<Position> listOfTickets);

    //Updates the
    void updateStatusAfterExpiring(ArrayList<Position> listOfTickets);


}
