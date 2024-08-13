/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author alexo
 */
public class BTree {
    private Node raiz;

    public BTree(){
        clear();
    }
    
    public BTree(String r){
        raiz = new Node(r);
    }
    
    public BTree(String r, boolean b){
        raiz = new Node(r);
        raiz.animal = b;
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
    public BTree getIzq(){
        return raiz.izq;
    }
    public BTree getDer(){
        return raiz.der;
    }


private void anadirHojas(String s){
        ArrayDeque<Node> cola = new ArrayDeque<>();
        cola.offer(raiz);
        while(!cola.isEmpty()){
            Node n = cola.poll();
            if(n.izq!=null && n.der!=null){
                cola.offer(n.izq.raiz);
                cola.offer(n.der.raiz);
            }else if(n.izq!=null){
                cola.offer(n.izq.raiz);
            }
            else if (n.der!=null){
                cola.offer(n.der.raiz);
            }else if(n.isLeaf()){
                n.der = new BTree();
                n.der.raiz = new Node(s);
                n.izq = new BTree();
                n.izq.raiz = new Node(s);
            }
        }
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
        ArrayDeque<Node> cola = new ArrayDeque<>();
        cola.offer(raiz);
        while(!cola.isEmpty()){
            Node n = cola.poll();
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
    
    public void builderArbol(LinkedList<String> questionList, HashMap<String, LinkedList<String>> ansMap){
        construirArbol1(questionList);
        construirArbol2(ansMap);
    }

    private void construirArbol1(LinkedList<String> questionList) {
        if (questionList.isEmpty()) return;
        this.raiz = new Node(questionList.poll()); // Inicializar el nodo raíz con la primera pregunta

        while (!questionList.isEmpty()) {
            String question = questionList.poll();
            anadirHojas(question);
        }
    }
    
    public void construirArbol2(HashMap<String, LinkedList<String>> animalMap) {
        for (HashMap.Entry<String, LinkedList<String>> entrada : animalMap.entrySet()) {
            String animal = entrada.getKey();
            LinkedList<String> respuestas = entrada.getValue();
            añadirEnHoja(respuestas, animal);
        }
    }
    
    private void añadirEnHoja(LinkedList<String> respuestas, String animal) {
        Node nodoActual = raiz;
        if (nodoActual == null) {
            // Si el árbol está vacío, crea la raíz
            raiz = new Node(animal);
            raiz.animal = true;
            return;
        }
        for (String respuesta : respuestas) {
            if (respuesta.equals("sí")) {
                if (nodoActual.izq == null) {
                    nodoActual.izq = new BTree(animal, true);
                    return;
                } else {
                    nodoActual = nodoActual.izq.raiz;
                }
            } else if (respuesta.equals("no")) {
                if (nodoActual.der == null) {
                    nodoActual.der = new BTree(animal, true);
                    return;
                } else {
                    nodoActual = nodoActual.der.raiz;
                }
            }
        }
        // Si llegamos a una hoja y aún hay respuestas, asignamos el animal
        if (nodoActual.izq == null && nodoActual.der == null) {
            nodoActual.contenido = animal;
            nodoActual.animal = true;
        }
    }
    
    public ArrayList<String> getLeaves() {
    ArrayList<String> hojas = new ArrayList<>();
    if (raiz == null) {
        return hojas; // Retorna una lista vacía si el árbol está vacío
    }

    ArrayDeque<Node> cola = new ArrayDeque<>();
    cola.offer(raiz);

    while (!cola.isEmpty()) {
        Node n = cola.poll();
        
        if (n.isLeaf() && n.animal) {
            hojas.add(n.contenido);
        } else {
            // Añadir los hijos a la cola para continuar el recorrido
            if (n.izq != null) {
                cola.offer(n.izq.raiz);
            }
            if (n.der != null) {
                cola.offer(n.der.raiz);
            }
        }
    }

    return hojas;
}
}