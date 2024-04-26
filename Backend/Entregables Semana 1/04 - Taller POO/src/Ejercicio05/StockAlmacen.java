package Ejercicio05;

import javax.swing.*;
import java.util.ArrayList;

public class StockAlmacen {
    private ArrayList<Producto> stockAlmacen;

    public StockAlmacen() {
        this.stockAlmacen = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.stockAlmacen.add(producto);
    }

    public boolean eliminarProducto(String nombre) {
        return stockAlmacen.removeIf(producto -> producto.getDescripcion().equalsIgnoreCase(nombre));
    }

    public void verificarStock(String id) {
        for (Producto producto : stockAlmacen) {
            if (producto.getDescripcion().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(null, "El stock del producto " + producto.getDescripcion() + " es: " + producto.getCantStock());
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado! ");
            }
        }
    }

    public String listarStock() {
        String productosPrint = "";
        for (Producto producto : stockAlmacen) {
            productosPrint += "Nombre: " + producto.getDescripcion() + "\n" +
                    "ID: " + producto.getId() + "\n";
        }
        String nombre = JOptionPane.showInputDialog(productosPrint + "\n Digita el nombre del producto: ");
        return nombre;
    }
}
