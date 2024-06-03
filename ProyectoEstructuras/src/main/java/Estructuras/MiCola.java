package Estructuras;

public class MiCola <E> {
    private NodeList primero, ultimo;
    
    public MiCola(){
        primero = null; ultimo = null;
    }
    
    public boolean offer(E e){
        if (e == null) return false;        
        NodeList nuevo = new NodeList(e);
        if(primero == null) {
            primero = nuevo; ultimo = nuevo;
        }
        else if (primero == ultimo){
            primero.setSiguiente(nuevo); ultimo = nuevo;
        }
        else{
            ultimo.setSiguiente(nuevo); 
            ultimo = nuevo;
        }
        return true;
    }
    
    public NodeList poll(){
        NodeList poll = primero;        
        if (primero == ultimo){
            primero = null; ultimo = null;
        }
        else{
            primero = primero.getSiguiente();
        }
        return poll;
    }
    
    public E roundRobin(){
        E actual = (E) this.poll().getElemento();
        this.offer(actual);
        return actual;
    }
    
    public boolean isEmpty(){
        return primero == null;
    }
}
