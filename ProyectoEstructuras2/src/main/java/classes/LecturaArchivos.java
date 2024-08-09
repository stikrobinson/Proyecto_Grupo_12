/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author alexo
 */
public class LecturaArchivos {
    private String questionFileName;
    private String ansFileName;
    private HashMap<String, LinkedList<String>> resultados;
    private LinkedList<String> preguntas;
    
    
    
    public LecturaArchivos(String questionFileName, String ansFileName){
        this.questionFileName = questionFileName;
        this.ansFileName = ansFileName;
        this.preguntas = new LinkedList<>();
        this.resultados = new HashMap<>();
    }
        
    private void lecturaPreguntas(String filePath){
         try{
            //Creo un leedor para el archivo de las preguntas en la carpeta "preguntas"
            BufferedReader br = new BufferedReader( new FileReader( ".\\src\\main\\resources\\Texto\\" + filePath + ".txt" ) );
            String line = "";
            while ( ( line = br.readLine() ) != null ){
                preguntas.add(line);
            }
        }catch(IOException ioe){System.err.println(ioe);};
    }
    
    private void  lecturaRespuestas(String filePath){
        try{           
            BufferedReader br = new BufferedReader( new FileReader( ".\\src\\main\\resources\\Texto\\" + filePath + ".txt" ) );
            String line = "";
            while( ( line = br.readLine() ) != null ){
                String[] datos = line.split(" ");
                if( datos.length - 1 == preguntas.size() ){
                    //Añado las respuestas a la lista
                    LinkedList<String> respuestas = new LinkedList<>();
                    for( int i = 1; i < datos.length; i++){
                        respuestas.add(datos[i]);
                    }
                    //No puede existir repeticion en las respuestas y verifico que las respuestas no esten ya registradas
                    if ( !resultados.values().contains(respuestas) ) {
                        resultados.put(datos[0], respuestas);
                    }
                }else{
                    //Cuando los archivos no tienen concordancia con en número de preguntas y el número de respuestas.
                }
            }
        }
        catch(IOException ioe){System.err.println(ioe);};
    }
    
    public BTree buildTree(){
        lecturaPreguntas(questionFileName);
        lecturaRespuestas(ansFileName);
        BTree btree = new BTree();
        btree.builderArbol(preguntas, resultados);
        System.out.println(btree);
        return btree;
    }
    
}
