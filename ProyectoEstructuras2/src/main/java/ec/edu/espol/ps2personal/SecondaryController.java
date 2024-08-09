package ec.edu.espol.ps2personal;

import classes.BTree;
import classes.LecturaArchivos;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class SecondaryController extends Stage {

    @FXML
    private Button secondaryButton;
    @FXML
    private Button pregBtn;
    @FXML
    private Button respBtn;
    
    private BTree abdJuego; 

    @FXML
    private void switchToGame() throws IOException {
        boolean hasTwoInput = !pregBtn.getText().equals("...") && !respBtn.getText().equals("...");
        if(hasTwoInput){
            LecturaArchivos lectAr = new LecturaArchivos(pregBtn.getText(),respBtn.getText());
            App.arbolJuego = lectAr.buildTree();
            App.setRoot("Juego");
        }
    }
    
    @FXML
    private void selectPreg(ActionEvent event) {
        FileChooser fileChooser = createDialog();

        File selectedFile = fileChooser.showOpenDialog(this);
        if(selectedFile!=null){
            String text=selectedFile.getName().split("\\.")[0];
            pregBtn.setText(text);
            Path destino = Path.of("src\\main\\resources\\Texto\\"+ text +".txt");  //Referencia relativa
            Path origen = Path.of(selectedFile.getPath()); //Referencia absoluta
            try {
                Files.copy(origen, destino.toAbsolutePath(),StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private FileChooser createDialog(){
        ExtensionFilter ext1 = new ExtensionFilter("Text Files", "*.txt");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(ext1);
        fileChooser.setTitle("Seleccionar archivo");
        return fileChooser;
    }
    
    @FXML
    private void selectAns(ActionEvent event) {
        FileChooser fileChooser = createDialog();
        File selectedFile = fileChooser.showOpenDialog(this);
        if(selectedFile!=null){
            String text=selectedFile.getName().split("\\.")[0];
            respBtn.setText(text);
            Path destino = Path.of("src\\main\\resources\\Texto\\"+ text +".txt");  //Referencia relativa
            Path origen = Path.of(selectedFile.getPath()); //Referencia absoluta
            try {
                Files.copy(origen, destino.toAbsolutePath(),StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}