package theatrebookingsystem.model;

public abstract class Ticket {
    private String seatType;
    private int seatNumber;

    private double price;

    public Ticket(String seatType, int seatNumber, double price) {
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public String getSeatType() {
        return seatType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Seat Type: " + seatType + ", Seat Number: " + seatNumber + ", Price: " + price;
    }
}
