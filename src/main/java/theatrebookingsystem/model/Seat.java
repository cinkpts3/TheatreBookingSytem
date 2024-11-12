package theatrebookingsystem.model;

import com.example.theatrebookingsystem.PerfomanceController;

public class Seat {
        private String seatNumber;
        private boolean isBooked;
        private boolean isSelected;

        private PerfomanceModel perfomance;

        public Seat(String seatNumber, PerfomanceModel perfomance) {
            this.seatNumber = seatNumber;
            this.isBooked = false;
            this.isSelected = false;
            this.perfomance = perfomance;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public boolean isBooked() {
            return isBooked;
        }

        public void setBooked(boolean booked) {
            this.isBooked = booked;
        }
        public boolean isSelected() {
        return isSelected;
    }

        public void setSelected(boolean selected) {
        this.isSelected= selected;
    }
    public PerfomanceModel getPerfomance() {
        return perfomance;
    }

    public String toString(){
            return "Selected seats" + seatNumber;
    }
}


