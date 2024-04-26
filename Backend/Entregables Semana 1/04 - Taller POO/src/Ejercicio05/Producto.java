package Ejercicio05;

public class Producto {
    private int id;
    private String descripcion;
    private int cantStock;

    public Producto(int id, String descripcion, int cantStock) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantStock = cantStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantStock() {
        return cantStock;
    }

    public void setCantStock(int cantStock) {
        this.cantStock = cantStock;
    }

    @Override
    public String toString() {
        return "Producto\n" +
                "id= " + id + "\n" +
                "descripcion= " + descripcion + "\n" +
                "Stock= " + cantStock + "\n";
    }
}
