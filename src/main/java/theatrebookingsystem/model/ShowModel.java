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
        this.runningTime = runningTime;
        this.startDate = startDate;
        setEndDate(endDate);
        this.stallsTicketPrice = stallsTicketPrice;
        this.circleTicketPrice = circleTicketPrice;
        this.balconyTicketPrice = balconyTicketPrice;
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

    public double getStallsTicketPrice() {
        return stallsTicketPrice;
    }

    public void setStallsTicketPrice(double stallsTicketPrice) {
        this.stallsTicketPrice = stallsTicketPrice;
    }

    public double getCircleTicketPrice() {
        return circleTicketPrice;
    }

    public void setCircleTicketPrice(double circleTicketPrice) {
        this.circleTicketPrice = circleTicketPrice;
    }

    public double getBalconyTicketPrice() {
        return balconyTicketPrice;
    }

    public void setBalconyTicketPrice(double balconyTicketPrice) {
        this.balconyTicketPrice = balconyTicketPrice;
    }
    @Override
    public String toString(){
        return "Title" + title +
                "; running time: " + runningTime +
                "; start date: " + startDate +
                "; end date: " + endDate +
                "; stalls ticket price: " + stallsTicketPrice +
                "; circle ticket price: " + circleTicketPrice +
                "; balcony ticket price: " + balconyTicketPrice;

    }
}
