import javax.swing.*;

public class CuentaBancaria {
    //Atributos
    private int numeroCuenta;
    private String nombreTitular;
    private int saldoCuenta;

    public CuentaBancaria() {
    }

    public CuentaBancaria(int numeroCuenta, String nombreTitular, int saldoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;
        this.saldoCuenta = saldoCuenta;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(int saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public void retiroCuenta(int retiro){
        if (retiro<this.saldoCuenta){
            this.saldoCuenta-=retiro;
            JOptionPane.showMessageDialog(null, "Retiro realizado con éxito! \n"+"Nuevo saldo: "+this.saldoCuenta);
        }else{
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
    }
    public void consignacionCuenta(int consignacion){
        this.saldoCuenta+=consignacion;
        JOptionPane.showMessageDialog(null,"Consignación realizada con éxito! \n"+"Nuevo saldo: "+this.saldoCuenta);
    }

    public void menuUsuario(){
        int option=0;
        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, "MENÚ \n" +
                        "1. Retiro\n" +
                        "2. Consignación\n" +
                        "3. Mostrar Saldo\n" +
                        "4. Salir\n" +
                        "Elige una Opción: "
                ));

                switch (option) {
                    case 1:
                        try {
                            int retiro = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el valor a retirar: "));
                            retiroCuenta(retiro);
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null, "Caracteres no validos");
                        }
                        break;
                    case 2:
                        try {
                            int consig = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el valor a retirar: "));
                            consignacionCuenta(consig);
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null, "Caracteres no validos");
                        }
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null,"Tú saldo actual: \n" + this.saldoCuenta);
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Caracteres no validos");
            }

        }while (option!=4);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta=" + numeroCuenta +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", saldoCuenta=" + saldoCuenta +
                '}';
    }
}
