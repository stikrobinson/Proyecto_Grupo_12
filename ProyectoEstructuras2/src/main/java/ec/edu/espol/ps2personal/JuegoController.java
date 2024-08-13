/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ps2personal;

import classes.BTree;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author alexo
 */
public class JuegoController implements Initializable {

    @FXML
    private Text PreguntaTXT;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    
    private BTree animalTree;
    private static int numPreguntas;
    static ArrayList<String> responses;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        responses = new ArrayList<>();
        animalTree = App.arbolJuego;
        try {
            auxLoad();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PreguntaTXT.setText(animalTree.getRaiz());
        //System.out.println(animalTree.recorridoPorAnchura());
        System.out.println(animalTree.getLeaves());
    }    

    @FXML
    private void yesChoce(ActionEvent event) throws IOException {
        animalTree = animalTree.getIzq();
        numPreguntas--;
        auxLoad();
        PreguntaTXT.setText(animalTree.getRaiz());
    }

    @FXML
    private void noChoice(ActionEvent event) throws IOException {
        animalTree = animalTree.getDer();
        numPreguntas--;
        auxLoad();
        PreguntaTXT.setText(animalTree.getRaiz());
    }
    
    
    private void auxLoad() throws IOException{
        responses = animalTree.getLeaves();
        if(responses.size()==1 || numPreguntas<=0) App.setRoot("finalScreen");
        System.out.println(responses);
    }
    
    public static void setNumPreguntas(int n){
        numPreguntas = n;
    }
    
}
