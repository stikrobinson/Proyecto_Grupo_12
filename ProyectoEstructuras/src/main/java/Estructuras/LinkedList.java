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
public class LinkedList<E> implements List<E>{

  private NodeList<E> head;
  private NodeList<E> tail;
  private int size;

  public LinkedList(){
    head = null;
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
			NodeList<E> puntero = head;
			public boolean hasNext(){
				return puntero!=null;
			}
			public E next(){
				E valor = puntero.getElemento();
				puntero = puntero.getSiguiente();
				return valor;
			}
		};
		return it;
	}

	public boolean add(E e) {
		if(e==null){
			return false;
		}
		
		if(head==null){
			head = new NodeList<E>(e);
		}else if(tail==null){
			tail = new NodeList<E>(e);
			tail.setAnterior(head);
			head.setSiguiente(tail);
		}else{
			NodeList<E> nuevo = new NodeList<E>(e);
			nuevo.setAnterior(tail);
			tail.setSiguiente(nuevo);
			tail = nuevo;
		}
		size++;
		return true;
		
		
	}

	public boolean remove(Object o) {
		for(NodeList<E> puntero = head;puntero!=null;puntero = puntero.getSiguiente()){
			if(puntero.getElemento().equals(o)){
				NodeList<E> anterior = puntero.getAnterior();
				NodeList<E> siguiente = puntero.getSiguiente();
				anterior.setSiguiente(siguiente);
				siguiente.setAnterior(anterior);
				puntero = null;
				size--;
				return true;
			}
		}
		return false;
	}

	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	public boolean addFirst(E e){
		if(e==null){
			return false;
		}

		if(head==null && tail==null){
			head = new NodeList<E>(e);
		}else if(head != null && tail==null){
			NodeList<E> nuevo = new NodeList<E>(e); 
			tail = head;
			tail.setAnterior(nuevo);
			head = nuevo;
			head.setSiguiente(tail);
		}else{
			NodeList<E> nuevo = new NodeList<E>(e);
			nuevo.setSiguiente(head);
			head.setAnterior(nuevo);
			head = nuevo;
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
		for(NodeList<E> puntero = head;puntero!=null;puntero = puntero.getSiguiente()){
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
		}else if(index==size-1){
			add(element);
			return;
		}
		
		int contador = 0;
		for(NodeList<E> puntero = head;puntero!=null;puntero = puntero.getSiguiente()){
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
		for(NodeList<E> puntero = head;puntero!=null;puntero = puntero.getSiguiente()){
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
			NodeList<E> puntero = head;
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
				indice++;
				arranque = true;
				return valor;
			}
			public int nextIndex(){
				if(!hasNext()){
					return -1;
				}
				return indice;
			}
			public E previous(){
				puntero = puntero.getAnterior();
				E valor = puntero.getElemento();
				indice--;
				return valor;
			}
			public int previousIndex(){
				if(!hasPrevious()){
					return -1;
				}
				return indice-2;
			}
			public void add(E e){
				LinkedList.this.add(indice,e);
			}
			public void remove(){
				LinkedList.this.remove(indice);
			}
			public void set(E e){
				LinkedList.this.set(indice,e);
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
