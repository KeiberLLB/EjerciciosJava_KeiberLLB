import javax.swing.*;
import java.util.ArrayList;

public class Ejercicio05 {
    public static void main(String[] args) {
        /*Lista de Reproducción Musical: Imagina que estás creando una aplicación para
        gestionar listas de reproducción musicales. Cada canción es representada
        simplemente por su nombre. El programa debe permitir:
             Añadir y remover canciones de la lista de reproducción.
             Mostrar la canción actual y las siguientes en la lista.
             Saltar a la siguiente canción.*/

        //Creación de la lista de canciones
        ArrayList<String> playList = new ArrayList<>();

        int option = 0;
        int cancionActual = 0;

        do {

            try {
                //integer.parseInt convierte de string a entero
                option = Integer.parseInt(JOptionPane.showInputDialog("PLAYLIST\n" +
                        "1. Agregar canción\n" +
                        "2. Remover canción\n" +
                        "3. Mostrar canción actual y siguientes en la lista\n" +
                        "4. Saltar a la siguiente canción\n" +
                        "5. Salir\n" +
                        "Ingrese una opción: "));

                switch (option) {
                    case 1://Añadir una canción
                        //Pedimos la nueva canción al usuario
                        String nuevaCancion = JOptionPane.showInputDialog(null, "Ingrese el nombre de la canción: ");

                        //Agregamos la nueva canción a la playlist
                        playList.add(nuevaCancion.toLowerCase());
                        JOptionPane.showMessageDialog(null, nuevaCancion + " Agregada Correctamente.");
                        ;
                        break;
                    case 2://Eliminar una canción de la playlist
                        //Pedimos al usuario el nombre de la canción a eliminar
                        String cancionEliminar = JOptionPane.showInputDialog(null, "Ingresa el nombre de la canción a eliminar");
                        if (playList.remove(cancionEliminar.toLowerCase())) {
                            JOptionPane.showMessageDialog(null, "Eliminada Correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null, cancionEliminar + " No se encuentra en la playList");
                        }
                        break;
                    case 3://Mostrar canción actual y siguientes en la lista
                        //preguntar si la playlist esta vacía
                        if (playList.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "La playlist está vacía.");
                        } else {
                            //creamos una variable para guardar en texto todas las canciones mayores a la actual
                            String listaTotal = "";
                            //agregamos la canción actual
                            listaTotal += "Cancion actual\n" + playList.get(cancionActual) + "\n" + "\nSiguientes en la lista:\n";

                            //forma de recorrer un arraylist
                            for (int i = cancionActual; i < playList.size() - 1; i++) {
                                //por cada iteración concatenamos a la variable
                                listaTotal += "** " + playList.get(i + 1) + "\n";
                            }
                            JOptionPane.showMessageDialog(null, listaTotal);
                        }
                        break;
                    case 4://Saltar a la siguiente canción
                        //Validar que si haya una siguiente canción
                        if (cancionActual + 1 < playList.size()) {
                            cancionActual++;
                            JOptionPane.showMessageDialog(null, "Canción actual " + playList.get(cancionActual));
                        } else {
                            JOptionPane.showMessageDialog(null, "PlayList finalizada");
                            cancionActual = 0;
                        }
                        JOptionPane.showMessageDialog(null, "Canción actual " + playList.get(cancionActual));
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Caracteres no validos");
            }
        } while (option != 5);
    }
}
