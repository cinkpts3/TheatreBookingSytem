package theatrebookingsystem.model;

import utils.CustomList;

public class BookingModel {


    private PerfomanceModel perfomance;
    private CustomerModel customer;
    private int bookingId;
    private CustomList<Ticket> tickets;

    private float totalprice;
    public BookingModel(PerfomanceModel perfomance, CustomerModel customer,  CustomList<Ticket> tickets){

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
                "; TICKETS: " + tickets;
    }
}
