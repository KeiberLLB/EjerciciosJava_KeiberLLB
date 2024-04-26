package Ejercicio06;

import Ejercicio05.Producto;

import javax.swing.*;
import java.util.ArrayList;

public class Estudiante {
    private ArrayList<String> calificaciones;
    private String nombre;

    public Estudiante() {
        this.calificaciones = new ArrayList<>();
    }

    public ArrayList<String> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<String> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //metodos
    public void nuevoEstudiante(){
        this.nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre del estudiante");
    }

    public void nuevaCalf(){
        String calf = JOptionPane.showInputDialog("Ingresa una nueva calificacion: ");
        this.calificaciones.add(calf);
    }

    public void promedio(){
        double cProm =0;
        for(String calf : calificaciones){
            cProm += Double.parseDouble(calf);
        }
        double prom = calificaciones.toArray().length;
        double calfProm = cProm/prom;
        JOptionPane.showMessageDialog(null, "El promedio del Estudiante es: "+calfProm);
    }
}
