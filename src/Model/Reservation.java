package Model;

public class Reservation {

    private String owner;
    private int numTickets;
    private Position[] seats;

    public Reservation(String owner, int numTickets, Position[] seats) {
        this.owner = owner;
        this.numTickets = numTickets;
        this.seats = seats;
    }

    public void prettyPrintReservation() {
        System.out.println("\nName for the reservation: " + this.owner);
        System.out.println("Number of tickets: " + this.numTickets);
        System.out.println("Seats reserved:");

        for(Position temp: seats ){
            System.out.println("Column: " + temp.column + " Row: " + temp.row);
        }
        System.out.println();
    }

    public String getOwner() {return owner;}

}
