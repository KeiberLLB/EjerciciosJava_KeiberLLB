package controlador;

import entity.Avion;
import entity.Vuelo;
import model.AvionModel;
import model.VueloModel;
import servicio.AvionServicio;
import servicio.VueloServicio;

import javax.swing.*;
import java.sql.Date;


public class VueloContraloder {
    VueloServicio servicioVuelo = new VueloServicio();
    VueloModel modelVuelo = new VueloModel();
    AvionServicio servicioAvion = new AvionServicio();
    AvionModel modelAvion = new AvionModel();

    public void insertA() {
        Vuelo vuelo = new Vuelo();
        String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo");
        vuelo.setDestino(destino);
        vuelo.setFecha_salida(servicioVuelo.date());
        vuelo.setHora_salida(servicioVuelo.time());
        boolean verificacionA = false;
        while (!verificacionA){
            int id_avion = Integer.parseInt(JOptionPane.showInputDialog(servicioAvion.getAll(modelAvion.findAll())+"\nIngrese el ID del avión: "));
            Avion verificacion = (Avion) modelAvion.findById(id_avion);
            if (verificacion != null){
                vuelo.setId_avion(verificacion.getId_avion());
                verificacionA = true;
            }
        }
    }

    public void updateA() {
        String list = servicioVuelo.getAll(modelVuelo.findAll());
        int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(list + "Ingrese el Id del vuelo a modificar: "));
        Vuelo objVuelo = (Vuelo) modelVuelo.findById(id_vuelo);
        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            objVuelo.setFecha_salida(servicioVuelo.date());
            objVuelo.setHora_salida(servicioVuelo.time());
            boolean verificacionA = false;
            while (!verificacionA){
                int id_avion = Integer.parseInt(JOptionPane.showInputDialog(servicioAvion.getAll(modelAvion.findAll())+"\nIngrese el ID del avión: "));
                Avion verificacion = (Avion) modelAvion.findById(id_avion);
                if (verificacion != null){
                    objVuelo.setId_avion(verificacion.getId_avion());
                    verificacionA = true;
                }
            }
            modelVuelo.update(objVuelo);
        }
    }

    public void deleteP() {
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(servicioVuelo.getAll(modelVuelo.findAll()) + "\nIngrese el Id del vuelo a eliminar: "));
        Vuelo objVuelo = (Vuelo) modelVuelo.findById(idDelete);
        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro? : \n" + objVuelo);
            if (confirm == 0) {
                this.modelVuelo.delete(objVuelo);
            }
        }
    }

    public void getForDate(){

        Date date = servicioVuelo.date();
        Vuelo objVuelo = (Vuelo) servicioVuelo.findByDate(date);
        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
        }else {
            JOptionPane.showMessageDialog(null,objVuelo.toString());
        }
    }
}
