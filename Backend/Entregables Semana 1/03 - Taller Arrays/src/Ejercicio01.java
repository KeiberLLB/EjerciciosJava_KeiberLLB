import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Ejercicio01 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        //Ejercicio 1
        /*Calculadora de Promedios: Escribe un programa que utilice un array de tipo
        double para almacenar las calificaciones finales de 10 estudiantes en un curso. El
        programa debe calcular y mostrar el promedio de estas calificaciones.*/
        /* control shift / (el / del teclado numérico )*/

        //creamos nuestro objeto escaner
        Scanner objScanner = new Scanner(System.in);

        //Creamos nuestro array que guardara las notas de los estudiantes
        double[] notas = new double[10];
        //variable para guardar la suma total
        double sumaTotal = 0;

        //Ciclo for para pedir al usuario las notas de los 10 estudiantes
        for (int i = 0; i < 10; i++) {
            System.out.println("Ingrese la nota del estudiante #" + (i + 1));
            try {
                notas[i] = objScanner.nextDouble();
                sumaTotal += notas[i];
            } catch (Exception e) {
                System.out.println("Nota no válida");
            }
        }
        ;
        //variable para guardar el promedio general de las notas
        double promedio = sumaTotal / notas.length;
        System.out.println("El promedio de todas las notas es: " + promedio);

        for (int i = 0; i < notas.length; i++) {
            System.out.println(notas[i]);
        }
        ;
    }
}