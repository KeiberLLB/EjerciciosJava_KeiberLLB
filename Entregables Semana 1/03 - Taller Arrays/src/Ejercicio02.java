import java.util.Scanner;
public class Ejercicio02 {
    public static void main(String[] args) {
        /*Buscador de Número Mayor y Menor: Utiliza un array de int para almacenar 5
        números enteros ingresados por el usuario (puedes usar la clase Scanner para la
        entrada de datos). El programa debe encontrar y mostrar el número mayor y el
        menor de la lista.*/
        Scanner objScanner = new Scanner(System.in);
        int[] numeros = new int[5];
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Ingrese un numero entero: ");
            try {
                numeros[i] = objScanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un numero entero!");
            }
        }
        int mayor = numeros[0];
        int menor = numeros[1];
        for (int i : numeros) {
            if (mayor < i) {
                mayor = i;
            } else if (menor > i) {
                menor = i;
            }
        }
        System.out.println("numero mayor " + mayor);
        System.out.println("numero menor " + menor);
    }
}
