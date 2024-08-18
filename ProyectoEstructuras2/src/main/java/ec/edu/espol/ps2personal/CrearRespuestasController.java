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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author steve
 */
public class CrearRespuestasController implements Initializable{
    static int numPreguntas;
    @FXML
    VBox vbox;
    int num = 1;
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        HBox hb = (HBox) vbox.getChildren().get(1);
        hb.getChildren().add(crearComboBox());
    }
    
    private HBox crearComboBox(){
        HBox hb = new HBox();
        for(int i=0; i<numPreguntas;i++){
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll("sí","no");
            hb.getChildren().add(comboBox);
        }
        return hb;
    }
    
    @FXML
    public void anadir(){
       if(num+1>Math.pow(2, numPreguntas)){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("Muchas respuestas");
           alert.setContentText("Más respuestas de las que se pueden manejar");
           alert.showAndWait();
           return;
       }
       HBox ultimoHB = (HBox) vbox.getChildren().get(num);
       TextField ultimoTF = (TextField) ultimoHB.getChildren().get(1);
       if(!ultimoTF.getText().equals("") && checkeo(num)){
        num++;
        HBox hb = new HBox();
        Label lb = new Label(num+".");
        TextField tf = new TextField();
        hb.getChildren().addAll(lb,tf,crearComboBox());
        vbox.getChildren().add(hb);
       }
    }
    
    private boolean checkeo(int num){
          HBox hb = (HBox) vbox.getChildren().get(num);
          HBox comboBoxes = (HBox) hb.getChildren().get(2);
          for(int i = 1; i<comboBoxes.getChildren().size(); i++){
                ComboBox cb = (ComboBox) comboBoxes.getChildren().get(1);
                if(cb.getValue()==null){
                    return false;
                }
          }
          return true;
    }
    
    @FXML 
    public void empezar() throws IOException{
        for(int i = 1; i<vbox.getChildren().size(); i++){
            HBox hb = (HBox) vbox.getChildren().get(i);
            TextField tf = (TextField) hb.getChildren().get(1);
            if(tf.getText().equals("") || !checkeo(num)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Faltan preguntas u opciones por añadir");
                alert.setContentText("Incluya las preguntas u opciones que faltan");
                alert.showAndWait();
                return;
            }
        }
        
        String nombreArchivo = ".\\src\\main\\resources\\Texto\\respuestasPersonalizadas.txt";

        try {
            FileWriter escritor = new FileWriter(nombreArchivo);

            for(int i = 1; i<vbox.getChildren().size(); i++){
                HBox hb = (HBox) vbox.getChildren().get(i);
                TextField tf = (TextField) hb.getChildren().get(1);
                String texto = tf.getText();
                HBox comboBoxes = (HBox) hb.getChildren().get(2);
                for(int j=0; j<comboBoxes.getChildren().size(); j++){
                    ComboBox cb = (ComboBox) comboBoxes.getChildren().get(j);
                    texto = texto + " " + cb.getValue();
                }
                if(i==vbox.getChildren().size()-1){
                    escritor.write(texto);
                }else{
                    escritor.write(texto+"\n");
                }
                
            }

            escritor.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo.");
            e.printStackTrace();
        }
        
        LecturaArchivos lectAr = new LecturaArchivos("preguntasPersonalizadas","respuestasPersonalizadas");
        App.arbolJuego = lectAr.buildTree();
        App.setRoot("numberSelect");
    }
    
    
}
