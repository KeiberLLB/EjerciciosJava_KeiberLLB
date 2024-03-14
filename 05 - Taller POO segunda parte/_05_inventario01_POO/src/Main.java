import javax.swing.*;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Inventario objInventario = new Inventario();

        int option = 0;

        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, "MENU \n" +
                        "1. Registrar nuevo producto\n" +
                        "2. Eliminar producto\n" +
                        "3. Buscar producto por nombre\n" +
                        "4. Listar productos\n" +
                        "5. Salir\n" +
                        "Elige una opcion:\n"
                ));
                switch (option) {
                    case 1:
                        try {
                            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id del producto"));
                            String nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre del producto");
                            int precio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el precio del producto"));
                            Producto nuevoProducto = new Producto(id, nombre, precio);
                            objInventario.agregarProducto(nuevoProducto);
                            JOptionPane.showMessageDialog(null, "Producto registrado con exito!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Carácter no valido");
                        }
                        break;
                    case 2:
                        try {
                            int idE = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el ID del producto a eliminar"));
                            if (objInventario.eliminarProducto(idE)){
                                JOptionPane.showMessageDialog(null,"Producto eliminado exitosamente");
                            }else {
                                JOptionPane.showMessageDialog(null,"Producto no encontrado");
                            };
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Carácter no valido");
                        }
                        break;
                    case 3:
                        String nombreS = JOptionPane.showInputDialog(null,"Ingresa el nombre del producto: ");
                        JOptionPane.showInputDialog(objInventario.buscarPorNombre(nombreS));
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, objInventario.listarProductos());
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Carácter no valido");
            }
        } while (option != 5);
    }
}