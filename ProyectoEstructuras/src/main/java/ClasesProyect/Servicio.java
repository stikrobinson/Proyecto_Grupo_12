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
    
    
}
