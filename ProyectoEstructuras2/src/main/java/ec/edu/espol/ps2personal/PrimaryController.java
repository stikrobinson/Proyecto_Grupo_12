package ec.edu.espol.ps2personal;

import classes.LecturaArchivos;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private Button playDefaultBtn;
    @FXML
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void playDefault(ActionEvent event) throws IOException {
        LecturaArchivos lectAr = new LecturaArchivos("defaultQuest","defaultAns");
        App.arbolJuego = lectAr.buildTree();
        App.setRoot("numberSelect"); //Cambiar a numberSelect
    }
}
