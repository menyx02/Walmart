package Model;

import Utils.Tools;

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

    public long getLastModified() {return lastModified;}

    public int getPrice() {return price;}

    public String prettyPrintStatus() {
        if(status == Status.AVAILABLE) return (Tools.ANSI_GREEN + " S " + Tools.ANSI_RESET);
        else if(status == Status.HELD) return (Tools.ANSI_YELLOW + " S " + Tools.ANSI_RESET);
        else if(status == Status.RESERVED) return (Tools.ANSI_RED + " S " + Tools.ANSI_RESET);
        else return "extra, should not be here";
    }

}
