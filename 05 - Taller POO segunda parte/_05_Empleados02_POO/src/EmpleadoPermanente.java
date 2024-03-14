public class EmpleadoPermanente extends Empleado{
    private String contrato;

    public EmpleadoPermanente(String nombre, int edad, int idEmpleado, double salario) {
        super(nombre, edad, idEmpleado, salario);
        this.contrato = "Termino Indefinivo";
    }

    public String getContrato() {
        return contrato;
    }
}
