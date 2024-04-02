package controladores;

import entity.Medico;
import entity.Paciente;
import model.PacienteModel;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PacienteControlador {
    PacienteModel objM = new PacienteModel();

    public String getAll(List<Object> objectList) {
        String list = "Lista de Pacientes: \n";
        for (Object med : objectList) {
            Medico objMed = (Medico) med;
            list += objMed.toString() + "\n";
        }
        return list;
    }

    public void insertP() {
        Paciente pac = new Paciente();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del paciente");
        String inputDate = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del paciente (YYYY-MM-DD):");
        String documento_identidad = JOptionPane.showInputDialog("Ingrese el documento del paciente");
        Date fecha_nacimiento = null;
        // Definimos el formato de fecha esperado
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Convertimos el String a un objeto Date
            Date dateOfBirth = format.parse(inputDate);
            fecha_nacimiento = dateOfBirth;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Utilice el formato YYYY-MM-DD.\n" + e.getMessage());
        }

        pac.setNombre(nombre);
        pac.setApellidos(apellidos);
        pac.setFecha_nacimiento(fecha_nacimiento);
        pac.setDocumento_identidad(documento_identidad);

        pac = (Paciente) this.objM.insert(pac);
        JOptionPane.showMessageDialog(null, pac);
    }

    public void updateP() {
        String list = getAll(objM.findAll());
        int id_paciente = Integer.parseInt(JOptionPane.showInputDialog(list + "Ingrese el Id de paciente a modificar: "));
        Paciente pac = (Paciente) objM.findById(id_paciente);

        if (pac == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            pac.setNombre(JOptionPane.showInputDialog(null, "Si es necesario ingrese el nombre: ", pac.getNombre()));
            pac.setApellidos(JOptionPane.showInputDialog(null, "Si es necesario ingrese los apellidos: ", pac.getApellidos()));
            pac.setDocumento_identidad(JOptionPane.showInputDialog(null, "Si es necesario ingrese la CC: ", pac.getDocumento_identidad()));
            String inputDate = JOptionPane.showInputDialog(null,"Ingrese la fecha de nacimiento del paciente si es necesario (YYYY-MM-DD):", pac.getFecha_nacimiento());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                // Convertimos el String a un objeto Date
                Date dateOfBirth = format.parse(inputDate);
                pac.setFecha_nacimiento(dateOfBirth);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Utilice el formato YYYY-MM-DD.\n" + e.getMessage());
            }
            objM.update(pac);
        }

    }
}

