import javax.swing.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        /*Contador Simple: Escribe un programa que utilice un bucle for para contar del 1 al 10 e
        imprimir cada número en la consola.*/
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        /*Suma de Números: Utiliza un bucle while para sumar los números del 1 al 100 e imprimir
        el resultado.*/
        int contador = 0;
        int sumaTotal = 0;
        while (contador <= 100) {
            sumaTotal += contador;
            contador++;
        }
        System.out.println(sumaTotal);

        /*Tabla de Multiplicar: Utiliza un bucle for anidado para imprimir la tabla de multiplicar
        del 1 al 10.*/
        for (int t = 1; t <= 10; t++) {
            System.out.println("\n" + "Tabla del " + t + "\n");
            for (int i = 1; i <= 10; i++) {
                System.out.println(t + " x " + i + " = " + (t * i));
            }
        }

        /*Sistema de Menú Interactivo: Desarrolla un sistema de menú interactivo que muestre
        diferentes opciones al usuario (por ejemplo, 1. Ver saldo 2. Depositar dinero 3. Retirar
        dinero 4. Salir). Utiliza un bucle para permitir al usuario interactuar con el menú hasta que
        elija salir.*/
        String option;
        double saldo = 0;
        do {
            option = JOptionPane.showInputDialog(null, "\n\n MENU DE OPCIONES\n" +
                    "1. Ver Saldo\n" +
                    "2. Depositar Dinero\n" +
                    "3. Retirar\n" +
                    "4. Salir\n" +
                    "Ingrese una opcion: ");
            switch (option) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Tu Saldo es: $" + saldo);
                    break;
                case "2":
                    try {
                        double consig = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el valor a depositar \n" + "$"));
                        saldo += consig;
                        JOptionPane.showMessageDialog(null, "Dinero consignado");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "El valor a consignar no es valido!");
                    }
                    break;
                case "3":
                    try {
                        double retiro = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el valor a retirar \n" + "$"));
                        if (retiro > saldo) {
                            JOptionPane.showMessageDialog(null, "Saldo Insuficiente!");
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Retirar Efectivo ...\n");
                            saldo -= retiro;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "El valor a retirar no es valido!");
                    }
                    break;
            }
        } while (!option.equals("4"));

        /*Validación de Entrada de Usuario: Escribe un programa que solicite al usuario que
        ingrese su edad. Si el usuario ingresa un valor que no es un número válido o está fuera de
        un rango razonable (por ejemplo, 0-120), el programa debe pedirle al usuario que intente de
        nuevo. Utiliza un bucle para seguir solicitando la entrada hasta que sea válida.*/
        boolean verificacion = false;
        do {
            try {
                double edad = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa tu edad: "));
                if (edad > 0 && edad <= 120) {
                    JOptionPane.showMessageDialog(null, "Tu edad es: " + edad);
                    verificacion = true;
                } else {
                    JOptionPane.showMessageDialog(null, "La edad ingresada no es valida");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La edad ingresada no es valida");
            }
        } while (!verificacion);

        /*Verificar un Número Primo: Escribe un programa que utilice un bucle para verificar si un
        número dado es primo o no.*/
        double numero = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el numero a verificar si es primo: "));

        if (numero == 0 || numero == 1 || numero == 4) {
            JOptionPane.showMessageDialog(null, "El numero ingresado no es primo");
        } else {
            boolean esPrimo = true;
            for (int x = 2; x <= numero / 2; x++) {
                // Si es divisible por cualquiera de estos números, no
                // es primo
                if (numero % x == 0) {
                    esPrimo = false;
                    break;
                }
            }
            if (esPrimo) {
                JOptionPane.showMessageDialog(null, "El numero " + numero + " es primo");
            } else {
                JOptionPane.showMessageDialog(null, "El numero " + numero + " no es primo");
            }
        }

        /*Juego de Adivinanzas: Crea un pequeño juego de adivinanzas donde el usuario debe
        adivinar un número generado aleatoriamente. Utiliza un bucle do-while para permitir al
        usuario seguir intentando hasta que adivine el número.*/
        Random rand = new Random();
        int numeroAdivinar = rand.nextInt(100) + 1; // Genera un número aleatorio entre 1 y 100
        int intentos = 0;
        int maxIntetos = 7;
        int intentoUsuario;
        JOptionPane.showMessageDialog(null, "¡Bienvenido al Juego de Adivinanzas!\n" +
                "Trata de adivinar un número entre 1 y 100.");
        do {
            intentoUsuario = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu intento:"));
            if (intentoUsuario < numeroAdivinar) {
                JOptionPane.showMessageDialog(null, "El número a adivinar es mayor.");
            } else if (intentoUsuario > numeroAdivinar) {
                JOptionPane.showMessageDialog(null, "El número a adivinar es menor.");
            }
            intentos++;
        } while (intentoUsuario != numeroAdivinar && maxIntetos != intentos);

        if (intentoUsuario == numeroAdivinar) {
            JOptionPane.showMessageDialog(null, "¡Felicidades! Has adivinado el número " + numeroAdivinar +
                    " en " + intentos + " intentos.");
        } else {
            JOptionPane.showMessageDialog(null, "No tienes mas intentos! Perdiste");
        }

        /*Cálculo de Interés Compuesto: Crea un programa que calcule el crecimiento de una
        inversión bajo un esquema de interés compuesto. El usuario debe poder ingresar el capital
        inicial, la tasa de interés anual y el número de años. Utiliza un bucle para calcular y mostrar
        el saldo de la inversión al final de cada año.*/
        double inversion = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingresa la inversion que deseas realizar : $"));
        double interes = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingresa la tasa de interes anual: "));
        double años = Double.parseDouble(JOptionPane.showInputDialog(null, "Por cuantos años?"));
        int counter=0;

        for (int i =0;i<=años;i++){
            inversion = inversion + inversion*(interes/100);
            System.out.println(inversion);
        }
        JOptionPane.showMessageDialog(null,"Saldo final: $"+inversion);

    }
}