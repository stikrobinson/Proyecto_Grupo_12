package com.mycompany.proyectoestructuras;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

import javafx.geometry.Pos;

import ClasesProyect.*;
import Estructuras.CircularLinkedList;
import Estructuras.ArrayList;
import java.util.PriorityQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class Escenas extends Stage {
    public Scene INICIOSESION, MENU, VER, CREAR, MISVEHICULOS, REGISTRO;
    private Stage ACCIDENTES, SERVICIOS;
    private ArrayList<String> telefonos = new ArrayList<>();
    
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
        
        Button btnRegistro = new Button("Crear Cuenta");
        btnRegistro.setStyle("-fx-background-color: #5353ec; -fx-text-fill: #ffffff; -fx-font-size: 30;");
        btnRegistro.setOnMouseClicked(e -> {
            App.STAGE.setScene(REGISTRO);
            telefonos.clear();
        });
        
        
        HBox vbRegistro = new HBox(); vbRegistro.setAlignment(Pos.CENTER); vbRegistro.setSpacing(20);
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
                    vbCampos.getChildren().addAll(tfUsuario, tfContrasena, vbRegistro, lblErr);
                }               
            }else{
                vbCampos.getChildren().clear();
                tfUsuario.clear(); tfContrasena.clear();
                Label lblErr = new Label("No hay registro de ese usuario :(");
                lblErr.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 20;");
                vbCampos.getChildren().addAll(tfUsuario, tfContrasena, vbRegistro, lblErr);
            }
        });
        
        vbRegistro.getChildren().addAll(btnINICIAR,btnRegistro);
        vbCampos.getChildren().addAll(tfUsuario, tfContrasena, vbRegistro);
        rootSESION.setCenter(vbCampos);
        
        Label usuarioEjemplo = new Label("""
                                         Usuario de ejemplo:
                                         Usuario: evaluador. Contrasena: martes11; Nombre: Luis Gabriel Canarte Lucio;
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
        btnVER.setStyle("-fx-background-color: #f5ff9e; -fx-text-fill: #000000; -fx-font-size: 40px;");
        btnVER.setOnMouseClicked(e -> {
            App.STAGE.setScene(VER);
            });
        
        //Creamos un contenedor para la creacion del vehiculo nuevo
        VBox vbNuevoVehiculo = new VBox(); vbNuevoVehiculo.setSpacing(15); vbNuevoVehiculo.setAlignment(Pos.CENTER);
        //Creamos las vegntanas para los historiales de accidentes y servicios, con sus respectivos contenedores y Labels
        BorderPane rootACCIDENTES = new BorderPane();
        Label lblTtACCIDENTES = new Label("Historial de Accidentes"); lblTtACCIDENTES.setStyle("-fx-text-fill: #000000; -fx-font-size: 40px;");
        VBox vbTtACCIDENTES = new VBox(); vbTtACCIDENTES.setAlignment(Pos.CENTER);
        vbTtACCIDENTES.getChildren().add(lblTtACCIDENTES);
        VBox vbACCIDENTES = new VBox(); vbACCIDENTES.setSpacing(20); vbACCIDENTES.setAlignment(Pos.CENTER);
                
        rootACCIDENTES.setCenter(vbACCIDENTES); rootACCIDENTES.setTop(vbTtACCIDENTES);
        ACCIDENTES = new Stage(); ACCIDENTES.setScene(new Scene(rootACCIDENTES, 600, 600));
        
        BorderPane rootSERVICIOS = new BorderPane();
        Label lblTtSERVICIOS = new Label("Historial de Servicios"); lblTtSERVICIOS.setStyle("-fx-text-fill: #000000; -fx-font-size: 50px;");
        VBox vbTtSERVICIOS = new VBox(); vbTtSERVICIOS.setAlignment(Pos.CENTER);
        vbTtSERVICIOS.getChildren().add(lblTtSERVICIOS);
        VBox vbSERVICIOS = new VBox(); vbSERVICIOS.setSpacing(20); vbSERVICIOS.setAlignment(Pos.CENTER);
        
        rootSERVICIOS.setTop(vbTtSERVICIOS); rootSERVICIOS.setCenter(vbSERVICIOS);
        SERVICIOS = new Stage(); SERVICIOS.setScene(new Scene(rootSERVICIOS, 600, 600));  
        
        Button btnCREAR = new Button("Poner en Venta un Vehiculo");
        btnCREAR.setStyle("-fx-background-color: #5353ec; -fx-text-fill: #ffffff; -fx-font-size: 40px;");
        btnCREAR.setOnMouseClicked(e -> {
            crearVehiculo(vbNuevoVehiculo, vbACCIDENTES, vbSERVICIOS);
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
            vbCampos.getChildren().addAll(tfUsuario, tfContrasena,vbRegistro);
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
        
        HBox hbFiltros = new HBox(); hbFiltros.setSpacing(20); hbFiltros.setAlignment(Pos.CENTER);
        
        vbTtVER.getChildren().add(lblTitulo);        
        
        //Creamos los ComboBox de los filtros y los ordenamientos
        ComboBox filtros = new ComboBox(); filtros.setMaxWidth(200);
        ObservableList<String> itemsFiltro = FXCollections.observableArrayList(
            "Carro", "Moto", "Camion", "Todos"
        );
        filtros.setItems(itemsFiltro);
        filtros.setValue("Todos");
        
        ComboBox ordenamientos = new ComboBox(); ordenamientos.setMaxWidth(10);
        ObservableList<String> itemsOrdenamiento = FXCollections.observableArrayList(
            "Año", "Marca y modelo(alfabetico)", "Precio", "Kilometraje", "Peso"
        );
        ordenamientos.setItems(itemsOrdenamiento);
        ordenamientos.setValue("Año");
        
        //Ahora creamos una lista circular doblemente enlazada para mostrar los vehículos
        CircularLinkedList<Vehiculo> vehiculosMostrados = new CircularLinkedList<>();
        
        //Creamos un ComboBox para el rango y dos TextFields
        Label lblRango = new Label("Rango: "); lblRango.setStyle("-fx-text-fill: #000000; -fx-font-size: 20;");
        
        ComboBox<String> rango = new ComboBox(); rango.setMaxWidth(200);
        ObservableList<String> itemsRango = FXCollections.observableArrayList(
            "Precio", "Kilometraje"
        );
        rango.setItems(itemsRango);
        
        TextField tfMax = new TextField(); tfMax.setPromptText("Max"); tfMax.setMaxWidth(100);
        tfMax.setStyle("-fx-background-color: #b0faff; -fx-text-fill: #000000; -fx-font-size: 15px;");
        
        TextField tfMin = new TextField(); tfMin.setPromptText("Min"); tfMin.setMaxWidth(100);
        tfMin.setStyle("-fx-background-color: #b0faff; -fx-text-fill: #000000; -fx-font-size: 15px;");
        
        //Creamos un contenedor para mostrar la informacion
        VBox vbVehiculoActualVER = new VBox(); vbVehiculoActualVER.setAlignment(Pos.CENTER);
        
        //Creamos un Boton que aplique los ordenamientos y filtros y llene la lista
        Button btnAplicarFiltro = new Button("Aplicar"); 
        btnAplicarFiltro.setOnAction( e -> {
            ordenarVehiculos(vehiculosMostrados, filtrarVehiculos(App.VEHICULOS, filtros, rango, tfMin, tfMax), ordenamientos);
            vehiculosMostrados.setListIterator();
            if ( !vehiculosMostrados.isEmpty() ) {
                System.out.println("si");
                mostrarVehiculo(vbVehiculoActualVER, vehiculosMostrados.getListIterator().next());
            }
            else {
                System.out.println("no");vbVehiculoActualVER.getChildren().clear();
            }
        }); 
        btnAplicarFiltro.fire();
        
        //Agregamos los ComboBox y el boton
        Label lblFiltro = new Label("Filtro: ");
        lblFiltro.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px;");
        Label lblOrdenamiento = new Label("Ordenamiento: ");
        lblOrdenamiento.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px;");
        
        hbFiltros.getChildren().addAll(lblFiltro, filtros, lblRango, rango, tfMax, tfMin, lblOrdenamiento, ordenamientos, btnAplicarFiltro);
        
        vbTtVER.getChildren().add(hbFiltros);
                
        //Colocamos los botones siguiente y anterior
        Button btnSiguienteVER = new Button("->"); btnSiguienteVER.setStyle("-fx-background-color: #abffa8; -fx-text-fill: #000000; -fx-font-size: 50px;");       
        btnSiguienteVER.setOnAction( e -> {
            if ( !vehiculosMostrados.isEmpty() ) mostrarVehiculo(vbVehiculoActualVER, vehiculosMostrados.getListIterator().next() );
        });
        VBox vbSiguiente = new VBox(); vbSiguiente.setAlignment(Pos.CENTER);
        vbSiguiente.getChildren().add(btnSiguienteVER);
        btnSiguienteVER.fire();
        
        Button btnAnteriorVER = new Button("<-"); btnAnteriorVER.setStyle("-fx-background-color: #ffa8b5; -fx-text-fill: #000000; -fx-font-size: 50px;");       
        btnAnteriorVER.setOnAction( e -> {
            if ( !vehiculosMostrados.isEmpty() ) mostrarVehiculo(vbVehiculoActualVER, vehiculosMostrados.getListIterator().previous() );
        });
        VBox vbAnterior = new VBox(); vbAnterior.setAlignment(Pos.CENTER);
        vbAnterior.getChildren().add(btnAnteriorVER);
        
        BorderPane rootVER = new BorderPane();
        rootVER.setTop(vbTtVER);
        rootVER.setCenter(vbVehiculoActualVER); 
        rootVER.setLeft(vbAnterior);
        rootVER.setRight(vbSiguiente);
        rootVER.setBottom(vbSalirVER);
        VER = new Scene(rootVER, 1000, 700);
        
        //CREAR
        Label lblTtCREAR = new Label("Vender Vehiculo");
        lblTtCREAR.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        lblTtCREAR.setAlignment(Pos.CENTER);
        VBox vbTtCREAR = new VBox(); vbTtCREAR.setAlignment(Pos.CENTER);
        vbTtCREAR.getChildren().add(lblTtCREAR);
        
        Button salirCREAR = new Button("Salir"); 
        salirCREAR.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
        salirCREAR.setOnMouseClicked(e -> {
            App.menu(); ACCIDENTES.close(); SERVICIOS.close();
        });
        VBox vbSalirCREAR = new VBox(); vbSalirCREAR.setAlignment(Pos.CENTER); vbSalirCREAR.getChildren().add(salirCREAR);
        
        BorderPane rootCREAR = new BorderPane();
        rootCREAR.setTop(vbTtCREAR);
        rootCREAR.setCenter(vbNuevoVehiculo);
        rootCREAR.setBottom(vbSalirCREAR);
        CREAR = new Scene(rootCREAR, 1100, 700);
        
        //MISVEHICULOS  
        BorderPane rootVEROwn = new BorderPane();

        Label lblTituloOwn = new Label("Mis Vehiculos");
        lblTituloOwn.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        lblTituloOwn.setAlignment(Pos.CENTER);
        VBox vbTtVEROwn = new VBox(); vbTtVEROwn.setAlignment(Pos.CENTER);

        vbTtVEROwn.getChildren().add(lblTituloOwn);        

        //Ahora creamos una lista circular doblemente enlazada para mostrar los vehículos
        CircularLinkedList<Vehiculo> vehiculosMostradosOwn = new CircularLinkedList<>();

        //Creamos un contenedor para mostrar la informacion
        VBox vbVehiculoActualVEROwn = new VBox(); vbVehiculoActualVEROwn.setAlignment(Pos.CENTER);

        rootVEROwn.setTop(vbTtVEROwn);
        rootVEROwn.setCenter(vbVehiculoActualVEROwn); 
        
        Button salirMISV = new Button("Salir"); 
        salirMISV.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 20px;");
        salirMISV.setOnMouseClicked(e -> {
            App.menu();
        });
        VBox vbSalirMISV = new VBox(); vbSalirMISV.setAlignment(Pos.CENTER); vbSalirMISV.getChildren().add(salirMISV);
        rootVEROwn.setBottom(vbSalirMISV);

        MISVEHICULOS = new Scene(rootVEROwn, 800, 600);
        
        //REGISTRO
        BorderPane rootRegistro = new BorderPane();
        
        Label tituloRegistro = new Label("Crea tu cuenta");
        tituloRegistro.setStyle("-fx-text-fill: #000000; -fx-font-size: 60;");
        tituloRegistro.setAlignment(Pos.CENTER);
        
        VBox vbTituloRegistro = new VBox(); vbTituloRegistro.setAlignment(Pos.CENTER);
        vbTituloRegistro.getChildren().add(tituloRegistro);
        
        rootRegistro.setTop(vbTituloRegistro);
        
        VBox vbCamposRegistros = new VBox(); vbCamposRegistros.setAlignment(Pos.CENTER); vbCamposRegistros.setSpacing(20);
        TextField tfUsuarioRegistro = new TextField(); tfUsuarioRegistro.setPromptText("Usuario"); tfUsuarioRegistro.setMaxWidth(600);
        tfUsuarioRegistro.setStyle("-fx-background-color: #b0faff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        TextField tfNombreRegistro = new TextField(); tfNombreRegistro.setPromptText("Nombre"); tfNombreRegistro.setMaxWidth(600);
        tfNombreRegistro.setStyle("-fx-background-color: #b0faff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        TextField tfContrasenaRegistro = new TextField(); tfContrasenaRegistro.setPromptText("Contraseña"); tfContrasenaRegistro.setMaxWidth(600);
        tfContrasenaRegistro.setStyle("-fx-background-color: #b0faff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        HBox hbTelefonosRegistros = new HBox(); hbTelefonosRegistros.setAlignment(Pos.CENTER); hbTelefonosRegistros.setSpacing(20);
        TextField tfTelefonosRegistros = new TextField(); tfTelefonosRegistros.setPromptText("Ingresa un teléfono"); hbTelefonosRegistros.setMaxWidth(550);
        tfTelefonosRegistros.setStyle("-fx-background-color: #b0faff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        Button btnAgregarTelefonos = new Button("Agregar");
        btnAgregarTelefonos.setStyle("-fx-background-color: #72b083; -fx-text-fill: #ffffff; -fx-font-size: 30px;");
        btnAgregarTelefonos.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        Label contadorTelefonos = new Label("0");
        contadorTelefonos.setStyle("-fx-text-fill: #00ff00; -fx-font-size: 40px;");
        btnAgregarTelefonos.setOnMouseClicked(e -> {
            if(!tfTelefonosRegistros.getText().equals("")){
                telefonos.add(tfTelefonosRegistros.getText());
                contadorTelefonos.setText(""+(Integer.parseInt(contadorTelefonos.getText())+1));
                tfTelefonosRegistros.setText("");
            }else{                    
                showMessage(AlertType.ERROR,"Error","Error","Ingrese correctamente un número");
            }
        });
        hbTelefonosRegistros.getChildren().addAll(tfTelefonosRegistros,btnAgregarTelefonos, contadorTelefonos);
        
        vbCamposRegistros.getChildren().addAll(tfUsuarioRegistro,tfNombreRegistro, tfContrasenaRegistro,hbTelefonosRegistros);
        
        HBox hbFinRegistro = new HBox(); hbFinRegistro.setSpacing(20); hbFinRegistro.setAlignment(Pos.CENTER); hbFinRegistro.setPadding(new Insets(10, 10, 10, 10)); 
        Button salidaRegistro = new Button("Salir");
        salidaRegistro.setStyle("-fx-background-color: #c2484e; -fx-text-fill: #ffffff; -fx-font-size: 30;");
        salidaRegistro.setOnMouseClicked(e -> {
            telefonos.clear();
            tfUsuarioRegistro.clear(); tfTelefonosRegistros.clear(); tfNombreRegistro.clear(); tfContrasenaRegistro.clear(); contadorTelefonos.setText("0");
            tfUsuario.clear(); tfContrasena.clear();
            vbCampos.getChildren().clear();
            vbCampos.getChildren().addAll(tfUsuario, tfContrasena,vbRegistro);
            App.STAGE.setScene(INICIOSESION);        
        });
 
        Button crearCuenta = new Button("Crear Cuenta");
        crearCuenta.setStyle("-fx-background-color: #72b083; -fx-text-fill: #ffffff; -fx-font-size: 30;");
        crearCuenta.setOnMouseClicked(e -> {
            if(App.USUARIOS.keySet().contains(tfUsuarioRegistro.getText())){
                showMessage(AlertType.ERROR,"Usuario Registrado","Usuario Registrado","El usuario que ingresó ya existe, ingrese otro");
            }
            else if(!tfUsuarioRegistro.getText().equals("")&&!tfContrasenaRegistro.getText().equals("")&&!tfNombreRegistro.getText().equals("")&&!telefonos.isEmpty()){
                ArrayList<String> telefonosUsuario = new ArrayList<>();
                for(String s: telefonos){
                    telefonosUsuario.add(s);
                }
                User nuevoUsuario = new User(tfUsuarioRegistro.getText(),tfContrasenaRegistro.getText(),tfNombreRegistro.getText(),telefonosUsuario);
                App.USUARIOS.put(nuevoUsuario.getUsuario(),nuevoUsuario);
                User.actualizarUsuarios();
                tfUsuarioRegistro.clear(); tfTelefonosRegistros.clear(); tfNombreRegistro.clear(); tfContrasenaRegistro.clear(); contadorTelefonos.setText("0");
                tfUsuario.clear(); tfContrasena.clear();
                vbCampos.getChildren().clear();
                vbCampos.getChildren().addAll(tfUsuario, tfContrasena,vbRegistro);
                telefonos.clear();
                App.STAGE.setScene(INICIOSESION);
            }else{
                showMessage(AlertType.ERROR,"Error","Error","Ingrese los datos correctamente");
            }
        });
   
        hbFinRegistro.getChildren().addAll(crearCuenta,salidaRegistro);
        
        rootRegistro.setCenter(vbCamposRegistros);
        rootRegistro.setBottom(hbFinRegistro);
        
        REGISTRO = new Scene(rootRegistro,800,600);

    }
    private static void mostrarVehiculo(VBox vbVehiculo, Vehiculo v){
        
        vbVehiculo.getChildren().clear();
        
        System.out.println(v.getModelo());
        
        Label lblMarca = new Label("Marca: " + v.getMarca()); lblMarca.setStyle("-fx-text-fill: #000000; -fx-font-size: 40;");
        Label lblModelo = new Label("Modelo: " + v.getModelo()); lblModelo.setStyle("-fx-text-fill: #000000; -fx-font-size: 40;");
        ImageView ivImage = new ImageView(new Image("Images\\"+v.getFoto()+".jpg"));
        ivImage.setFitHeight(100);
        ivImage.setFitWidth(100);
        Label lblAnio = new Label("Año: " + v.getAnio()); lblAnio.setStyle("-fx-text-fill: #000000; -fx-font-size: 20;");
        Label lblKm = new Label("Kilometraje: " + v.getKilometraje() + "km"); lblKm.setStyle("-fx-text-fill: #000000; -fx-font-size: 20;");
        Label lblPrecio = new Label("Precio: $" + v.getPrecio()); lblPrecio.setStyle("-fx-text-fill: #73ad71; -fx-font-size: 40;");
        Label lblDuenio = new Label("Dueño: " + v.getDuenio().getNombre()); lblDuenio.setStyle("-fx-text-fill: #000000; -fx-font-size: 20;");
        Label lblTelefonos = new Label("Teléfonos: "); lblTelefonos.setStyle("-fx-text-fill: #000000; -fx-font-size: 20;");
        
        vbVehiculo.getChildren().addAll(lblMarca, lblModelo, ivImage, lblAnio, lblKm, lblPrecio, lblDuenio, lblTelefonos);
        
        for ( String numero : v.getDuenio().getTelefonos() ){
            Label lblNumero = new Label(numero); lblNumero.setStyle("-fx-text-fill: #4287f5; -fx-font-size: 20; -fx-underline: true");
            vbVehiculo.getChildren().add(lblNumero);
        }         
    }
    private static ArrayList<Vehiculo> filtrarVehiculos(ArrayList<Vehiculo> vehiculos, ComboBox<String> filtro, ComboBox<String> rango, TextField min, TextField max){
        ArrayList<Vehiculo> vehiculosFiltrados= new ArrayList<>();
        TipoVehiculo tipo = TipoVehiculo.CARRO;
        
        Double minimo = 0.0; Double maximo = 0.0; String parametro = "";
        
        if ( !min.getText().equals("") && !max.getText().equals("") ){
            minimo = Double.valueOf(min.getText());
            maximo = Double.valueOf(max.getText());
        }
        
        if ( rango.getValue() != null ){
            parametro = rango.getValue();
        }
        
        switch (filtro.getValue()){
                case "Carro":                    
                    break;
                case "Camion":
                    tipo = TipoVehiculo.CAMION;
                    break;
                case "Moto":
                    tipo = TipoVehiculo.MOTO;
                    break;
                default:
                    if ( parametro.equals("") ){
                        vehiculosFiltrados.addAll(vehiculos);
                        return vehiculosFiltrados;
                    }     
                    for ( Vehiculo v : vehiculos ){
                        if ( parametro.equals("Precio") ){
                            if ( minimo <= v.getPrecio() && v.getPrecio() <= maximo ){
                                vehiculosFiltrados.add(v);
                            }
                        }else {
                            if( minimo <= v.getKilometraje() && v.getKilometraje() <= maximo ){
                                vehiculosFiltrados.add(v);
                            }
                        }
                    }               
                    
                    return vehiculosFiltrados;
        }
        for ( Vehiculo v : vehiculos ){
            if ( v.getTipoVehiculo() == tipo ){
                if ( parametro.equals("Precio") ){
                    if ( minimo <= v.getPrecio() && v.getPrecio() <= maximo ){
                        vehiculosFiltrados.add(v);
                    }
                }else if ( parametro.equals("Kilometraje") ){
                    if( minimo <= v.getKilometraje() && v.getKilometraje() <= maximo ){
                        vehiculosFiltrados.add(v);
                    }
                }else vehiculosFiltrados.add(v);
            }
        }
        return vehiculosFiltrados;
    }
    
    public static void ordenarVehiculos
        (CircularLinkedList<Vehiculo> vehiculosMostrados, ArrayList<Vehiculo> vehiculos, ComboBox<String> orden){
                    
        vehiculosMostrados.clear(); 
        PriorityQueue<Vehiculo> pqOrden;
        
        switch ( orden.getValue() ){
            case "Marca y modelo(alfabetico)":
                pqOrden = new PriorityQueue<Vehiculo>( ( Vehiculo v1, Vehiculo v2 ) -> {
                    if ( v1.getMarca().compareTo(v2.getMarca()) == 0) return v1.getModelo().compareTo(v2.getModelo());
                    else return v1.getMarca().compareTo(v2.getMarca());
                });
                break;            
            case "Precio":
                pqOrden = new PriorityQueue<Vehiculo>( ( Vehiculo v1, Vehiculo v2 ) -> {
                    return Double.compare(v2.getPrecio(), v1.getPrecio());
                });
                break;
            case "Kilometraje":
                pqOrden = new PriorityQueue<>( (Vehiculo v1, Vehiculo v2) -> {
                    return v2.getKilometraje() - v1.getKilometraje();
        });
                break;            
            case "Peso":
                pqOrden = new PriorityQueue<Vehiculo>( ( Vehiculo v1, Vehiculo v2 ) -> {
                    return v2.getPeso() - v1.getPeso();
                });
                break;
            default:
                pqOrden = new PriorityQueue<Vehiculo>( ( Vehiculo v1, Vehiculo v2 ) -> {
                    return v2.getAnio() - v1.getAnio();
                });
                break;
        }
        
        for ( Vehiculo v : vehiculos ){          
            if ( v != null )pqOrden.offer(v);
        }
        while ( !pqOrden.isEmpty() ){
            vehiculosMostrados.add(pqOrden.poll());
        }
    }
     
    public void crearVehiculo(VBox vbNuevoVehiculo, VBox vbACCIDENTES, VBox vbSERVICIOS){
        //Vaciamos los contenedores
        vbNuevoVehiculo.getChildren().clear(); vbACCIDENTES.getChildren().clear(); vbSERVICIOS.getChildren().clear();
        //Creamos los textfields
        int ancho = 300;
        TextField tfMarca = new TextField(); tfMarca.setPromptText("Marca"); tfMarca.setMaxWidth(ancho);
        tfMarca.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfModelo = new TextField(); tfModelo.setPromptText("Modelo"); tfModelo.setMaxWidth(ancho);
        tfModelo.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfAnio = new TextField(); tfAnio.setPromptText("Año"); tfAnio.setMaxWidth(200);
        tfAnio.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfKilometraje = new TextField(); tfKilometraje.setPromptText("Kilometraje (entero)"); //tfKilometraje.setMaxWidth(ancho);
        tfKilometraje.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfPrecio = new TextField(); tfPrecio.setPromptText("Precio (ej: 12.34) [$]"); tfPrecio.setMaxWidth(500);
        tfPrecio.setStyle("-fx-background-color: #c1ffbd; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfMotor = new TextField(); tfMotor.setPromptText("Motor"); tfMotor.setMaxWidth(ancho);
        tfMotor.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        Label lblTransmision = new Label("Transmision: "); lblTransmision.setStyle("-fx-text-fill: #000000; -fx-font-size: 25px;");
        
        ComboBox<String> cbTransmision = new ComboBox(); cbTransmision.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 20px;");
        ObservableList<String> itemsTransmision = FXCollections.observableArrayList(
            "manual", "automática"
        );
        cbTransmision.setItems(itemsTransmision);
        cbTransmision.setValue("automática");
        
        TextField tfPeso = new TextField(); tfPeso.setPromptText("Peso (entero) [kg]"); tfPeso.setMaxWidth(ancho);
        tfPeso.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfUbicacion = new TextField(); tfUbicacion.setPromptText("Ubicacion"); tfUbicacion.setMaxWidth(ancho);
        tfUbicacion.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        Label lblTipo = new Label("Transmision: "); lblTipo.setStyle("-fx-text-fill: #000000; -fx-font-size: 25px;");
        
        ComboBox<String> cbTipo = new ComboBox(); cbTipo.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 20px;");
        ObservableList<String> itemsTipo = FXCollections.observableArrayList(
            "CARRO", "CAMION", "MOTO"
        );
        cbTipo.setItems(itemsTipo);
        cbTipo.setValue("CARRO");
        
        //Creamos los contenedores y agregamos los controladores
        HBox hb1 = new HBox(); hb1.setAlignment(Pos.CENTER);
        HBox hb2 = new HBox(); hb2.setAlignment(Pos.CENTER);
        HBox hb3 = new HBox(); hb3.setAlignment(Pos.CENTER); hb3.setSpacing(10);
        HBox hb4= new HBox(); hb4.setAlignment(Pos.CENTER); hb4.setSpacing(40);
        
        hb1.getChildren().addAll(tfMarca, tfModelo, tfAnio, tfKilometraje);
        hb2.getChildren().addAll(tfMotor, tfPeso, tfUbicacion);
        hb3.getChildren().addAll(lblTransmision, cbTransmision, lblTipo, cbTipo);
        
        vbNuevoVehiculo.getChildren().addAll(hb1, hb2, tfPrecio, hb3);
        
        //Creamos dos botones que abriran las ventanas para agregar los historiales de accidentes y servicios
        Button btnACCIDENTES = new Button("Agregar Historial de Accidentes");
        btnACCIDENTES.setStyle("-fx-background-color: #91fffb; -fx-text-fill: #000000; -fx-font-size: 20px;");
        btnACCIDENTES.setOnAction( e -> {
            ACCIDENTES.show();
        });
        
        Button btnSERVICIOS = new Button("Agregar Historial de Servicios");
        btnSERVICIOS.setStyle("-fx-background-color: #91fffb; -fx-text-fill: #000000; -fx-font-size: 20px;");
        btnSERVICIOS.setOnAction( e -> {
            SERVICIOS.show();
        });
        
        hb4.getChildren().addAll(btnACCIDENTES, btnSERVICIOS);
        
        //Creamos una lista de historiales de servicios y otra de accidentes para el nuevo vehiculo
        ArrayList<Accident> accidentes = new ArrayList<>(); ArrayList<Servicio> servicios = new ArrayList<>();
       
        //Creamos los controladores para las nuevas ventanas
        
        //Servicios
        DatePicker dtServicio = new DatePicker(); dtServicio.setValue(LocalDate.now()); dtServicio.setPrefSize(300, 80);
        dtServicio.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 20px;");
        
        TextField tfTipoServicio = new TextField(); tfTipoServicio.setPromptText("Tipo"); tfTipoServicio.setMaxWidth(ancho);
        tfTipoServicio.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfCostoServicio = new TextField(); tfCostoServicio.setPromptText("Costo (ej: 12.34) [$]"); tfCostoServicio.setMaxWidth(ancho);
        tfCostoServicio.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfDetallesServicio = new TextField(); tfDetallesServicio.setPromptText("Detalles"); tfDetallesServicio.setMaxWidth(ancho);
        tfDetallesServicio.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        //Accidentes
        DatePicker dtAccidente = new DatePicker(); dtAccidente.setValue(LocalDate.now()); dtAccidente.setPrefSize(300, 80);
        dtAccidente.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 20px;");
        
        TextField tfDescripcion = new TextField(); tfDescripcion.setPromptText("Tipo"); tfDescripcion.setMaxWidth(ancho);
        tfDescripcion.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        TextField tfCostoAccidente = new TextField(); tfCostoAccidente.setPromptText("Costo (ej: 12.34) [$]"); tfCostoAccidente.setMaxWidth(ancho);
        tfCostoAccidente.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 30px;");
        
        //Creamos un boton para agregar el servicio y el accidente
        Button btnAgregarServicio = new Button("Agregar"); btnAgregarServicio.setStyle("-fx-background-color: #f6ff91; -fx-text-fill: #000000; -fx-font-size: 20px;");
        btnAgregarServicio.setOnAction( e -> {
            if( !tfTipoServicio.getText().equals("") && !tfCostoServicio.getText().equals("") ){ //El detalle es opcional y la fecha siempre tiene valor
                servicios.add(new Servicio(
                        dtServicio.getValue(), tfTipoServicio.getText(), Double.parseDouble( tfCostoServicio.getText() ), tfDetallesServicio.getText() 
                ));
                
                dtServicio.setValue(LocalDate.now()); tfTipoServicio.clear(); tfCostoServicio.clear(); tfDetallesServicio.clear();
                Label lblEx = new Label("Agregado con exito!\nServicios: " + servicios.size());
                lblEx.setStyle("-fx-text-fill: #00ff00; -fx-font-size: 20px;");
                vbSERVICIOS.getChildren().clear();
                vbSERVICIOS.getChildren().addAll(dtServicio, tfTipoServicio, tfCostoServicio, tfDetallesServicio, btnAgregarServicio, lblEx);
            }else{
                Label lblErr = new Label("Todos los campos deben llenarse");
                lblErr.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 20px;");
                vbSERVICIOS.getChildren().clear();
                vbSERVICIOS.getChildren().addAll(dtServicio, tfTipoServicio, tfCostoServicio, tfDetallesServicio, btnAgregarServicio, lblErr);
            }
        });
        
        vbSERVICIOS.getChildren().addAll(dtServicio, tfTipoServicio, tfCostoServicio, tfDetallesServicio, btnAgregarServicio);
        
        Button btnAgregarAccidente = new Button("Agregar"); btnAgregarAccidente.setStyle("-fx-background-color: #f6ff91; -fx-text-fill: #000000; -fx-font-size: 20px;");
        btnAgregarAccidente.setOnAction( e -> {
            if( !tfCostoAccidente.getText().equals("") ){ //La descripcion es opcional
                accidentes.add(new Accident(
                        dtAccidente.getValue(), tfDescripcion.getText(), Double.valueOf( tfCostoAccidente.getText() ) 
                ));
                
                dtAccidente.setValue(LocalDate.now()); tfDescripcion.clear(); tfCostoAccidente.clear();
                Label lblEx = new Label("Agregado con exito!\nAccidentes: " + accidentes.size());
                lblEx.setStyle("-fx-text-fill: #00ff00; -fx-font-size: 20px;");
                vbACCIDENTES.getChildren().clear();
                vbACCIDENTES.getChildren().addAll(dtAccidente, tfDescripcion, tfCostoAccidente, btnAgregarAccidente, lblEx);
            }else{
                Label lblErr = new Label("Todos los campos deben llenarse");
                lblErr.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 20px;");
                vbACCIDENTES.getChildren().clear();
                vbACCIDENTES.getChildren().addAll(dtAccidente, tfDescripcion, tfCostoAccidente, btnAgregarAccidente, lblErr);
            }
        });
        
        vbACCIDENTES.getChildren().addAll(dtAccidente, tfDescripcion, tfCostoAccidente, btnAgregarAccidente);
        


        Button btnAdjuntarFoto = new Button("Agregar Foto"); btnAdjuntarFoto.setStyle("-fx-background-color: #f6ff91; -fx-text-fill: #000000; -fx-font-size: 20px;");
        btnAdjuntarFoto.setOnAction( e -> {
            ExtensionFilter ext1 = new ExtensionFilter("Image Files", "*.jpg");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(ext1);
            fileChooser.setTitle("Open your car Image");
            
            
            File selectedFile = fileChooser.showOpenDialog(this);
            if(selectedFile!=null){
                String imagen=selectedFile.getName().split("\\.")[0];
                btnAdjuntarFoto.setText(imagen);
                Path destino = Path.of("src\\main\\resources\\Images\\"+ imagen +".jpg");  //Referencia relativa
                Path origen = Path.of(selectedFile.getPath()); //Referencia absoluta
                try {
                    Files.copy(origen, destino.toAbsolutePath(),StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
        });
        
        //Creamos el boton para crear el vehiculos
        Button btnCrear = new Button("Poner en Venta el Vehiculo!");
        btnCrear.setStyle("-fx-background-color: #f6ff91; -fx-text-fill: #000000; -fx-font-size: 30px;");
        btnCrear.setOnAction( e -> {
            if (
                !tfMarca.getText().equals("") && !tfModelo.getText().equals("") && !tfAnio.getText().equals("") && !tfKilometraje.getText().equals("")
                && !tfMotor.getText().equals("") && !tfPrecio.getText().equals("") && !tfUbicacion.getText().equals("") && !tfPeso.getText().equals("")
                && !(false)    
                    ){
                    double precio = Double.parseDouble(tfPrecio.getText());
                    int anio = Integer.parseInt(tfAnio.getText());
                    int kilometraje = Integer.parseInt(tfKilometraje.getText());
                    int peso = Integer.parseInt(tfPeso.getText());
                    TipoVehiculo tipo = TipoVehiculo.valueOf(cbTipo.getValue());
                    String id = String.valueOf(Integer.valueOf(App.VEHICULOS.get((App.VEHICULOS.size()-1)).getId())+1);
                    Vehiculo nuevo;
                    nuevo = new Vehiculo(id, precio, tfMarca.getText(), tfModelo.getText(), btnAdjuntarFoto.getText(), anio, kilometraje, tfMotor.getText(), cbTransmision.getValue(), peso, tfUbicacion.getText(), accidentes, servicios, tipo, App.USUARIOACTUAL);
                    App.VEHICULOS.add(nuevo);
                    App.USUARIOACTUAL.getVehiculos().add(nuevo);

                    Vehiculo.actualizarVehiculos();
                    App.menu();
                }
        });
        
        vbNuevoVehiculo.getChildren().addAll(hb4, btnAdjuntarFoto,btnCrear);
    }
    
    public void showMessage(AlertType tipo, String titulo, String encabezado, String mensaje) {
        Alert alerta = new Alert(tipo);             //constructor de la alerta
        alerta.setHeaderText(encabezado);   //establecer encabezado de la alerta
        alerta.setTitle(titulo);                           //establecer el título de la ventana que muestra la alerta
        alerta.setContentText(mensaje);        //establecer el contenido de la alerta
        alerta.showAndWait();     
}
}
