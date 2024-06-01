package com.mycompany.proyectoestructuras;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.geometry.Pos;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene MENU;
    public static Stage STAGE;

    @Override
    public void start(Stage s) throws IOException {
        STAGE = s;
        BorderPane rootPrincipal = new BorderPane();
        Escenas escenas = new Escenas();
        
        Label lblTitulo = new Label("Administracion de Vehiculos");
        lblTitulo.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        lblTitulo.setAlignment(Pos.CENTER);
        VBox vbTitulo = new VBox(); vbTitulo.setAlignment(Pos.CENTER);
        vbTitulo.getChildren().add(lblTitulo);
        
        Button btnVER = new Button("Visualizar Vehiculos");
        btnVER.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 40px;");
        btnVER.setOnMouseClicked(e -> {
            STAGE.setScene(escenas.VER);        
            });
        
        Button btNCREAR = new Button("Crear Nuevo Vehiculo");
        btNCREAR.setStyle("-fx-background-color: #5167b0; -fx-text-fill: #ffffff; -fx-font-size: 40px;");
        btNCREAR.setOnMouseClicked(e -> {
            STAGE.setScene(escenas.CREAR);        
            });
        
        VBox vbPrincipal = new VBox();vbPrincipal.setAlignment(Pos.CENTER);
        vbPrincipal.setSpacing(40);
        vbPrincipal.getChildren().addAll(btnVER, btNCREAR);
       
        rootPrincipal.setCenter(vbPrincipal);
        rootPrincipal.setTop(vbTitulo);
        MENU = new Scene(rootPrincipal, 800, 600);
        STAGE.setScene(MENU);
        STAGE.show();
    }

    static void setRoot(String fxml) throws IOException {
        MENU.setRoot(loadFXML(fxml));
    }
    
    public static void menu(){
        STAGE.setScene(MENU);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}