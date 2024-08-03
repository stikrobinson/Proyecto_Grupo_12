package ec.edu.espol.proyectoestructuras2;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}