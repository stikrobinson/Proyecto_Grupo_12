/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ps2personal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author alexo
 */
public class NumberSelectController implements Initializable {

    @FXML
    Text amountQuest;
    @FXML
    Button plusBtn;
    @FXML
    Button minusBTN;
    @FXML
    Button btnEmpezar;
    @FXML
    private FlowPane fpOpciones;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        amountQuest.setText(App.arbolJuego.altura()-1+"");
        for (String str : App.arbolJuego.getLeaves()){
            VBox bv = new VBox();
            Text txt = new Text(str);
            ImageView imv = new ImageView();
            try{
                imv.setImage(new Image("Images\\"+str+".jpg"));
                imv.setFitWidth(50);
                imv.setPreserveRatio(true);
            }catch(Exception e){
              imv.setImage(new Image("Images\\"+"default"+".jpg"));
              imv.setFitWidth(50);
              imv.setPreserveRatio(true);
            }
            bv.getChildren().addAll(txt,imv);
            fpOpciones.getChildren().add(bv);
        }
            
    }
    
    @FXML
    private void empezar() throws IOException{
        JuegoController.setNumPreguntas(Integer.valueOf(amountQuest.getText()));
        App.setRoot("Juego");
    }
    
    @FXML
    private void plusClick(){
        if(Integer.valueOf(amountQuest.getText())<App.arbolJuego.altura()-1){
            amountQuest.setText(""+(Integer.valueOf(amountQuest.getText())+1));
        }
    } 
    
    @FXML
    private void minusClick(){
        if(Integer.valueOf(amountQuest.getText())>1){
            amountQuest.setText(""+(Integer.valueOf(amountQuest.getText())-1));
        }
    } 
    
}
