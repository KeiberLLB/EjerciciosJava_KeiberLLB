public class Empleado {
    //Atributos
    private String nombre;
    private String posicion;
    private double salario;
    private int id;

    public Empleado(String nombre, String posicion, double salario, int id) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.salario = salario;
        this.id = id;
    }

    //Metodos

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void upSalario(double aumento){
        this.salario+=this.salario*(aumento/100);
        System.out.println("Salario actualizado exitosamente!: " + this.salario);

    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + this.nombre + '\'' +
                ", posicion='" + this.posicion + '\'' +
                ", salario=" + this.salario +
                ", id=" + this.id +
                '}';
    }
}
