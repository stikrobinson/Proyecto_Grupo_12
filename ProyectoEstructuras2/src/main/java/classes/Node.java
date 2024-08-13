/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author alexo
 */
public class Node {
    public String contenido;
    public BTree izq, der;
    public boolean animal;
    
    public Node(String c){
        contenido = c;
        izq = null; der = null;
    }
    
    public Node(){
        contenido = null;
        izq = null; der = null;
    }
    
    public boolean isLeaf(){
        return this.izq == null && this.der == null;
    }
}
