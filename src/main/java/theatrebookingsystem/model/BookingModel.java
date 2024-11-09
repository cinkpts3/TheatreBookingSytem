package theatrebookingsystem.model;

import utils.CustomList;

public class BookingModel {


    private PerfomanceModel perfomance;
    private CustomerModel customer;
    private int bookingId;
    private CustomList<Ticket> tickets;

    public BookingModel(PerfomanceModel perfomance, CustomerModel customer, int bookingId, CustomList<Ticket> tickets){

        this.bookingId = bookingId;
        this.customer = customer;
        this.perfomance = perfomance;
        this.tickets = tickets;
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

    public CustomList<Ticket> getTickets() {
        return tickets;
    }
    public String toString(){
        return "PERFOMANCE: " + perfomance +
                "; CUSTOMER: " + customer +
                "; BOOKING ID: " + bookingId+
                "; TICKETS: " + tickets;
    }
}
