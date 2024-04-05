package controlador;

import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import model.VueloModel;
import servicio.PasajeroServicio;
import servicio.ReservacionServicio;
import servicio.VueloServicio;

import javax.swing.*;

public class ReservacionControlador {
    PasajeroServicio servicioPasajero = new PasajeroServicio();
    PasajeroControlador controladorPasajero = new PasajeroControlador();
    VueloServicio servicioVuelo = new VueloServicio();
    VueloModel modelVuelo = new VueloModel();
    ReservacionServicio servicioReservacion = new ReservacionServicio();

    public void insertC() {
        Reservacion reserva = new Reservacion();
        String cc = JOptionPane.showInputDialog("Ingrese el documento de identidad del pasajero que desea realizar la reserva: ");
        Pasajero objPasajero = (Pasajero) servicioPasajero.findByCC(cc);
        if (objPasajero != null){
            reserva.setId_pasajero(objPasajero.getId_pasajero());
        }else {
            Pasajero objP = (Pasajero) controladorPasajero.insertP(cc);
            reserva.setId_pasajero(objP.getId_pasajero());
        }
        boolean vueloCapacidadDisponible = false;
        while (!vueloCapacidadDisponible){
            try {
                String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo: ");
                Vuelo vuelo = (Vuelo) servicioVuelo.findByDestino(destino);
                if (vuelo!= null){
                    String listAsientosDisponibles = servicioReservacion.disponibilidadAsientos(vuelo.getId_vuelo());
                    if (listAsientosDisponibles!=null){

                    }

                    reserva.setId_vuelo(vuelo.getId_vuelo());
                }


            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"El vuelo seleccionado no tiene asientos disponibles!");
            }
            int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(servicioVuelo.getAll(modelVuelo.findAll())));

        }


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
        if (horaStr == null) {
            JOptionPane.showMessageDialog(null, "Hora inválida. Introduce una hora en formato HH:mm:ss");
        }
        try {
            // Convertir la hora al formato adecuado para Java
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            System.out.println("hola");
            java.util.Date date = sdf.parse(horaStr);
            // Convertir la hora al formato adecuado para SQL (time)
            Time timestamp = new Time(date.getTime());
            cita.setHora_cita(timestamp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al convertir la hora: " + e.getMessage());
        }
        String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la cita: ");
        cita.setMotivo(motivo);
        cita = (Reservacion) this.objReservacionModal.insert(cita);
    }


}
