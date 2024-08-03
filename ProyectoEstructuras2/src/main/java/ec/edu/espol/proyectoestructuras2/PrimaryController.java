package ec.edu.espol.proyectoestructuras2;

import ec.edu.espol.clases.*;
import java.io.IOException;
import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrimaryController {
    
    private VBox vBox;
    
    public void initialize(){
        String ruta = "https://www.infobae.com/new-resizer/aWPzqVhovehmcFmyHjiyMEDimsM=/992x1323/filters:format(webp):quality(85)/cloudfront-us-east-1.images.arcpublishing.com/infobae/5OL72B2YLNDWBKBEOWVCDELLPM.jpg";
        Image skibidi = new Image(ruta, 1000, 0, false, false);
        ImageView iv = new ImageView(skibidi);
        vBox.getChildren().add(iv);
    }
    
    
}
