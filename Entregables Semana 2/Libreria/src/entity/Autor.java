package entity;

import model.LibroModel;
import entity.Libro;


public class Autor {
    private int id;
    private String nombre;
    private String nacionalidad;

    public Autor() {
    }

    public LibroModel objLibro;

    public Autor(int id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        String libros = "";
        for (Object libro : objLibro.findAll()){
            Libro obj = (Libro) libro;
            libros+=obj.getTitulo()+"\n";
        }
        return "Autor: " +"\n"+
                "id= " + id +"\n"+
                "nombre= " + nombre + '\n' +
                "nacionalidad= " + nacionalidad + '\n'+
                libros+"\n";
    }
}
