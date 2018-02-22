package Model;

import java.util.ArrayList;

public class Reservation {

    private String owner;
    private int numTickets;
    private Seat[] seats;
    private int totalCost;

    public Reservation(String owner, int numTickets, Seat[] seats) {
        this.owner = owner;
        this.numTickets = numTickets;
        this.seats = seats;
        this.totalCost = calculateTotalCost();
    }

    public void prettyPrintReservation() {
        System.out.println("\nName for the reservation: " + this.owner);
        System.out.println("Number of tickets: " + this.numTickets);
        System.out.println("Seats reserved:");
        System.out.println("Total for this order $" + this.totalCost);

        for(Seat temp: seats ){
            System.out.println("Row: " + temp.getPosition().row + " Column: " + temp.getPosition().column);
        }
        System.out.println();
    }

    public String getOwner() {return owner;}

    private int calculateTotalCost() {
        int sum = 0;
        for(Seat temp: seats) {
            sum += temp.getPrice();
        }
        return sum;
    }

}
