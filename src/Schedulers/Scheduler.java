package Schedulers;

import Model.Position;
import Model.Reservation;
import Model.Seat;
import Utils.Tools;
import Venues.Venue;
import Venues.VenueA;

import java.util.ArrayList;
import java.util.Scanner;

public class Scheduler implements IScheduler{

    public VenueA venue;


    public Scheduler(VenueA venue) {
        this.venue = venue;
    }


    @Override
    public void processRequest(String userName, int ticketsWanted) {
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




        //SELECT TICKETS
        Scanner sc = new Scanner(System.in);
        ArrayList<Position> list = new ArrayList<Position>();
        int doneSelecting = 0;
        while(doneSelecting != 1) {
            //Print the stage so user can see for seat selection, then get input
            venue.prettyPrint();
            list = Tools.convertUserInputToPositions(ticketsWanted);

            if(this.checkIfPositionsAreValidForThisVenue(list) == false) {
                Tools.printErrorMessage("Sorry, those seat positions are not valid. You are being redirected " +
                        "to the Main Menu, check the stage map and try again\n\n");
                return;
            }

            if(this.selectTickets(list) == false) {
                Tools.printErrorMessage("At least one of the seats you selected is not available. Try again");
                continue;
            }
            venue.prettyPrint();
            venue.printSelectedTickets(list);

            System.out.println("Do you like your selection and do you want to move to the reservation page? yes/no. " +
                    "Type main menu to go back");
            try {
                String input = sc.nextLine();

                if(input.equals("yes")) doneSelecting = 1;
                else if(input.equals("no"))  {
                    this.unselectTickets(list);
                    System.out.println("Ok, try different seats!");
                }
                else if(input.equals("main menu")) {
                    this.unselectTickets(list);
                    return;
                }
                else throw new Exception();
            }
            catch (Exception e) {
                Tools.printErrorMessage("Sorry, not valid input. Try again");
                this.unselectTickets(list);
            }
        }


        //TICKETS HOLD - RESERVATION

        //Change the tickets from selected to held
        this.unselectTickets(list);
        this.holdTickets(list, userName);


        System.out.println("\n\n\nWelcome to the reservation page! The seats that you have selected are:\n");
        for(Position temp: list ){
            System.out.println("Row: " + temp.row + " Column: " + temp.column);
        }
        venue.prettyPrint();
        System.out.println();
        System.out.println("We can hold your seats for 15 seconds, if the reservation hasn't been confirmed " +
        "by then the seats will expire and you will have to start the process again");

        System.out.println("Do you want to reserve these seats? yes/no. Type main menu to go back");

        try {
            String input = sc.nextLine();
            if(input.equals("yes")) {
                boolean processed = this.reserveTickets(list, userName);
                if(processed == false) {
                    //Could not reserve tickets
                    Tools.printErrorMessage("We are sorry! The tickets expired. Try again\n\n");
                    return;
                }
                else {
                    System.out.println("Thanks for buying your tickets with us! You can refer to your reservation " +
                            "through the \"Look up reservations\" option on the main menu");
                    System.out.println("You are now being redirected to the MAIN MENU\n\n");
                    return;
                }
            }
            else if(input.equals("no")) {
                System.out.println("We are sorry we couldn't assist you. Come back soon!\n\n");
                this.unholdTickets(list);
                return;
            }
            else if(input.equals("main menu")) {
                System.out.println("Reservation has been cancelled\n\n");
                this.unholdTickets(list);
                return;
            }
            else {
                throw new Exception();
            }
        }
        catch(Exception e) {
            Tools.printErrorMessage("There was an error processing your request, try again!\n\n");
            this.unholdTickets(list);
        }
    }


    @Override
    public boolean findIfNumberOfSeatsIsAvailable(int numberOfSeatsWanted) {
       if(venue.getAvailableSeats() < numberOfSeatsWanted) return false;
       else return true;
    }


    @Override
    public boolean checkIfListTicketsIsAvailable(ArrayList<Position> listOfTickets) {
        for(Position temp : listOfTickets) {
            if(venue.getSeatAt(temp.row, temp.column).checkStatus() != Seat.Status.AVAILABLE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkIfPositionsAreValidForThisVenue(ArrayList<Position> listOfTickets) {
        for(Position temp: listOfTickets) {
            if(temp.row >= this.venue.getNumRows() || temp.column >= this.venue.getNumColumns())
                return false;
        }
        return true;
    }

    @Override
    public boolean selectTickets(ArrayList<Position> listOfTickets) {
        if(this.checkIfListTicketsIsAvailable(listOfTickets) == false){
            return false;

        }

        for(Position temp : listOfTickets) {
                venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.SELECTED, "");
        }
        return true;
    }

    @Override
    public void unselectTickets(ArrayList<Position> listOfTickets) {
        for(Position temp : listOfTickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.AVAILABLE, "");
        }
    }


    @Override
    public boolean  holdTickets(ArrayList<Position> listOfTickets, String userName) {
        if(this.checkIfListTicketsIsAvailable(listOfTickets) == false) return false;
        //User is thinking about getting this tickets, hold them.
        for(Position temp : listOfTickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.HELD, userName);
        }

        //Update availability of venue
        venue.decreaseAvailableTicketsBy(listOfTickets.size());
        return true;
    }

    @Override
    public void unholdTickets(ArrayList<Position> listOfTickets) {
        for(Position temp : listOfTickets) {
            venue.getSeatAt(temp.row, temp.column).changeStatus(Seat.Status.AVAILABLE, "");
        }

        //Update availability of venue
        venue.increaseAvailableTicketsBy(listOfTickets.size());
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
        Reservation reservation = new Reservation(userName.toLowerCase(), listOfTickets.size(), venue.getSeats(listOfTickets));
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
