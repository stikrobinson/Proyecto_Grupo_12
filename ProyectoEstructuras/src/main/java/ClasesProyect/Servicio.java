/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyect;

import java.time.LocalDate;

/**
 *
 * @author alexo
 */
public class Servicio {
    private LocalDate fecha;
    private String tipo;
    private double costo;
    private String detalles;

    public Servicio(LocalDate fecha, String tipo, double costo, String detalles) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.costo = costo;
        this.detalles = detalles;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    
    
    
    
}
