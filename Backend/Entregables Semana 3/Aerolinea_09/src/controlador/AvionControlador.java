package controlador;

import entity.Avion;
import model.AvionModel;
import servicio.AvionServicio;

import javax.swing.*;

public class AvionControlador {
    AvionServicio servicioAvion = new AvionServicio();
    AvionModel modelAvion = new AvionModel();
    public void insertA() {
        Avion avion = new Avion();
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del avión");
        int columnas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de columnas que tiene el avión"));
        int filas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de filas que tiene el avión"));
        int capacidad = filas*columnas;
        avion.setFilas(filas);
        avion.setColumnas(columnas);
        avion.setModelo(modelo);
        avion.setCapacidad(capacidad);
        this.modelAvion.insert(avion);
        JOptionPane.showMessageDialog(null, avion.toString());
    }
    public void updateA() {
        String list = servicioAvion.getAll(modelAvion.findAll());
        int id_avion = Integer.parseInt(JOptionPane.showInputDialog(list + "Ingrese el Id del avión a modificar: "));
        Avion objAvion = (Avion) modelAvion.findById(id_avion);

        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            objAvion.setModelo(JOptionPane.showInputDialog(null, "Si es necesario ingrese el modelo: ", objAvion.getModelo()));
            objAvion.setFilas(Integer.parseInt(JOptionPane.showInputDialog(null, "Si es necesario ingrese las filas: ", objAvion.getFilas())));
            objAvion.setColumnas(Integer.parseInt(JOptionPane.showInputDialog(null, "Si es necesario ingrese las columnas: ", objAvion.getColumnas())));
            objAvion.setCapacidad(objAvion.getColumnas()* objAvion.getFilas());
            modelAvion.update(objAvion);
        }
    }
    public void deleteP() {
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(servicioAvion.getAll(modelAvion.findAll()) + "\nIngrese el Id del avión a eliminar: "));
        Avion objAvion = (Avion) modelAvion.findById(idDelete);
        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "Avion no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro? : \n" + objAvion);
            if (confirm == 0) {
                this.modelAvion.delete(objAvion);
            }
        }
    }
    public void getByCapacidad() {
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la capacidad del avion a buscar: \nLos resultados de la busqueda seran igual al valor solicitado o mayores."));
        Avion avion = (Avion) servicioAvion.findByCapacidad(capacidad);
        if (avion == null) {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
        } else {
            JOptionPane.showMessageDialog(null, avion);
        }
    }
}
