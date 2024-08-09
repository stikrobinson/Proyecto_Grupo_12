module ec.edu.espol.ps2personal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.ps2personal to javafx.fxml;
    exports ec.edu.espol.ps2personal;
}
