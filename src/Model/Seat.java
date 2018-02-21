package Model;

public class Seat {

    public enum Status {
        AVAILABLE, HELD, RESERVED
    }



    private Position position;
    private Status status;
    private int price;
    private long lastModified;

    public Seat(Position position, Status status, int price) {
        this.position = position;
        this.status = status;
        this.price = price;
    }

    //Create a timestamp every time the status changes
    public void changeStatus(Status newStatus) {
        this.status = newStatus;
        this.lastModified = System.currentTimeMillis();
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public Position getPosition() {return position;}

    public Status checkStatus() {return status;}
}
