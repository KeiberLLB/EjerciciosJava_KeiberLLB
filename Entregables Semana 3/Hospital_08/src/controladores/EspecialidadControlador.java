package controladores;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;
import java.util.List;

public class EspecialidadControlador {
    EspecialidadModel objM = new EspecialidadModel();

    public String getAll(List<Object> objectList) {
        String list = "Lista de Especializaciones: \n";
        for (Object esp : objectList) {
            Especialidad objEsp = (Especialidad) esp;
            list += objEsp.toString() + "\n";
        }
        return list;
    }

    public void insertE() {
        Especialidad esp = new Especialidad();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad");
        String descriccion = JOptionPane.showInputDialog("Ingrese la descriccion de la especialidad");

        esp.setNombre(nombre);
        esp.setDescripcion(descriccion);

        esp = (Especialidad) this.objM.insert(esp);
        JOptionPane.showMessageDialog(null, esp);
    }

    public void updateE() {
        String list = getAll(objM.findAll());
        int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(list + "Ingrese el ID de la especialidad"));
        Especialidad esp = (Especialidad) objM.findById(id_especialidad);
        if (esp == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            esp.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre si es necesario: ", esp.getNombre()));
            esp.setDescripcion(JOptionPane.showInputDialog(null, "Ingresa la descripcion si es necesario: ", esp.getDescripcion()));
            this.objM.update(esp);
        }
    }
}
