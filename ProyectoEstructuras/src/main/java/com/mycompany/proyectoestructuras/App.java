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
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class App extends Application {

    private static Scene MENU;
    public static Stage STAGE;
    public static HashMap<String, User> USUARIOS;
    public static User USUARIOACTUAL;
    public static ArrayList<Vehiculo> VEHICULOS;

    @Override
    public void start(Stage s) throws IOException {
        STAGE = s;
        USUARIOS = cargarUsuarios();
        VEHICULOS = cargarVehiculos();
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
    
    
    public static ArrayList<Vehiculo> cargarVehiculos(){
    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try{
            FileReader reader = new FileReader("src/main/resources/com/mycompany/proyectoestructuras/vehiculos.csv");
            BufferedReader bf = new BufferedReader(reader);
            String line;
            bf.readLine();
            while((line=bf.readLine())!=null){
                String[] division = line.split(",");
                String id = division[0];
                double precio = Double.valueOf(division[1]);
                String marca = division[2];
                String modelo = division[3];
                String foto = division[4]; 
                int anio = Integer.valueOf(division[5]);
                int kilometraje = Integer.valueOf(division[6]);
                String motor = division[7];
                String trasmision = division[8];
                int peso = Integer.valueOf(division[9]);
                String ubiActual = division[10];
                ArrayList<Accident> histAccident = new ArrayList<>(); 
                if(!division[11].equals("null")){
                    String[] accidentes = division[11].split("/");
                    for(String s: accidentes){
                    String[] subdivision = s.split(";");
                    String[] divisionFecha = subdivision[0].split("-");
                    LocalDate fecha = LocalDate.of(Integer.valueOf(divisionFecha[2]),Integer.valueOf(divisionFecha[1]),Integer.valueOf(divisionFecha[0]));
                    histAccident.add(new Accident(fecha,subdivision[1],Double.valueOf(subdivision[2])));
                    }
                }
                ArrayList<Servicio> histService = new ArrayList<>(); 
                if(!division[12].equals("null")){
                String[] servicios = division[12].split("/");
                for(String s: servicios){
                    String[] subdivision = s.split(";");
                    String[] divisionFecha = subdivision[0].split("-");
                    LocalDate fecha = LocalDate.of(Integer.valueOf(divisionFecha[2]),Integer.valueOf(divisionFecha[1]),Integer.valueOf(divisionFecha[0]));
                    histService.add(new Servicio(fecha,subdivision[1],Double.valueOf(subdivision[2]),subdivision[3]));
                }
                }
                TipoVehiculo tipovehiculo = TipoVehiculo.valueOf(division[13]); 
                User duenio = USUARIOS.get(division[14]);
                Vehiculo vehiculo = new Vehiculo(id,precio,marca,modelo,foto,anio,kilometraje,motor,trasmision,peso,ubiActual,histAccident,histService,tipovehiculo,duenio);
                vehiculos.add(vehiculo);
            }
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return vehiculos;
    }
    
        private static HashMap<String, User> cargarUsuarios(){
        HashMap<String, User> usuarios = new HashMap<>();
        
        try{
            FileReader reader = new FileReader("src/main/resources/com/mycompany/proyectoestructuras/usuarios.csv");
            BufferedReader br = new BufferedReader(reader);
            String line; br.readLine();String []datos;
            
            while( (line = br.readLine() ) != null ){
                datos = line.split(",");
                String usuario = datos[0];
                String contrasena = datos[1];
                String nombre = datos[2];
                ArrayList<String> telefonos = new ArrayList<>();
                for ( String numero : datos[3].split(";") ){
                    if ( !numero.equals("") ) telefonos.add(numero);
                }
                
                usuarios.put(usuario, new User(usuario, contrasena, nombre, telefonos));
            }
            
        }catch(IOException e){System.err.println(e);}
        
        return usuarios;
    
    }
}