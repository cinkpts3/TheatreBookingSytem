package theatrebookingsystem.model;
public class Seat {
        private String seatNumber;
        private boolean isBooked;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
            this.isBooked = false;
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
}


