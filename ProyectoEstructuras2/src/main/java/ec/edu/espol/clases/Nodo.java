package ec.edu.espol.clases;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CltControl
 */
public class Nodo {
    public String contenido;
    public ABD izq, der;
    
    public Nodo(String c){
        contenido = c;
        izq = null; der = null;
    }
    
    public Nodo(){
        contenido = null;
        izq = null; der = null;
    }
    
    public boolean isLeaf(){
        return this.izq == null && this.der == null;
    }
}
