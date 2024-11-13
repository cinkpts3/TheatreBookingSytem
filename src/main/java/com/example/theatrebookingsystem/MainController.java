    package com.example.theatrebookingsystem;

    import javafx.fxml.FXML;
    import javafx.scene.control.Tab;
    import javafx.scene.control.TabPane;
    import theatrebookingsystem.model.ShowModel;
    import utils.CustomList;

    public class MainController {



        private ShowController showController;
        private PerfomanceController perfomanceController;

        public void setShowController(ShowController showController) {
            this.showController = showController;

        }


        public void setPerfomanceController(PerfomanceController performanceController) {
            this.perfomanceController = performanceController;

        }
        @FXML
        private TabPane mainTabPane;  // This should refer to your TabPane in the FXML file
        @FXML
        private Tab bookingTab;       // This should refer to your booking tab

        public void switchToBookingTab() {
            if (mainTabPane != null) {
                mainTabPane.getSelectionModel().select(bookingTab);  // Selects the booking tab
            } else {
                System.out.println("TabPane is null, check FXML linkage.");
            }
        }
        private BookingController bookingController;

        public void setBookingController(BookingController bookingController) {
            this.bookingController = bookingController;
        }

        public BookingController getBookingController() {
            return bookingController;
        }



    }
