package ClasesProyect;

import Estructuras.ArrayList;
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
    private ArrayList numeroCel; // Cambiarlo por una TDA propio tipo ArrayList
    private ArrayList carrosComprados; //Cambiarlo por una TDA
    private ArrayList carrosEnVenta; //Lista de carros que el Salesman tiene en su catalogo, cambiar tipo de dato a TDA propio
    
    public User(String u, String p, String n){
        usuario = u; contrasena = p; nombre = n; numeroCel = null; 
        carrosComprados = null; carrosEnVenta  = null;
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
    
    public static HashMap<String, User> cargarUsuarios(){
        HashMap<String, User> usuarios = new HashMap<>();
        usuarios.put("a", new User("a", "b", "c"));
        usuarios.put("sebsm1234", new User("sebsm1234", ".getContrasena()", "Sebastian Manzanilla"));
        return usuarios;
    }
}
