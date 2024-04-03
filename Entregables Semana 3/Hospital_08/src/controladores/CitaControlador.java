package controladores;

import entity.Cita;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CitaControlador {
    PacienteControlador objPC = new PacienteControlador();
    PacienteModel objPM = new PacienteModel();
    MedicoControlador objMC = new MedicoControlador();
    MedicoModel objMM = new MedicoModel();


    public String getAll(List<Object> object) {
        String list = "Lista de Citas: \n";
        for (Object cita : object) {
            Cita objCita = (Cita) cita;
            list += objCita.toString() + "\n";
        }
        return list;
    }

    public void insertC() {
        Cita cita = new Cita();
        String id_pac = objPC.getAll(objPM.findAll());
        int id_paciente = Integer.parseInt(JOptionPane.showInputDialog(id_pac + "\nIngrese el Id del paciente" + "\nSi el paciente no esta registrado marque 0 !"));
        if (id_paciente != 0) {
            cita.setId_paciente(id_paciente);
        } else {
            objPC.insertP();
            id_paciente = Integer.parseInt(JOptionPane.showInputDialog(id_pac + "\nIngrese el Id del paciente"));
            cita.setId_paciente(id_paciente);
        }
        String id_med = objMC.getAll(objMM.findAll());
        int id_medico = Integer.parseInt(JOptionPane.showInputDialog(id_med + "\nIngrese el Id del medico"));
        cita.setId_medico(id_medico);
        boolean dateCorrect = false;
        while (!dateCorrect) {
            try {
                String inputDate = JOptionPane.showInputDialog("Ingrese la fecha de la cita (YYYY-MM-DD):");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNacimiento = LocalDate.parse(inputDate, formatter);
                Date dateOfBirth = null;
                // Convertimos el String a un objeto Date
                dateOfBirth = Date.valueOf(fechaNacimiento);
                cita.setFecha_cita(dateOfBirth);
                dateCorrect = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Utilice el formato YYYY-MM-DD.\n" + e.getMessage());
            }
        }
        String horaStr = JOptionPane.showInputDialog(null, "Introduce la hora (formato HH:mm:ss):");
        if (horaStr == null || !horaStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")) {
            JOptionPane.showMessageDialog(null, "Hora inválida. Introduce una hora en formato HH:mm:ss");
        }
        try {
            // Convertir la hora al formato adecuado para Java
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date date = (Date) sdf.parse(horaStr);
            // Convertir la hora al formato adecuado para SQL (time)
            Time timestamp = new Time(date.getTime());
            cita.setHora_cita(timestamp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al convertir la hora: " + e.getMessage());
        }
    }
}
