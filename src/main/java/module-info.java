module com.example.theatrebookingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.theatrebookingsystem to javafx.fxml;
    exports com.example.theatrebookingsystem;
}