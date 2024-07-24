module ec.edu.espol.proyectoestructuras2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyectoestructuras2 to javafx.fxml;
    exports ec.edu.espol.proyectoestructuras2;
}
