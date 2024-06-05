package com.mycompany.proyectoestructuras;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.geometry.Pos;

import ClasesProyect.*;
import Estructuras.CircularLinkedList;
import java.util.PriorityQueue;

public class Escenas extends Stage {
    public Scene INICIOSESION, MENU, VER, CREAR, MISVEHICULOS;
    
    public Escenas(){
        //INICIOSESION
        BorderPane rootSESION = new BorderPane();
        
        Label lblUsuarioActual = new Label("a");//Esto se muestra en el menu
        
        Label lblTtSESION = new Label("Inicio de Sesion");
        lblTtSESION.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        VBox vbTtSESION = new VBox();vbTtSESION.setAlignment(Pos.CENTER);
        vbTtSESION.getChildren().add(lblTtSESION);
        rootSESION.setTop(vbTtSESION);
        
        VBox vbCampos = new VBox(); vbCampos.setAlignment(Pos.CENTER); vbCampos.setSpacing(20);
        TextField tfUsuario = new TextField(); tfUsuario.setPromptText("Usuario"); tfUsuario.setMaxWidth(600);
        tfUsuario.setStyle("-fx-background-color: #b0faff; -fx-text-fill: #000000; -fx-font-size: 40px;");
        TextField tfContrasena = new TextField(); tfContrasena.setPromptText("Contrasena"); tfContrasena.setMaxWidth(600);
        tfContrasena.setStyle("-fx-background-color: #b0faff; -fx-text-fill: #000000; -fx-font-size: 40px;");
        
        Button btnINICIAR = new Button("Iniciar Sesion");
        btnINICIAR.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 30;");
        btnINICIAR.setOnMouseClicked(e -> {
            User usuario = App.USUARIOS.get(tfUsuario.getText());
            if(usuario != null){
                if ( usuario.getContrasena().equals(tfContrasena.getText()) ){
                    App.STAGE.setScene(MENU);lblUsuarioActual.setText(usuario.getNombre());App.USUARIOACTUAL = usuario;
                }else{
                    vbCampos.getChildren().clear();
                    tfUsuario.clear(); tfContrasena.clear();
                    Label lblErr = new Label("Contraseña Incorrecta >:(");
                    lblErr.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 20;");
                    vbCampos.getChildren().addAll(tfUsuario, tfContrasena, btnINICIAR, lblErr);
                }               
            }else{
                vbCampos.getChildren().clear();
                tfUsuario.clear(); tfContrasena.clear();
                Label lblErr = new Label("No hay registro de ese usuario :(");
                lblErr.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 20;");
                vbCampos.getChildren().addAll(tfUsuario, tfContrasena, btnINICIAR, lblErr);
            }
        });
        
        vbCampos.getChildren().addAll(tfUsuario, tfContrasena, btnINICIAR);
        rootSESION.setCenter(vbCampos);
        
        Label usuarioEjemplo = new Label("""
                                         Usuario de ejemplo:
                                         Usuario: a. Contrasena: b; Nombre: c;
                                         """);
        usuarioEjemplo.setStyle("-fx-text-fill: #c2c2c2; -fx-font-size: 20px;");
        VBox vbUsuarioEjemplo = new VBox(); vbUsuarioEjemplo.setAlignment(Pos.CENTER_RIGHT);
        vbUsuarioEjemplo.getChildren().add(usuarioEjemplo);
        rootSESION.setBottom(vbUsuarioEjemplo);
        
        INICIOSESION = new Scene(rootSESION, 800, 600);
        
        //MENU
        BorderPane rootMENU = new BorderPane();
        
        Label lblTtMENU = new Label("Administracion de Vehiculos");
        lblTtMENU.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        lblTtMENU.setAlignment(Pos.CENTER);
        VBox vbTtMENU = new VBox(); vbTtMENU.setAlignment(Pos.CENTER);
        vbTtMENU.getChildren().add(lblTtMENU);
        
        Button btnVER = new Button("Vehiculos Disponibles");
        btnVER.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 40px;");
        btnVER.setOnMouseClicked(e -> {
            App.STAGE.setScene(VER);        
            });
        
        Button btnCREAR = new Button("Poner en Venta un Vehiculo");
        btnCREAR.setStyle("-fx-background-color: #5167b0; -fx-text-fill: #ffffff; -fx-font-size: 40px;");
        btnCREAR.setOnMouseClicked(e -> {
            App.STAGE.setScene(CREAR);        
        });
        
        Button btnMISV = new Button("Mis Vehiculos en Venta");
        btnMISV.setStyle("-fx-background-color: #72b083; -fx-text-fill: #ffffff; -fx-font-size: 40px;");
        btnMISV.setOnMouseClicked(e -> {
            App.STAGE.setScene(MISVEHICULOS);        
        });
        
        VBox vbPrincipal = new VBox();vbPrincipal.setAlignment(Pos.CENTER);
        vbPrincipal.setSpacing(40);
        vbPrincipal.getChildren().addAll(btnVER, btnCREAR, btnMISV);
        
        Button btnCerrar = new Button("Cerrar Sesion");
        btnCerrar.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
        btnCerrar.setOnMouseClicked(e -> {
            tfUsuario.clear(); tfContrasena.clear();
            vbCampos.getChildren().clear();
            vbCampos.getChildren().addAll(tfUsuario, tfContrasena, btnINICIAR);
            App.STAGE.setScene(INICIOSESION);
        });
        VBox vbCerrar = new VBox(); vbCerrar.setAlignment(Pos.CENTER); vbCerrar.getChildren().addAll(lblUsuarioActual, btnCerrar);
        
        lblUsuarioActual.setStyle("-fx-text-fill: #000000; -fx-font-size: 30;");
        lblUsuarioActual.setAlignment(Pos.CENTER);
       
        rootMENU.setCenter(vbPrincipal);
        rootMENU.setTop(vbTtMENU);
        rootMENU.setBottom(vbCerrar);
        MENU = new Scene(rootMENU, 800, 600);
        
        //VER
        Button salirVER = new Button("Salir"); 
        salirVER.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
        salirVER.setOnMouseClicked(e -> {
            App.menu();
        });
        VBox vbSalirVER = new VBox(); vbSalirVER.setAlignment(Pos.CENTER); vbSalirVER.getChildren().add(salirVER);
        
        Label lblTitulo = new Label("Vehiculos Disponibles");
        lblTitulo.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        lblTitulo.setAlignment(Pos.CENTER);
        VBox vbTtVER = new VBox(); vbTtVER.setAlignment(Pos.CENTER);
        vbTtVER.getChildren().add(lblTitulo);        
        
        //Creamos una cola de prioridad que tendra el orden de los vehiculos por año por defecto
        PriorityQueue<Vehiculo> vehiculosPorAnio = new PriorityQueue<>( (Vehiculo v1, Vehiculo v2) -> {
            return v2.getAnio() - v1.getAnio();
        });
        //Añadimos todos los vehiculos disponibles a la cola de prioridad
        for( Vehiculo v : App.VEHICULOS ){
            vehiculosPorAnio.offer(v);
        }
        //Ahora creamos una lista circular doblemente enlazada para mostrar los vehículos
        CircularLinkedList<Vehiculo> vehiculosMostrados = new CircularLinkedList<>();
        //Añadimos los vehiculos ya ordenados de la cola de prioridad a la nueva lista
        while ( !vehiculosPorAnio.isEmpty() ){
            vehiculosMostrados.add(vehiculosPorAnio.poll());
        }
        
        //Mostramos el cursor de la lista
        VBox vbVehiculoCursor = new VBox(); vbVehiculoCursor.setAlignment(Pos.CENTER);
        mostrarVehiculo(vbVehiculoCursor, vehiculosMostrados);
        //Colocamos los botones siguiente y anterior
        Button btnSiguiente = new Button("->"); btnSiguiente.setStyle("-fx-background-color: #abffa8; -fx-text-fill: #000000; -fx-font-size: 50px;");       
        btnSiguiente.setOnAction( e -> {
            vehiculosMostrados.cursorSiguiente();
            mostrarVehiculo(vbVehiculoCursor, vehiculosMostrados);
        });
        VBox vbSiguiente = new VBox(); vbSiguiente.setAlignment(Pos.CENTER);
        vbSiguiente.getChildren().add(btnSiguiente);
        
        Button btnAnterior = new Button("<-"); btnAnterior.setStyle("-fx-background-color: #ffa8b5; -fx-text-fill: #000000; -fx-font-size: 50px;");       
        btnAnterior.setOnAction( e -> {
            vehiculosMostrados.cursorAnterior();
            mostrarVehiculo(vbVehiculoCursor, vehiculosMostrados);
        });
        VBox vbAnterior = new VBox(); vbAnterior.setAlignment(Pos.CENTER);
        vbAnterior.getChildren().add(btnAnterior);
        
        BorderPane rootVER = new BorderPane();
        rootVER.setTop(vbTtVER);
        rootVER.setCenter(vbVehiculoCursor);
        rootVER.setLeft(vbAnterior);
        rootVER.setRight(vbSiguiente);
        rootVER.setBottom(vbSalirVER);
        VER = new Scene(rootVER, 800, 600);

        //CREAR
        Label lblTtCREAR = new Label("Vender Vehiculo");
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
        
        //MISVEHICULOS  
        BorderPane rootMISV = new BorderPane();
        
        Label lblTtMISV = new Label("Mis Vehiculos");
        lblTtMISV.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        lblTtMISV.setAlignment(Pos.CENTER);
        VBox vbTtMISV = new VBox(); vbTtMISV.setAlignment(Pos.CENTER);
        vbTtMISV.getChildren().add(lblTtMISV);
        
        rootMISV.setTop(vbTtMISV);
        
        Button salirMISV = new Button("Salir"); 
        salirMISV.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
        salirMISV.setOnMouseClicked(e -> {
            App.menu();
        });
        VBox vbSalirMISV = new VBox(); vbSalirMISV.setAlignment(Pos.CENTER); vbSalirMISV.getChildren().add(salirMISV);
        rootMISV.setBottom(vbSalirMISV);
        
        MISVEHICULOS = new Scene(rootMISV, 800, 600);
    }
    private static void mostrarVehiculo(VBox vbVehiculo, CircularLinkedList<Vehiculo> vehiculos){
        vbVehiculo.getChildren().clear();
        //String id, double precio, String marca, String modelo, String foto, int anio, int kilometraje, String motor, String trasmision, String peso, String ubiActual, String histAccident, String histService
        Vehiculo v = vehiculos.getCursor();
        Label lblMarca = new Label("Marca: " + v.getMarca()); lblMarca.setStyle("-fx-text-fill: #000000; -fx-font-size: 40;");
        Label lblModelo = new Label("Modelo: " + v.getModelo()); lblModelo.setStyle("-fx-text-fill: #000000; -fx-font-size: 40;");
        Label lblAnio = new Label("Año: " + v.getAnio()); lblAnio.setStyle("-fx-text-fill: #000000; -fx-font-size: 20;");
        Label lblKilometraje = new Label("Kilometraje: " + v.getKilometraje() + "km"); lblKilometraje.setStyle("-fx-text-fill: #000000; -fx-font-size: 20;");
        Label lblPrecio = new Label("Precio: $" + v.getPrecio()); lblPrecio.setStyle("-fx-text-fill: #73ad71; -fx-font-size: 40;");
        
        vbVehiculo.getChildren().addAll(lblMarca, lblModelo, lblAnio, lblKilometraje, lblPrecio);
    }
}
