/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyect;

/**
 *
 * @author alexo
 */
public class Vehiculo implements Saveable, SearchFilter{
    private String id;
    private double precio; //Precio puede cambiar si el salesman lo requiere
    private String marca;
    private String modelo;
    private String foto; //Direccion de la foto del carro, se puede actualizar la foto
    private String anio;
    private int kilometraje; //Atributo que puede cambiar si el duenio sigue usando el coche hasta que sea vendido
    private String motor;
    private String trasmision;
    private String peso;
    private String ubiActual; //puede ser cambiada
    private String histAccident; //Utilizar un TDA de Strings senialando  accidentes (Tipo dato Accident),,,
    private String histService; //Utilizar un TDA de Strings senialando reparaciones, procesos de mantenimiento, accidentes,,,

    public Vehiculo(String id, double precio, String marca, String modelo, String foto, String anio, int kilometraje, String motor, String trasmision, String peso, String ubiActual, String histAccident, String histService) {
        this.id = id;
        this.precio = precio;
        this.marca = marca;
        this.modelo = modelo;
        this.foto = foto;
        this.anio = anio;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.trasmision = trasmision;
        this.peso = peso;
        this.ubiActual = ubiActual;
        this.histAccident = histAccident;
        this.histService = histService;
    }

    public String getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getFoto() {
        return foto;
    }

    public String getAnio() {
        return anio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public String getMotor() {
        return motor;
    }

    public String getTrasmision() {
        return trasmision;
    }

    public String getPeso() {
        return peso;
    }

    public String getUbiActual() {
        return ubiActual;
    }

    public String getHistAccident() {
        return histAccident;
    }

    public String getHistService() {
        return histService;
    }

    
    
    
    
}
