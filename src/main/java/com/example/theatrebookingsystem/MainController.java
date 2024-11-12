    package com.example.theatrebookingsystem;

    import javafx.fxml.FXML;
    import javafx.scene.control.TabPane;
    import theatrebookingsystem.model.ShowModel;
    import utils.CustomList;

    public class MainController {

        @FXML
        private TabPane tabPane;

        private ShowController showController;
        private PerfomanceController perfomanceController;

        public void setShowController(ShowController showController) {
            this.showController = showController;

        }

        /**
         * Sets the PerformanceController reference and checks if both controllers are initialized.
         */
        public void setPerfomanceController(PerfomanceController performanceController) {
            this.perfomanceController = performanceController;

        }


    }
