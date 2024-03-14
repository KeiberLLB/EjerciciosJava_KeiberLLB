import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> listaProductos;

    public Inventario() {
        this.listaProductos = new ArrayList<>();
    }

    //metodos
    public void agregarProducto(Producto producto) {
        this.listaProductos.add(producto);
    }

    public boolean eliminarProducto(int id) {
        return listaProductos.removeIf(producto -> producto.getId() == id);
    }

    public String listarProductos() {
        String productos = "";
        for (Producto producto : this.listaProductos) {
            productos += producto.toString();
        }
        return productos;
    }
    public Producto buscarPorNombre(String nombreBuscar) {
        for (Producto objProducto : this.listaProductos) {
            if (objProducto.getNombre().equalsIgnoreCase(nombreBuscar)) {
                return objProducto;
            }
        }
        return null;
    }
}
