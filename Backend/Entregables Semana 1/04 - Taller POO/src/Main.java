import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Libro objLibro = new Libro(
                "100 años de soledad",
                "Gabriel Garcia Marquez",
                "mayo de 1967",
                false);

        //objLibro.setTitulo("El rastro de tu sangre en la nieve");
        System.out.println(objLibro.getTitulo() + " " + objLibro.getAutor());
        System.out.println(objLibro.toString());

        Empleado objEmpleado = new Empleado(
                "Keiber Lázaro",
                "Junior",
                800,
                1090440363);

        Scanner objScaner = new Scanner(System.in);
        double aumento = objScaner.nextDouble();

        objEmpleado.upSalario(aumento);
        System.out.println(objEmpleado.getSalario());



        CuentaBancaria objCuenta = new CuentaBancaria();
        int option = 0;
        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, "MENÚ PRINCIPAL \n" +
                        "1. Registrar Usuario\n" +
                        "2. Menú Bancario\n"+
                        "3. Salir"
                ));
                switch (option){
                    case 1:
                        int numCuenta = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese numero cuenta: "));
                        String nombreTitular = JOptionPane.showInputDialog(null,"Ingrese nombre titular cuenta: ");

                        int saldoCuenta =Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese su saldo inicial: "));
                        objCuenta = new CuentaBancaria(
                                numCuenta,
                                nombreTitular,
                                saldoCuenta
                        );
                        objCuenta.menuUsuario();
                        break;
//                    case 2:
//                        objCuenta.menuUsuario();
//                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Caracteres no validos");
            }
        } while (option != 3);

    }
}