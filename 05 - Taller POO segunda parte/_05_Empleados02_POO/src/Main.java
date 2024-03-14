import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        GestionEmpleados gestionEmpleados = new GestionEmpleados();

        Date inicio = new Date(124, 2, 1);
        Date fin = new Date(126, 11, 20);

        EmpleadoTemporal temporal1 = new EmpleadoTemporal("Juan", 30, 101, 2500, inicio, fin);
        EmpleadoPermanente permanente1 = new EmpleadoPermanente("Maria", 35, 102, 3500);

        gestionEmpleados.agregarEmpleadoT(temporal1);
        gestionEmpleados.agregarEmpleadoP(permanente1);

        gestionEmpleados.mostrarEmpleadosT();
        gestionEmpleados.mostrarEmpleadosP();

    }
}