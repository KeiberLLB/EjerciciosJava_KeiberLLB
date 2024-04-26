import java.util.Scanner;

public class Ejercicio03 {
    public static void main(String[] args) {
        /*Sistema de Reservas: Desarrolla un programa para un teatro para gestionar las
        reservas de asientos. El teatro tiene 5 filas con 10 asientos cada una. Utiliza un
        arreglo bidimensional de boolean donde true representa un asiento ocupado y false
        uno disponible. El sistema debe permitir:
        * Reservar y cancelar asientos.
        * Mostrar los asientos disponibles.
        * Contabilizar el total de asientos ocupados y disponibles.*/

        //Creamos la matriz que nos permitirá guardar el estado de todos los asientos
        boolean[][] listaAsientos = new boolean[5][10];

        //Objeto scanner para poder obtener información del usuario por consola
        Scanner objScanner = new Scanner(System.in);

        //Variable para guardar la opción del usuario
        int opcion = 0;

        do {
            objScanner.nextLine();
            System.out.println("ENTER PARA CONTINUAR");
            objScanner.nextLine();
            System.out.println("---- RESERVA DE SILLAS TEATRO ----");
            System.out.println("1. Reserva asiento.");
            System.out.println("2. Cancelar asiento.");
            System.out.println("3. Mostrar asientos disponibles.");
            System.out.println("4. Contabilizar el total de asientos ocupados y disponibles.");
            System.out.println("5. Salir.");
            System.out.println("Ingrese una opción:");

            //Leemos la opción elegida por el usuario
            opcion = objScanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la fila (1-5)");
                    int fila = objScanner.nextInt() - 1;
                    System.out.println("Ingrese el asiento (1-10)");
                    int asiento = objScanner.nextInt() - 1;
                    if (!listaAsientos[fila][asiento]) {
                        listaAsientos[fila][asiento] = true;
                        System.out.println("Asiento reservado correctamente.");
                    } else {
                        System.out.println("El asiento ya se encuentra reservado!");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese la fila (1-5)");
                    fila = objScanner.nextInt();
                    System.out.println("Ingrese el asiento (1-10)");
                    asiento = objScanner.nextInt();
                    if (listaAsientos[fila][asiento]) {
                        listaAsientos[fila][asiento] = false;
                        System.out.println("Reserva de asiento cancelada correctamente");
                    } else {
                        System.out.println("Este asiente ya esta libre");
                    }
                    break;
                case 3:
                    System.out.println("Mostrando asientos disponibles...");
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (!listaAsientos[i][j]) {
                                System.out.println("(" + (i + 1) + " - " + (j + 1) + ")");
                            }
                        }
                    }
                    break;
                case 4:
                    int ocupados = 0, disponibles = 0;
                    for (boolean[] filaAsiento : listaAsientos) {
                        for (boolean asientoOcupado : filaAsiento) {
                            if (!asientoOcupado) {
                                disponibles++;
                            } else {
                                ocupados++;
                            }
                        }
                    }
                    System.out.println("Total de asientos ocupados: " + ocupados);
                    System.out.println("Total de asientos disponibles: " + disponibles);

                    /*int counterD = 0;
                    int counterND = 0;
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (!listaAsientos[i][j]) {
                                counterD++;
                            } else {
                                counterND++;
                            }
                        }
                    }
                    System.out.println("El total de asientos DISPONIBLES es: " + counterD);
                    System.out.println("El total de asientos NO Disponibles es: " + counterND);*/
                    break;
            }
        } while (opcion != 5);
    }
}
