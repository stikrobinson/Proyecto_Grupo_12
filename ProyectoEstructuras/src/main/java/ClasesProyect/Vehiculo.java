/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyect;

import Estructuras.ArrayList;

/**
 *
 * @author alexo
 */
public class Vehiculo implements Comparable{
    private String id;
    private double precio; //Precio puede cambiar si el salesman lo requiere
    private String marca;
    private String modelo;
    private String foto; //Direccion de la foto del carro, se puede actualizar la foto
    private int anio;
    private int kilometraje; //Atributo que puede cambiar si el duenio sigue usando el coche hasta que sea vendido
    private String motor;
    private String trasmision;
    private int peso;
    private String ubiActual; //puede ser cambiada
    private ArrayList<Accident> histAccident; //Utilizar un TDA de Strings senialando  accidentes (Tipo dato Accident),,,
    private ArrayList<Servicio> histService; //Utilizar un TDA de Strings senialando reparaciones, procesos de mantenimiento, accidentes,,,
    private TipoVehiculo tipovehiculo; 
    private String duenio;

    public Vehiculo(String id, double precio, String marca, String modelo, String foto, int anio, int kilometraje, String motor, String trasmision, int peso, String ubiActual, ArrayList<Accident> histAccident, ArrayList<Servicio> histService, TipoVehiculo tipovehiculo, String duenio) {
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
        this.tipovehiculo = tipovehiculo;
        this.duenio = duenio;
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

    public int getAnio() {
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

    public int getPeso() {
        return peso;
    }

    public String getUbiActual() {
        return ubiActual;
    }

    public ArrayList<Accident> getHistAccident() {
        return histAccident;
    }

    public ArrayList<Servicio> getHistService() {
        return histService;
    }

    @Override
    public int compareTo(Object o) {
        Vehiculo v = (Vehiculo) o;
        return this.getAnio() - v.getAnio();
    }

}
