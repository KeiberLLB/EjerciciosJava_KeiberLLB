package Ejercicio06;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante();

        int option = 0;
        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, "MENU\n" +
                        "1. Registrar Estudiante\n" +
                        "2. Ingresar Nueva Calificacion\n" +
                        "3. Promedio Calificaciones\n" +
                        "4. Salir"));
                switch (option) {
                    case 1:
                        estudiante.nuevoEstudiante();
                        estudiante.nuevaCalf();
                        break;
                    case 2:
                        estudiante.nuevaCalf();
                        break;
                    case 3:
                        estudiante.promedio();
                        break;
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null,"Carater no valido");
            }


        } while (option != 4);


    }
}
