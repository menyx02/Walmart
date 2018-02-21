package Schedulers;

import Model.Position;
import Venues.Venue;

import java.util.ArrayList;

public interface IScheduler {

    final int TIME_ALLOWANCE = 5;

    boolean findIfNumberOfSeatsIsAvailable(int numberOfSeatsWanted);

    boolean findIfSeatsWantedAreTogether(int numberofSeatsWanted);

    void holdTickets(ArrayList<Position> listOfTickets);

    void reserveTickets(ArrayList<Position> listOfTickets);

}
