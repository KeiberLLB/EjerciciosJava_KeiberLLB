package controlador;

import entity.Pasajero;
import model.PasajeroModel;
import servicio.PasajeroServicio;

import javax.swing.*;

public class PasajeroControlador {
    PasajeroModel modelPasajero = new PasajeroModel();
    PasajeroServicio servicioPasajero = new PasajeroServicio();

    public Object insertP(String cc) {
        Pasajero pasajero = new Pasajero();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del pasajero");
        String apellido = JOptionPane.showInputDialog("Ingrese los apellidos del pasajero");
        pasajero.setDocumento_identidad(cc);
        pasajero.setNombre(nombre);
        pasajero.setApellido(apellido);
        this.modelPasajero.insert(pasajero);
        JOptionPane.showMessageDialog(null, pasajero.toString());
        return pasajero;
    }
    public void updateP() {
        String list = servicioPasajero.getAll(modelPasajero.findAll());
        int id_passer = Integer.parseInt(JOptionPane.showInputDialog(list + "Ingrese el Id del pasajero a modificar: "));
        Pasajero objPasajero = (Pasajero) modelPasajero.findById(id_passer);

        if (objPasajero == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            objPasajero.setNombre(JOptionPane.showInputDialog(null, "Si es necesario ingrese el nombre: ", objPasajero.getNombre()));
            objPasajero.setApellido(JOptionPane.showInputDialog(null, "Si es necesario ingrese los apellidos: ", objPasajero.getApellido()));
            objPasajero.setDocumento_identidad(JOptionPane.showInputDialog(null, "Si es necesario ingrese la CC: ", objPasajero.getDocumento_identidad()));
            modelPasajero.update(objPasajero);
        }
    }
    public void deleteP() {
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(servicioPasajero.getAll(modelPasajero.findAll()) + "\nIngrese el Id del pasajero a eliminar: "));
        Pasajero objPasajero = (Pasajero) modelPasajero.findById(idDelete);
        if (objPasajero == null) {
            JOptionPane.showMessageDialog(null, "Pasajero no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro? : \n" + objPasajero);
            if (confirm == 0) {
                this.modelPasajero.delete(objPasajero);
            }
        }
    }
    public void getByCC() {
        String cc = JOptionPane.showInputDialog(null, "Ingresa el numero de documento del pasajero: ");
        Pasajero pasajero = (Pasajero) servicioPasajero.findByCC(cc);
        if (pasajero == null) {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
        } else {
            JOptionPane.showMessageDialog(null, pasajero);
        }
    }
}