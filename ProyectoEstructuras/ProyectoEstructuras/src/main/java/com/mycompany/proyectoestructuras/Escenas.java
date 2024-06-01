package com.mycompany.proyectoestructuras;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.geometry.Pos;

public class Escenas extends Stage {
    public Scene VER, CREAR;
    
    public Escenas(){
        //VER
        Button salirVER = new Button("Salir"); 
        salirVER.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
        salirVER.setOnMouseClicked(e -> {
            App.menu();
        });
        VBox vbSalirVER = new VBox(); vbSalirVER.setAlignment(Pos.CENTER); vbSalirVER.getChildren().add(salirVER);
        
        Label lblTitulo = new Label("Lista de Vehiculos");
        lblTitulo.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        lblTitulo.setAlignment(Pos.CENTER);
        VBox vbTtVER = new VBox(); vbTtVER.setAlignment(Pos.CENTER);
        vbTtVER.getChildren().add(lblTitulo);
        
        BorderPane rootVER = new BorderPane();
        rootVER.setTop(vbTtVER);
        rootVER.setBottom(vbSalirVER);
        VER = new Scene(rootVER, 800, 600);

        //CREAR
        Label lblTtCREAR = new Label("Nuevo Vehiculo");
        lblTtCREAR.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        lblTtCREAR.setAlignment(Pos.CENTER);
        VBox vbTtCREAR = new VBox(); vbTtCREAR.setAlignment(Pos.CENTER);
        vbTtCREAR.getChildren().add(lblTtCREAR);
        
        Button salirCREAR = new Button("Salir"); 
        salirCREAR.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
        salirCREAR.setOnMouseClicked(e -> {
            App.menu();
        });
        VBox vbSalirCREAR = new VBox(); vbSalirCREAR.setAlignment(Pos.CENTER); vbSalirCREAR.getChildren().add(salirCREAR);
        
        BorderPane rootCREAR = new BorderPane();
        rootCREAR.setTop(vbTtCREAR);
        rootCREAR.setBottom(vbSalirCREAR);
        CREAR = new Scene(rootCREAR, 800, 600);
    }
    
}
