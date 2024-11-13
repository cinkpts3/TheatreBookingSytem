package theatrebookingsystem.model;

import utils.CustomList;

public class BookingModel {


    private PerfomanceModel perfomance;
    private CustomerModel customer;
    private int bookingId;
    private CustomList<Seat> seats;

    private float totalprice;
    public BookingModel(PerfomanceModel perfomance, CustomerModel customer, CustomList<Seat> seats) {//,  CustomList<Seat> seats){

        this.customer = customer;
        this.perfomance = perfomance;
        this.seats = seats;

    }




    public int getBookingId() {
        return bookingId;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public PerfomanceModel getPerformance() {
        return perfomance;
    }

    public CustomList<Seat> getSeats() {
        return seats;
    }
    public String toString(){
        return "PERFOMANCE: " + perfomance +
                "; CUSTOMER: " + customer +
                "; TICKETS: " + seats;
    }
}
