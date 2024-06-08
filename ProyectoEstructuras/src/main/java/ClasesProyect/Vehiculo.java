/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesProyect;

import Estructuras.ArrayList;
import com.mycompany.proyectoestructuras.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

/**
 *
 * @author alexo
 */
public class Vehiculo implements Comparable<Vehiculo>{
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

    public TipoVehiculo getTipovehiculo() {
        return tipovehiculo;
    }

    public void setTipovehiculo(TipoVehiculo tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
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
    public int compareTo(Vehiculo v) {
        return this.getAnio() - v.getAnio();
    }
    
    @Override
    public boolean equals (Object o) {
        if(o==this){
            return true;
        }
        if(o!=null && getClass()==o.getClass()){
            Vehiculo other = (Vehiculo) o;
            return other.getId().equals(o);
        }else{
            return false;
        }
    }
    
    public static ArrayList<Vehiculo> cargarVehiculos(){
    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try{
            FileReader reader = new FileReader("src/main/resources/com/mycompany/proyectoestructuras/vehiculos.csv");
            BufferedReader bf = new BufferedReader(reader);
            String line;
            bf.readLine();
            while((line=bf.readLine())!=null){
                String[] division = line.split(",");
                String id = division[0];
                double precio = Double.valueOf(division[1]);
                String marca = division[2];
                String modelo = division[3];
                String foto = division[4]; 
                int anio = Integer.valueOf(division[5]);
                int kilometraje = Integer.valueOf(division[6]);
                String motor = division[7];
                String trasmision = division[8];
                int peso = Integer.valueOf(division[9]);
                String ubiActual = division[10];
                ArrayList<Accident> histAccident = new ArrayList<>(); 
                if(!division[11].equals("null")){
                    String[] accidentes = division[11].split("/");
                    for(String s: accidentes){
                    String[] subdivision = s.split(";");
                    String[] divisionFecha = subdivision[0].split("-");
                    LocalDate fecha = LocalDate.of(Integer.valueOf(divisionFecha[2]),Integer.valueOf(divisionFecha[1]),Integer.valueOf(divisionFecha[0]));
                    histAccident.add(new Accident(fecha,subdivision[1],Double.valueOf(subdivision[2])));
                    }
                }
                ArrayList<Servicio> histService = new ArrayList<>(); 
                String[] servicios = division[12].split("/");
                for(String s: servicios){
                    String[] subdivision = s.split(";");
                    String[] divisionFecha = subdivision[0].split("-");
                    LocalDate fecha = LocalDate.of(Integer.valueOf(divisionFecha[2]),Integer.valueOf(divisionFecha[1]),Integer.valueOf(divisionFecha[0]));
                    histService.add(new Servicio(fecha,subdivision[1],Double.valueOf(subdivision[2]),subdivision[3]));
                }
                TipoVehiculo tipovehiculo = TipoVehiculo.valueOf(division[13]); 
                String duenio = division[14];
                Vehiculo vehiculo = new Vehiculo(id,precio,marca,modelo,foto,anio,kilometraje,motor,trasmision,peso,ubiActual,histAccident,histService,tipovehiculo,duenio);
                vehiculos.add(vehiculo);
            }
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return vehiculos;
    }
    
    public static void actualizarVehiculos(){
        try{
            FileWriter writer = new FileWriter("src/main/resources/com/mycompany/proyectoestructuras/vehiculos.csv");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write("id,precio,marca,modelo,foto,anio,kilometraje,motor,transmision,peso,ubiActual,histAccident,histService,tipoVehiculo,duenio");
            bw.newLine();
            for(Vehiculo v: App.VEHICULOS){
            String histAccident = "";
            String histService = "";
            int contador1 = 0;
            if(v.getHistAccident().isEmpty()){
                histAccident = histAccident + "null";
            }else{
                for(Accident a: v.getHistAccident()){
                    histAccident = histAccident + a.getFecha().getDayOfMonth()+"-"+a.getFecha().getMonthValue()+"-"+a.getFecha().getYear()+";"+a.getDescripcion()+";"+a.getCostoRepa();
                    if(contador1<v.getHistAccident().size()-1){
                        histAccident = histAccident + "/";
                    }
                    contador1++;
                }
            }
            int contador2 = 0;
            for(Servicio s: v.getHistService()){
                histService = histService + s.getFecha().getDayOfMonth()+"-"+s.getFecha().getMonthValue()+"-"+s.getFecha().getYear()+";"+s.getTipo()+";"+s.getCosto()+";"+s.getDetalles();
                if(contador2<v.getHistService().size()-1){
                    histService = histService + "/";
                }
                contador2++;
            }
            bw.write(v.getId()+","+v.getPrecio()+","+v.getMarca()+","+v.getModelo()+","+v.getFoto()+","+v.getAnio()+","+v.getKilometraje()+","+v.getMotor()+","+v.getTrasmision()+","+v.getPeso()+","+v.getUbiActual()+","+histAccident+","+histService+","+v.getTipovehiculo()+","+v.getDuenio());
            bw.newLine();
            }
            bw.close();
            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
