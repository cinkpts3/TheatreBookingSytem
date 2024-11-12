package theatrebookingsystem.model;

import java.time.LocalDate;
import java.util.Random;

public class PerfomanceModel {


        private LocalDate date;
        private String matineeTime;
        private String eveningTime;

        private ShowModel show;

        private int id;
        public PerfomanceModel(int id, LocalDate date, String matineeTime,String eveningTime, ShowModel show){ //, ShowModel show
           this.id = id;
            this.show = show;
            setDate(date);
            this.matineeTime = matineeTime;
            this.eveningTime = eveningTime;

        }


        public LocalDate getDate(){
            return date;
        }

        public void setDate(LocalDate date){
            if (date.isAfter(show.getStartDate()) && date.isBefore(show.getEndDate())){ //the date of perfomance is before end date and after start date
               this.date = date;
            }
            else{
                throw new IllegalArgumentException("date should be between start and end date of a show");
            }
        }

        public String getMatineeTime(){
           return matineeTime;
        }
        public String getEveningTime(){
            return eveningTime;
        }



        public String toString(){
            return "TITLE: " + show.getTitle() +
                    "; DATE: " + date +
                    "; MATINEE TIME: " + matineeTime +
                    "; EVENING TIME: " + eveningTime;
        }


    public int getId() {
            return id ;
    }


}
