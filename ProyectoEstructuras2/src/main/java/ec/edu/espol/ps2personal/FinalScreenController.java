/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ps2personal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author alexo
 */
public class FinalScreenController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblResultado;
    private int index = 0;
    @FXML
    private ImageView imgAnimal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(JuegoController.responses.isEmpty()){
            lblTitulo.setText("No se encontró a ningún animal con las características que buscaba");
            lblResultado.setText("");
        }else if(JuegoController.responses.size()==1){
            lblTitulo.setText("El animal que usted pensó es:");
            lblResultado.setText(JuegoController.responses.get(0));
            cargarImg();
        }else{
          lblTitulo.setText("Entre los animales que usted pudo haber pensado son:");
          lblResultado.setText(JuegoController.responses.get(0));
          Button btnDer = new Button("->");
          btnDer.setOnAction((e)->{
            if(index<JuegoController.responses.size()-1){
                index++;
            }else if(index==JuegoController.responses.size()-1){
                index = 0;
            }
            lblResultado.setText(JuegoController.responses.get(index));
            cargarImg();
          });
          Button btnIzq = new Button("<-");
          btnIzq.setOnAction((e)->{
            if(index>0){
                index--;
            }else if(index==0){
                index = JuegoController.responses.size()-1;
            }                    
            lblResultado.setText(JuegoController.responses.get(index));
            cargarImg();
          });
          borderPane.setLeft(btnIzq);  
          borderPane.setRight(btnDer);  
          cargarImg();
        }
    }    

    @FXML
    private void playAgain(ActionEvent event) throws IOException {
        App.setRoot("NumberSelect");
    }
    private void cargarImg(){
        try{
            imgAnimal.setImage(new Image("Images\\"+lblResultado.getText()+".jpg"));
            imgAnimal.setFitWidth(800);
            imgAnimal.setPreserveRatio(true);
        }catch(Exception e){
          imgAnimal.setImage(new Image("Images\\"+"default"+".jpg"));
          imgAnimal.setFitWidth(800);
          imgAnimal.setPreserveRatio(true);
        }
    }

}
