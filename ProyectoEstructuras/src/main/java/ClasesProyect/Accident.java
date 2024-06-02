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
public class Accident {
    private LocalDate fecha;
    private String descripcion;
    private Double costoRepa;

    public Accident(LocalDate fecha, String descripcion, Double costoRepa) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.costoRepa = costoRepa;
    }
    
}
