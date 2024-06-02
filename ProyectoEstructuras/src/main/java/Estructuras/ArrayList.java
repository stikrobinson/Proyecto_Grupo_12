/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author steve
 */
public class ArrayList<E> implements List<E>{
    
    private E[] elementos;
    private int capacidad = 10;
    private int size;
    
    public ArrayList(){
    elementos = (E[]) new Object[capacidad];
    size = 0;
    }
    
    private void addCapacity(){
        E[] nuevoArray = (E[]) new Object[capacidad*2];
        capacidad = capacidad*2;
        int contador = 0;
        for(E elemento: elementos){
            nuevoArray[contador] = elemento;
            contador++;
         }
        elementos = nuevoArray;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        for(int i =0; i<size; i++){
        if(elementos[i].equals(o)){
          return true;
        }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
          Iterator<E> iterador = new Iterator<E>(){
          private int count = 0;
          public boolean hasNext(){
            return count<size;
          }
          public E next(){
            E siguiente = elementos[count];
            count = count + 1;
            return siguiente;
          }
          };
          return iterador;
    }

    @Override
    public boolean add(E e) {
        if(e==null){
            return false;
        }else if(size<capacidad){
            elementos[size] = e;
            size++;
            return true;
        }else{
            addCapacity();
            elementos[size] = e;
            size++;
            return true;
        }
    }
    
    @Override
    public String toString(){
        String string = "{";
        for(int i = 0; i<size; i++){
          if(i==size-1){
            string = string + elementos[i];
          }else{
            string = string + elementos[i] + ",";
          }
        }
        return string + "}";
    }

    @Override
    public boolean remove(Object o) {
        for(int i =0; i<size; i++){
        if(elementos[i].equals(o)){
          remove(i);
          return true;
        }
        
    }
        return false;
    }

    @Override
    public void clear() {
        elementos = (E[]) new Object[capacidad];
        size = 0;
    }

    @Override
    public E get(int index) {
        if(!(index<size && 0<=index)){
            throw new IndexOutOfBoundsException();
         }
        return elementos[index];
    }

    @Override
    public E set(int index, E element) {
        if(!(index<size && 0<=index)){
            throw new IndexOutOfBoundsException();
        }
        E viejo = elementos[index];
        elementos[index]=element;
        return viejo;
    }

    @Override
    public void add(int index, E element) {
    if(element==null){
      return;
    }else if(size+1<capacidad){
      size++;
      E viejo = element;
      for(int i=index; i<size; i++){
        viejo = this.set(i,viejo);
      }
      return;
    }else{
      addCapacity();
      size++;
      E viejo = element;
      for(int i=index; i<size; i++){
        viejo = this.set(i,viejo);
      }
      return;
    }
    }

    @Override
    public E remove(int index) {
     if(!(index<size && 0<=index)){
            throw new RuntimeException("Error");
     }else{
      E antiguo = elementos[index];
      for(int i=index; i<size-1; i++){
        elementos[i] = elementos[i+1];
      }
      size--;
      return antiguo;
    }
    }

    @Override
    public int indexOf(Object o) {
        for(int i =0; i<size; i++){
        if(elementos[i].equals(o)){
          return i;
        }
      }
      return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i =size-1; i>=0; i--){
        if(elementos[i].equals(o)){
          return i;
        }
      }
      return -1;
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c.isEmpty()){
            return false;
        }
        for(E elemento: c){
            this.add(elemento);
        }
        return true;
    }
    
    //métodos sin implementar
    
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
  
}
