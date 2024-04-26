import javax.swing.*;
import java.util.ArrayList;

public class Ejercicio04 {
    public static void main(String[] args) {
        /*Planificador de Viajes: Escribe un programa para ayudar a planificar viajes por
        carretera. Los usuarios pueden ingresar varias ciudades que planean visitar en
        orden. Utiliza un arreglo de String para almacenar las ciudades. El programa debe
        ser capaz de:
             Añadir y remover ciudades del itinerario.
             Mostrar la lista completa de ciudades a visitar.
             (Opcional) Calcular la distancia total del viaje asumiendo distancias ficticias entre
                ciudades consecutivas.*/

        //Creación de la lista de Ciudades
        ArrayList<String> itinerario = new ArrayList<>();
        ArrayList<String> visitadas = new ArrayList<>();

        int option = 0;

        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, "MENÚ VIAJE\n" +
                        "1. Agregar Ciudad.\n" +
                        "2. Remover Ciudad.\n" +
                        "3. Mostrar Ciudades.\n" +
                        "4. Marcar Ciudad Como Visitada.\n" +
                        "5. Mostrar Ciudades Pendientes.\n" +
                        "6. Mostrar Ciudades Vistadas.\n" +
                        "7. Salir\n" +
                        "Elige una Opción: "
                ));

                switch (option) {
                    case 1://Agregar Ciudad
                        //solicitamos la nueva ciudad
                        String nuevaCiudad = JOptionPane.showInputDialog(null, "Ingrese la nueva Ciudad: ");
                        //Agregamos la nueva ciudad al itinerario
                        itinerario.add(nuevaCiudad.toLowerCase());
                        JOptionPane.showMessageDialog(null, "Ciudad agregada con éxito!");
                        break;
                    case 2://remover ciudad
                        //solicitud nombre ciudad a eliminar
                        String ciudadEliminar = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ciudad a eliminar: ");
                        if (itinerario.remove(ciudadEliminar.toLowerCase())) {
                            JOptionPane.showMessageDialog(null, "Ciudad eliminada correctamente!");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias!");
                        }
                        break;
                    case 3://mostrar todas las ciudades
                        //itinerario.isEmpty verifica si la lista esta vacía
                        if (itinerario.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay ciudades registradas!");
                        } else {
                            //variable que almacenará las ciudades para poder mostrarlas en una sola pantalla
                            String ciudades = "";
                            //forma de recorrer un arraylist
                            for (String ciudad : itinerario) {
                                String[] palabras = ciudad.split("\\s+");
                                StringBuilder resultado = new StringBuilder();
                                for (String palabra : palabras) {
                                    String palabraCapitalizada = palabra.toUpperCase().charAt(0) + palabra.substring(1).toLowerCase();
                                    resultado.append(palabraCapitalizada).append(" ");
                                }
                                ciudades += "** " + resultado + "\n";
                            }
                            JOptionPane.showMessageDialog(null, ciudades);
                        }
                        break;
                    case 4:
                        String ciudadMVisita = JOptionPane.showInputDialog(null, "Nombre Ciudad Visitada");
                        if (!itinerario.contains(ciudadMVisita.toLowerCase())) {
                            JOptionPane.showMessageDialog(null, "La ciudad no esta registrada en el itinerario!");
                        } else if (visitadas.contains(ciudadMVisita.toLowerCase())) {
                            JOptionPane.showMessageDialog(null, "La ciudad ya se había registrado anteriormente!");
                        } else {
                            visitadas.add(ciudadMVisita.toLowerCase());
                            JOptionPane.showMessageDialog(null, "Ciudad marcada como visitada");
                        }
                        break;
                    case 5:
                        String cPend = "";
                        for (String ciudad : itinerario) {
                            if (!visitadas.contains(ciudad)) {
                                String[] palabras = ciudad.split("\\s+");
                                StringBuilder resultado = new StringBuilder();
                                for (String palabra : palabras) {
                                    String palabraCapitalizada = palabra.toUpperCase().charAt(0) + palabra.substring(1).toLowerCase();
                                    resultado.append(palabraCapitalizada).append(" ");
                                }
                                cPend += "** " + resultado + "\n";
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Ciudades Pendientes\n" + cPend);
                        break;
                    case 6:
                        String cVist = "";
                        if (visitadas.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay ciudades visitadas aún!");
                        } else {
                            for (String ciudad : visitadas) {
                                String[] palabras = ciudad.split("\\s+");
                                StringBuilder resultado = new StringBuilder();
                                for (String palabra : palabras) {
                                    String palabraCapitalizada = palabra.toUpperCase().charAt(0) + palabra.substring(1).toLowerCase();
                                    resultado.append(palabraCapitalizada).append(" ");
                                }
                                cVist += "** " + resultado + "\n";
                            }
                            JOptionPane.showMessageDialog(null, "Ciudades Pendientes\n" + cVist);
                        }
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Caracteres no validos");
            }


        } while (option != 7);


    }
}
