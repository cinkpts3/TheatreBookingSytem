package theatrebookingsystem.model;

import java.time.LocalDate;

public class PerfomanceModel {


        private LocalDate date;
        private String matineeTime;
        private String eveningTime;

        private ShowModel show;
        public PerfomanceModel(LocalDate date, String matineeTime,String eveningTime, ShowModel show){ //, ShowModel show
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
           return getMatineeTime();
        }
        public String getEveningTime(){
            return eveningTime;
        }

        public String toString(){
            return "Title: " + show.getTitle() +
                    "; date: " + date +
                    "; matinee time: " + matineeTime +
                    "; evening time: " + eveningTime;
        }




}
