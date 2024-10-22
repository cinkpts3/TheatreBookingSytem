package theatrebookingsystem.model;

public class ShowModel {
    private String title;
    private double runningTime;
    private int startDate;
    private int endDate;
    private double ticketPrice;



    public ShowModel(String title, double runningTime, int startDate, int endDate, double ticketPrice){
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

    public int getStartDate(){
        return startDate;
    }
    public void setStartDate(int startDate){
        if (startDate>=1 && startDate<=31){
            this.startDate = startDate;
        }
    }
    public int getEndDate(){
        return endDate;
    }
    public void setEndDate(int endDate){
        if (endDate>=1 && endDate<=31){
            this.endDate = endDate;
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
