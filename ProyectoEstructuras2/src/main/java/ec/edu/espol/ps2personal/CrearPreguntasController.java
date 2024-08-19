/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ps2personal;


import classes.LecturaArchivos;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import static javafx.geometry.Pos.TOP_CENTER;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author steve
 */
public class CrearPreguntasController{
    
    @FXML
    TextField firstTextField;
    @FXML
    VBox vbox;
    int num = 1;
    
    @FXML
    public void anadir(){
       HBox ultimoHB = (HBox) vbox.getChildren().get(num);
       TextField ultimoTF = (TextField) ultimoHB.getChildren().get(1);
       if(!ultimoTF.getText().equals("")){
        num++;
        HBox hb = new HBox();
        hb.setPrefHeight(65); hb.setPrefWidth(331); hb.setAlignment(TOP_CENTER); hb.setSpacing(20);
        Label lb = new Label(num+".");
        lb.setStyle("-fx-font-size: 20px;");
        TextField tf = new TextField();
        tf.setStyle("-fx-font-size: 30px;"); tf.setPrefWidth(400.0); tf.setPrefHeight(25.0);
        hb.getChildren().addAll(lb,tf);
        vbox.getChildren().add(hb);
       }
    }
    
    @FXML
    public void switchToRespuestas() throws IOException{
        
        for(int i = 1; i<vbox.getChildren().size(); i++){
                HBox hb = (HBox) vbox.getChildren().get(i);
                TextField tf = (TextField) hb.getChildren().get(1);
                if(tf.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Faltan preguntas por añadir");
                alert.setContentText("Incluya las preguntas que faltan");
                alert.showAndWait();
                return;
                }
        }
        
        
        String nombreArchivo = ".\\src\\main\\resources\\Texto\\preguntasPersonalizadas.txt";

        try {
            FileWriter escritor = new FileWriter(nombreArchivo);

            for(int i = 1; i<vbox.getChildren().size(); i++){
                HBox hb = (HBox) vbox.getChildren().get(i);
                TextField tf = (TextField) hb.getChildren().get(1);
                if(i==vbox.getChildren().size()-1){
                    escritor.write(tf.getText());
                }else{
                    escritor.write(tf.getText()+"\n");
                }
                
            }

            escritor.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo.");
            e.printStackTrace();
        }
        
        CrearRespuestasController.numPreguntas = num;
        App.setRoot("crearRespuestas");
    }
    
}
