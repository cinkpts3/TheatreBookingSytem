package theatrebookingsystem.model;

import java.time.LocalDate;

public class ShowModel {
    private String title;
    private double runningTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private double stallsTicketPrice;
    private double circleTicketPrice;
    private double balconyTicketPrice;



    public ShowModel(String title, double runningTime, LocalDate startDate, LocalDate endDate, double stallsTicketPrice, double circleTicketPrice, double balconyTicketPrice ){
        this.title = title;
        setRunningTime(runningTime);
        setStartDate(startDate);
        setEndDate(endDate);
        this.stallsTicketPrice = stallsTicketPrice;
        this.circleTicketPrice = circleTicketPrice;
        this.balconyTicketPrice = balconyTicketPrice;
    }


    public String getTitle(){
        return title;
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
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Start date should be before end date!");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }
    public void setEndDate(LocalDate endDate){
        if (startDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("End date should be after start date!");
        }
        this.endDate = endDate;
    }

    public double getStallsTicketPrice() {
        return stallsTicketPrice;
    }



    public double getCircleTicketPrice() {
        return circleTicketPrice;
    }



    public double getBalconyTicketPrice() {
        return balconyTicketPrice;
    }


    @Override
    public String toString(){
        return "TITLE" + title +
                "; RUNNING TIME: " + runningTime +
                "; START DATE: " + startDate +
                "; END DATE: " + endDate +
                "; STALLS TICKET PRICE: " + stallsTicketPrice +
                "; CIRCLE TICKET PRICE: " + circleTicketPrice +
                "; BALCONY TICKET PRICE: " + balconyTicketPrice;

    }
}
