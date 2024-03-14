package Ejercicio05;

import javax.swing.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int option = 0;
        Random rand = new Random();
        StockAlmacen metodos = new StockAlmacen();
        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, "MENÚ DE OPCIONES\n" +
                        "1. Agregar Producto\n" +
                        "2. Eliminar Producto\n" +
                        "3. Verificar Stock\n" +
                        "4. Salir\n" +
                        "Elige una opción: "));
                switch (option) {
                    case 1:
                        int ID = rand.nextInt();
                        String nombreNP = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto: ");
                        int cantNP = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad a registrar en Stock: "));
                        Producto producto = new Producto(ID, nombreNP, cantNP);
                        metodos.agregarProducto(producto);
                        JOptionPane.showMessageDialog(null, "Producto registrado correctamente!");
                        break;
                    case 2:
                        String nombreE = metodos.listarStock();
                        if (metodos.eliminarProducto(nombreE)){
                            JOptionPane.showMessageDialog(null,"Producto eliminado!");
                        }else {
                            JOptionPane.showMessageDialog(null,"No se puedo eliminar el producto...");
                        }
                        break;
                    case 3:
                        String nombreS = metodos.listarStock();
                        metodos.verificarStock(nombreS);
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Caracter invalido!");
            }
        } while (option != 4);
    }
}
