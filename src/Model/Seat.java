package Model;

import Utils.Tools;

public class Seat {

    public enum Status {
        AVAILABLE, HELD, RESERVED, SELECTED
    }

    private Position position;
    private Status status;
    private int price;
    private long lastModified;
    private String owner;

    public Seat(Position position, Status status, int price) {
        this.position = position;
        this.status = status;
        this.price = price;
        this.owner = "";
    }

    //Create a timestamp every time the status changes
    public void changeStatus(Status newStatus, String newOwner) {
        this.status = newStatus;
        this.owner = newOwner;
        this.lastModified = System.currentTimeMillis();
    }

    public Status checkStatus() {return status;}

    public void changePrice(int price) {
        this.price = price;
    }

    public Position getPosition() {return position;}


    public long getLastModified() {return lastModified;}

    public int getPrice() {return price;}

    public String prettyPrintStatus() {
        if(status == Status.AVAILABLE) return (Tools.ANSI_GREEN + " S " + Tools.ANSI_RESET);
        else if(status == Status.HELD) return (Tools.ANSI_YELLOW + " S " + Tools.ANSI_RESET);
        else if(status == Status.RESERVED) return (Tools.ANSI_RED + " S " + Tools.ANSI_RESET);
        else if(status == Status.SELECTED) return (Tools.ANSI_PURPLE + " S " + Tools.ANSI_RESET);
        else return "extra, should not be here";
    }

    public static String getStatusCoding() {
        StringBuilder sb = new StringBuilder();
        sb.append(Tools.ANSI_GREEN + "S: available\n");
        sb.append(Tools.ANSI_PURPLE + "S: selected\n");
        sb.append(Tools.ANSI_YELLOW + "S: held\n");
        sb.append(Tools.ANSI_RED + "S: reserved\n" + Tools.ANSI_RESET);

        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {

        boolean scoreKeeper = false;

        if(object != null && object instanceof Seat) {

            scoreKeeper = (this.position.equals(((Seat) object).position));
            scoreKeeper &= (this.status == ((Seat) object).status);
            scoreKeeper &= (this.owner.equals(((Seat) object).owner));
            scoreKeeper &= (this.price == ((Seat) object).price);
        }

        return scoreKeeper;
    }


}
