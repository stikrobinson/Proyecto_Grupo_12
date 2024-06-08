package com.mycompany.proyectoestructuras;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

import java.io.IOException;

import Estructuras.*;
import ClasesProyect.*;

public class App extends Application {

    private static Scene MENU;
    public static Stage STAGE;
    public static HashMap<String, User> USUARIOS;
    public static User USUARIOACTUAL;
    public static ArrayList<Vehiculo> VEHICULOS;

    @Override
    public void start(Stage s) throws IOException {
        STAGE = s;
        USUARIOS = User.cargarUsuarios();
        VEHICULOS = Vehiculo.cargarVehiculos();
        Escenas escenas = new Escenas();
        MENU = escenas.MENU;
        STAGE.setScene(escenas.INICIOSESION);
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