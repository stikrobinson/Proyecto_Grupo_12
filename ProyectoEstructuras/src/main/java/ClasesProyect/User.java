package ClasesProyect;

import Estructuras.ArrayList;
import com.mycompany.proyectoestructuras.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author alexo
 */
public class User {
    private String usuario;
    private String contrasena;
    private String nombre;
    private ArrayList<String> telefonos;
    private ArrayList<Vehiculo> vehiculos;
    
    public User(String u, String p, String n,  ArrayList<String> t){
        usuario = u; contrasena = p; nombre = n; telefonos = t; 
        vehiculos  = new ArrayList<Vehiculo>();
    }
    
    public ArrayList<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    
    public ArrayList<String> getTelefonos(){
        return telefonos;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public String getContrasena(){
        return contrasena;
    }
    
    public void comprarCarro(Vehiculo c){
        
    }
    
    public boolean equals(Object o){
        User u = (User) o;
        return u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena) && u.getNombre().equals(nombre);
    }

    public Vehiculo obtenerVehiculo(String id){ //id del carro a buscar
        return null;
    }

    public void crearCarro(Vehiculo car){
        
    }
    public void editarCarro(Vehiculo car){
        
    }
    public void eliminarCarro(Vehiculo car){
        
    }
    
    public static void actualizarUsuarios(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/com/mycompany/proyectoestructuras/usuarios.csv"))){
            bw.write("usuario, contrasena, nombre, telefonos(;)");
            bw.newLine();
            for(String u: App.USUARIOS.keySet()){
                String line = "";
                User usuario = App.USUARIOS.get(u);
                line = line + u+","+usuario.getContrasena()+","+usuario.getNombre()+",";
                int contador = 0;
                for(String s: usuario.getTelefonos()){
                    if(contador!=usuario.getTelefonos().size()-1){
                        line = line + s + ";";
                    }else{
                        line = line + s;
                    }
                    contador++;
                }
                bw.write(line);
                bw.newLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    
}
}