import java.util.ArrayList;
import java.util.Scanner;
public class Curso {
    private String codigo;
    private String nombreC;
    private ArrayList<Estudiante> listaEstudiantes;
    private static int idEstudiante = 1;
    public Curso(String codigo, String nombreC) {
        this.codigo = codigo;
        this.nombreC = nombreC;
        this.listaEstudiantes = new ArrayList<>();
    }
    //METODOS
    public void guardarEstudiante(Scanner objScan) {
        System.out.println("Agregar nuevo estudiante");
        System.out.println("Ingresa el nombre del estudiante: ");
        String nombre = objScan.next();
        System.out.println("Ingresa el email del estudiante: ");
        String email = objScan.next();
        Estudiante objEstudiante = new Estudiante(idEstudiante, nombre, email);
        idEstudiante++;
        if (this.listaEstudiantes.add(objEstudiante)) {
            System.out.println("Estudiante agregado correctamente!");
        } else {
            System.out.println("No se pudo agregar al estudiante!");
        }
    }
    public void listarEstudiantes() {
        //validar si la lista está vacía
        if (this.listaEstudiantes.isEmpty()) {
            System.out.println("El curso ".concat(this.nombreC).concat(" no tiene estudiantes registrados"));
        } else {
            System.out.println("\nListando Estudiantes del curso " + this.nombreC + "\n");
            for (Estudiante estd : this.listaEstudiantes) {
                System.out.println(estd.toString());
            }
        }
    }
    public void eliminarEstudiante(Scanner objScan) {
        listarEstudiantes();
        System.out.println("Ingrese le ID del estudiante que desea eliminar del curso: ");
        int idE = objScan.nextInt();
        if (this.listaEstudiantes.removeIf(estudiante -> estudiante.getId() == idE)) {
            System.out.println("Estudiante eliminado correctamente!");
        } else {
            System.out.println("¡No se puedo eliminar el estudiante!");
        }
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombreC() {
        return nombreC;
    }
    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }
    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }
    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    @Override
    public String toString() {
        return "Curso\n" +
                "código= " + codigo + "\n" +
                "nombre Curso= " + nombreC + "\n";
    }
}
