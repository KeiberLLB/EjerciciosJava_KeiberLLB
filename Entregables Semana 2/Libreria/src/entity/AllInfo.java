package entity;

public class AllInfo {
    private String nombre;
    private String nacionalidad;
    private String titulo;
    private int año_publicacion;
    private double precio;

    public AllInfo() {
    }

    public AllInfo(String nombre, String nacionalidad, String titulo, int año_publicacion, double precio) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.titulo = titulo;
        this.año_publicacion = año_publicacion;
        this.precio = precio;
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
}
