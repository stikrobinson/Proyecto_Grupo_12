/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases;

/**
 *
 * @author alexo
 */
public class Juego {
    private static Juego juego;
    
    private Juego(){
        //Atributos necesarios para proceder con el juego
    }
    
    public static Juego getJuego(){
        if(juego==null){
            juego = new Juego();
        }
        return Juego.juego;
    }
    
    public void reiniciarJuego(){
        juego=null;
    }
    
}
