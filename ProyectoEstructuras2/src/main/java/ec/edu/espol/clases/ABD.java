package ec.edu.espol.clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author sebsm
 */
public class ABD {
    private Nodo raiz;

    public ABD(){
        clear();
    }
    public ABD(String r){
        raiz = new Nodo(r);
    }
    public void clear(){
        raiz = null;
    }
    public boolean isEmpty(){
        return raiz == null;
    }
    public boolean isLeaf(){
        return raiz.izq == null && raiz.der == null;
    }

    public String getRaiz(){
        return raiz.contenido;
    }
    public ABD getIzq(){
        return raiz.izq;
    }
    public ABD getDer(){
        return raiz.der;
    }

    public void cargar(String p, String r){
        clear();
        //Conisgo la lista de preguntas y las lista de los animales con sus respuestas
        ArrayList<String> preguntas = new ArrayList<>();
        // [pregunta 1, pregunta 2, pregunta 3, ..., pregunta n]
        HashMap<String, ArrayList<String>> respuestas = new HashMap<>();
        //{ animal 1 = [si, no,..., no] ; animal 2 = {no, si, ..., si] ; ...}

        try{
            //Creo un leedor para el archivo de las preguntas en la carpeta "preguntas"
            BufferedReader br = new BufferedReader( new FileReader( "./src/main/resources/ec/edu/espol/proyectoestructuras2/preguntas/" + p ) );
            String line = "";

            while ( ( line = br.readLine() ) != null ){
                //Preguntas
                preguntas.add(line);
            }
            //Cambio al leedor por otro con para el archivo de las respuestas en la carpeta "respuestas"
            br = new BufferedReader( new FileReader( "./src/main/resources/ec/edu/espol/proyectoestructuras2/respuestas/" + r ) );

            while( ( line = br.readLine() ) != null ){
                //Respuestas
                String[] datos = line.split(";");
                ArrayList<String> sino = new ArrayList<>();

                //Verifico que haya tantas respuestas como preguntas para cada animal
                if( datos.length - 1 == preguntas.size() ){
                    //Añado las respuestas a la lista
                    for( int i = 1; i < datos.length; i++){
                        sino.add(datos[i]);
                    }

                    //No puede existir repeticion en las respuestas
                    //Verifico que las respuestas no esten ya registradas
                    if ( !respuestas.values().contains(sino) ) {
                        //Añado al animal con sus respuestas
                        respuestas.put(datos[0], sino);
                    }
                }
            }

            //Ya tengo las dos listas llenas
//            System.out.println(preguntas);
//            System.out.println(respuestas);
        }
        catch(IOException ioe){System.err.println(ioe);};

        //La raiz del arbol es la primera pregunta
        raiz = new Nodo(preguntas.get(0));

        //Añado el resto de preguntas
        for( int i = 1; i < preguntas.size(); i++){
            anadirPregunta(preguntas.get(i));
        }

        //Ahora nuestro arbol es tal que todas las hojas son la ultima pregunta
        //Y sus ancestros son las preguntas anteriores

        //Ahora añadimos a los animales
        anadirHojas();
        for( String animal : respuestas.keySet() ){
            anadirAnimal( animal,  respuestas.get(animal) );
        }

        //Nuestro arbol ya esta lleno
    }

    private void anadirPregunta(String p){
        //Las preguntas son todos los nodos menos los ultimos, los cuales son los animales
        //Primero verifico si el arbol actual es hoja, si lo es, la pregunta va a ser ambos nodos de la raiz
        if( isLeaf() ){
            raiz.izq = new ABD(p);
            raiz.der = new ABD(p);
        }
        //Caso contrario, hago una llamada recursiva con el subArbol izquierdo y el derecho
        else{
            raiz.izq.anadirPregunta(p);
            raiz.der.anadirPregunta(p);
        }
    }

    private void anadirHojas(){
        ArrayDeque<Nodo> cola = new ArrayDeque<>();
        cola.offer(raiz);
        while(!cola.isEmpty()){
            Nodo n = cola.poll();
            if(n.izq!=null && n.der!=null){
                cola.offer(n.izq.raiz);
                cola.offer(n.der.raiz);
            }else if(n.izq!=null){
                cola.offer(n.izq.raiz);
            }
            else if (n.der!=null){
                cola.offer(n.der.raiz);
            }else if(n.isLeaf()){
                n.der = new ABD();
                n.der.raiz = new Nodo();
                n.izq = new ABD();
                n.izq.raiz = new Nodo();
            }
        }
    }
    
    private boolean anadirAnimal( String animal, ArrayList<String> respuestas){
        //Verifico que la lista de respuestas no este vacia
        if( respuestas.isEmpty() ) return false;

        //Si la respuesta a cada pregunta es "si", me dirigo a la izquierda
        //Caso contrario, si es "no" me dirijo a la derecha

        //Elimino la primera respuesta porque ya la use
        if ( respuestas.remove(0).equals("si") ){
            //Si la lista de respuestas esta vacia ya, es porque llegue a la ultima pregunta
            if ( respuestas.isEmpty() ){
                //Anado a la izquierda si es que no hay ya un animal ahi
                if ( raiz.izq.raiz.contenido == null ) {
                    raiz.izq.raiz.contenido = animal;
                    return true;
                }
                //Caso contrario, no lo anado
                return false;
            }
            //Si las respuestas no estan vacias, continuo al siguiente subarbol izquierdo
            return raiz.izq.anadirAnimal(animal, respuestas);
        }
        //Si la respuesta es distinta a "si", hago el mismo proceso pero con la derecha
        if ( respuestas.isEmpty() ){
            //Anado a la derecha si es que no hay ya un animal ahi
            if ( raiz.der.raiz.contenido == null) {
                raiz.der.raiz.contenido = animal;
                return true;
            }
            //Caso contrario, no lo anado
            return false;
            }
            //Si las respuestas no estan vacias, continuo al siguiente subarbol derecho
            return raiz.der.anadirAnimal(animal, respuestas);
    }
    
    public ArrayList<String> adivinarAnimal( ArrayDeque<String> respuestas ){
        //Para este punto, el usuario habra dado n respuestas, donde n es el numero de preguntas
        //que el usuario quizo responder (n <= numero de preguntas)
        
        //Si las respuestas estan vacias, retorno todos los animales que encuentre en este subarbol
        if ( respuestas.isEmpty() ) return getAnimales();
        
        //Caso contrario, evaluo la siguiente respuesta
        //Si la respuesta es "si", continuo a la izquierda
        if( respuestas.poll().equals("si") ){
            //Si el subArbol izquierdo es null, es porque no hay un animal que satisfaga las respuestas
            if ( raiz.izq.raiz.contenido == null ) return null;
            //Caso contario, hago la llamada recursiva
            return raiz.izq.adivinarAnimal(respuestas);
        }
        //Caso contrario, continuo a la derecha y hago el mismo proceso
        //Si el subArbol derecho es null, es porque no hay un animal que satisfaga las respuestas
        if ( raiz.der.raiz.contenido == null ) return null;
        //Caso contario, hago la llamada recursiva
        return raiz.der.adivinarAnimal(respuestas);
    }
    
    public ArrayList<String> getAnimales(){
        //Recorrido de anchura, eliminando las preguntas
        ArrayList<String> lista = new ArrayList<>();
        ArrayDeque<Nodo> cola = new ArrayDeque<>();
        cola.offer(raiz);
        
        while(!cola.isEmpty()){
            Nodo n = cola.poll();
            //Solo se anade contenido si el elemento no es pregunta
            //Las preguntas son todas aquellas que contengan el signo de interrogacion
            if ( n.isLeaf() && n.contenido!=null) lista.add(n.contenido);
            
            if(n.izq!=null && n.der!=null){
                cola.offer(n.izq.raiz);
                cola.offer(n.der.raiz);
            }else if(n.izq!=null){
                cola.offer(n.izq.raiz);
            }
            else if (n.der!=null){
                cola.offer(n.der.raiz);
            }
        }
        return lista;
    }

    public ArrayList<String> enOrden(){

        ArrayList<String> recorrido = new ArrayList<>();

        if( isEmpty() ) return recorrido;

        //Primero izquierda
        if( raiz.izq != null ) recorrido.addAll(raiz.izq.enOrden());
        //Luego la raiz
        recorrido.add(raiz.contenido);
        //Por ultimo derecha
        if( raiz.der != null ) recorrido.addAll(raiz.der.enOrden());

        return recorrido;
    }

    public ArrayList<String> recorridoPorAnchura(){
        ArrayList<String> lista = new ArrayList<>();
        ArrayDeque<Nodo> cola = new ArrayDeque<>();
        cola.offer(raiz);
        while(!cola.isEmpty()){
            Nodo n = cola.poll();
            lista.add(n.contenido);
            if(n.izq!=null && n.der!=null){
                cola.offer(n.izq.raiz);
                cola.offer(n.der.raiz);
            }else if(n.izq!=null){
                cola.offer(n.izq.raiz);
            }
            else if (n.der!=null){
                cola.offer(n.der.raiz);
            }
        }
        return lista;
    }
    
    public int altura(){
        if(isEmpty()) return 0;
        if(isLeaf()) return 1;
        int alturaIzq = (raiz.izq!=null) ? raiz.izq.altura() : 0;
        int alturaDer = 0;
        if(raiz.der!=null) alturaDer = raiz.der.altura();

        return 1 + Math.max( alturaDer, alturaIzq);
    }

    @Override
    public String toString(){
        //En orden por defecto
        if ( isEmpty() ) return "[ ]";
        String texto = "[ ";
        ArrayList<String> contenido = recorridoPorAnchura();

        for ( int i = 0; i < contenido.size() - 1; i++ ){
            texto += contenido.get(i) + ", ";
        }
        texto += contenido.get(contenido.size()-1) + " ]";

        return texto;
    }
}