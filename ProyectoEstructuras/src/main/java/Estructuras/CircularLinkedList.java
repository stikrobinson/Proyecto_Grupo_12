/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author steve
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CircularLinkedList<E> implements List<E>{

  private NodeList<E> tail;
  private int size;

  public CircularLinkedList(){
    tail = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size==0;
  }

  public boolean contains(Object o) {
    for(E element: this){
      if(element.equals(o)){
        return true;
      }
    }
    return false;
  }

  public Iterator<E> iterator() {
    Iterator<E> it = new Iterator<E>(){
      NodeList<E> puntero = tail;
      int contador=0;
      public boolean hasNext(){
        return contador!=size;
      }
      public E next(){
        puntero = puntero.getSiguiente();
        E valor = puntero.getElemento();
        contador++;
        return valor;
      }
    };
    return it;
  }

  public boolean add(E e) {
    if(e==null){
      return false;
    }

    if(size==0){
      tail = new NodeList<E>(e);
      tail.setAnterior(tail);
      tail.setSiguiente(tail);
    }else if(size==1){
      NodeList<E> nuevo = new NodeList<E>(e);
      nuevo.setAnterior(tail);
      tail.setSiguiente(nuevo);
      nuevo.setSiguiente(tail);
      tail.setAnterior(nuevo);
      tail = nuevo;
    }else{
      NodeList<E> nuevo = new NodeList<E>(e);
      nuevo.setAnterior(tail);
      nuevo.setSiguiente(tail.getSiguiente());
      tail.getSiguiente().setAnterior(nuevo);
      tail.setSiguiente(nuevo);
      tail = nuevo;
    }
    size++;
    return true;


  }

  public boolean remove(Object o) {
    int contador = 0;
    for(NodeList<E> puntero = tail.getSiguiente();contador<size;puntero = puntero.getSiguiente()){
      if(puntero.getElemento().equals(o)){
        NodeList<E> anterior = puntero.getAnterior();
        NodeList<E> siguiente = puntero.getSiguiente();
        anterior.setSiguiente(siguiente);
        siguiente.setAnterior(anterior);
        puntero = null;
        size--;
        return true;
      }
      contador++;
    }
    return false;
  }

  public void clear() {
    tail = null;
    size = 0;
  }

  public boolean addFirst(E e){
    if(e==null){
      return false;
    }

    if(size==0){
      tail = new NodeList<E>(e);
      tail.setAnterior(tail);
      tail.setSiguiente(tail);
    }else if(size==1){
      NodeList<E> nuevo = new NodeList<E>(e); 
      nuevo.setSiguiente(tail);
      nuevo.setAnterior(tail);
      tail.setSiguiente(nuevo);
      tail.setAnterior(nuevo);
    }else{
      NodeList<E> nuevo = new NodeList<E>(e);
      nuevo.setAnterior(tail);
      nuevo.setSiguiente(tail.getSiguiente());
      tail.getSiguiente().setAnterior(nuevo);
      tail.setSiguiente(nuevo);
      
    }
    size++;
    return true;
  }

  public E get(int index) {
      if(!(index>=0 && index<size)){
        throw new IndexOutOfBoundsException();
      }

      int contador = 0;
      for(E elemento: this){
        if(contador==index){
          return elemento;
        }
        contador++;
      }
      return null;

  }


  public E set(int index, E element) {
    if(!(index>=0 && index<size)){
      throw new IndexOutOfBoundsException();
    }else if(element == null){
      return null;
    }
    int contador = 0;
    for(NodeList<E> puntero = tail.getSiguiente();puntero!=null;puntero = puntero.getSiguiente()){
      if(contador==index){
        E valor = puntero.getElemento();
        puntero.setElemento(element);
        return valor;
      }
      contador++;
    }
    return null;

  }

  public void add(int index, E element) {
    if(!(index>=0 && index<size) && !(index==0 && size==0)){
      throw new IndexOutOfBoundsException();
    }else if(element == null){
      return;
    }

    if(index==0){
      addFirst(element);
      return;
    }
    
    int contador = 0;
    for(NodeList<E> puntero = tail.getSiguiente();contador<size;puntero = puntero.getSiguiente()){
      if(contador==index){
        NodeList<E> nuevo = new NodeList<E>(element);
        NodeList<E> anterior = puntero.getAnterior();
        nuevo.setSiguiente(puntero);
        nuevo.setAnterior(anterior);
        anterior.setSiguiente(nuevo);
        puntero.setAnterior(nuevo);
        size++;
        return;
      }
      contador++;
    }
  }
  
  public E remove(int index) {
    if(!(index>=0 && index<size) && !(index==0 && size==0)){
      throw new IndexOutOfBoundsException();
    }

    int contador = 0;
    for(NodeList<E> puntero = tail.getSiguiente();puntero!=null;puntero = puntero.getSiguiente()){
      if(contador==index){
        E valor = puntero.getElemento();
        NodeList<E> anterior = puntero.getAnterior();
        NodeList<E> siguiente = puntero.getSiguiente();
        anterior.setSiguiente(siguiente);
        siguiente.setAnterior(anterior);
        puntero = null;
        size--;
        return valor;
      }
      contador++;
  }
    return null;
  }

  public int indexOf(Object o) {
    int indice = 0;
    for(E element: this){
      if(element.equals(o)){
        return indice;
      }
      indice++;
    }
    return -1;
  }

  public int lastIndexOf(Object o) {
    int indice = size-1;
    for(NodeList<E> puntero = tail; puntero!=null;puntero = puntero.getAnterior()){
      if(puntero.getElemento().equals(o)){
        return indice;
      }
      indice--;
    }
    return -1;
  }

  public ListIterator<E> listIterator() {
    ListIterator<E> it = new ListIterator<E>(){
      NodeList<E> puntero = tail.getSiguiente();
      int indice = 0;
      boolean arranque = false;

      public boolean hasNext(){
        return puntero.getSiguiente()!=null;
      }
      public boolean hasPrevious(){
        return puntero.getAnterior()!=null;
      }
      public E next(){
        if(arranque){
          puntero = puntero.getSiguiente();
        }
        E valor = puntero.getElemento();
        arranque = true;
        indice++;
        return valor;
      }
      public int nextIndex(){
        return indice;
      }
      public E previous(){
        puntero = puntero.getAnterior();
        E valor = puntero.getElemento();
        indice--;
        return valor;
      }
      public int previousIndex(){
        return indice;
      }
      public void add(E e){
        CircularLinkedList.this.add(indice,e);
      }
      public void remove(){
        CircularLinkedList.this.remove(indice);
      }
      public void set(E e){
        CircularLinkedList.this.set(indice,e);
      }

    };
    return it;
  }

  public String toString(){
    String string = "{";
    int contador = 1;
    for(E i: this){
      if(contador!=size){
        string = string + i + ", ";
      }else{
        string = string + i;
      }
      contador++;
    }
    return string+"}";
  }

  //métodos sin implementar

  public Object[] toArray() {
    // TODO Auto-generated method stub
    return null;
  }

  public <T> T[] toArray(T[] a) {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean containsAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean addAll(Collection<? extends E> c) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean addAll(int index, Collection<? extends E> c) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean removeAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean retainAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  public ListIterator<E> listIterator(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<E> subList(int fromIndex, int toIndex) {
    // TODO Auto-generated method stub
    return null;
  }

}
