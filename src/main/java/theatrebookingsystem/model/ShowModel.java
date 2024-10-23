package theatrebookingsystem.model;

import java.time.LocalDate;

public class ShowModel {
    private String title;
    private double runningTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private double ticketPrice;



    public ShowModel(String title, double runningTime, LocalDate startDate, LocalDate endDate, double ticketPrice){
        this.title = title;
        this.runningTime = runningTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketPrice = ticketPrice;
    }


    public String getTitle(){
        return title;
    }
    public void setTtile(String title){
        this.title = title;
    }

    public double getRunningTime(){
        return runningTime;
    }
    public void setRunningTime(double runningTime){
        if (runningTime> 0 ){
            this.runningTime = runningTime;
        }
    }

    public LocalDate getStartDate(){
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        if (endDate.isAfter(startDate)) {
            this.startDate = startDate;
        }
        else{
            throw new IllegalArgumentException("start date should be before end date!");
        }
    }

    public LocalDate getEndDate(){
        return endDate;
    }
    public void setEndDate(LocalDate endDate){
        if (startDate.isBefore(endDate)) {
            this.endDate = endDate;
        }
        else{
            throw new IllegalArgumentException("end date should be after start date");
        }
    }

    public double getTicketPrice(){
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice){ //to signify the ticket type
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString(){
        return "Title" + title + "; "
                + "running time: " + runningTime +"; "
                + "start date: " + startDate + "; "
                + "end date: " + endDate + "; "
                + "ticket price: : " + ticketPrice;

    }
}
