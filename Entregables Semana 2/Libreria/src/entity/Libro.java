package entity;

import model.AutorModel;

public class Libro {
    private int id;
    private String titulo;
    private int año_publicacion;
    private double precio;
    private int id_autor;


    public Libro() {
    }


    public Libro(int id, String titulo, int año_publicacion, double precio, int id_autor) {
        this.id = id;
        this.titulo = titulo;
        this.año_publicacion = año_publicacion;
        this.precio = precio;
        this.id_autor = id_autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño_publicacion() {
        return año_publicacion;
    }

    public void setAño_publicacion(int año_publicacion) {
        this.año_publicacion = año_publicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }


    @Override
    public String toString() {
        AutorModel objAutor = new AutorModel();
        Autor obj = (Autor) objAutor.findById(id_autor);
        return "Libro " + "\n" +
                "id= " + id + "\n" +
                "titulo= " + titulo + '\n' +
                "año publicación=" + año_publicacion + "\n" +
                "precio= " + precio + "\n" + obj.getNombre() + "\n";
    }
}
