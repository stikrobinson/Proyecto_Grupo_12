/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author steve
 */
public class NodeList<E>{
  private E elemento;
  private NodeList<E> anterior;
  private NodeList<E> siguiente;

  public NodeList(E elemento){
    this.elemento = elemento;
    anterior = null;
    siguiente = null;
  }

  public E getElemento() {
  	return elemento;
  }
  
  public void setElemento(E elemento) {
  	this.elemento = elemento;
  }
  
  public NodeList<E> getAnterior() {
  	return anterior;
  }
  
  public void setAnterior(NodeList<E> anterior) {
  	this.anterior = anterior;
  }
  
  public NodeList<E> getSiguiente() {
  	return siguiente;
  }
  
  public void setSiguiente(NodeList<E> siguiente) {
  	this.siguiente = siguiente;
  }

}
