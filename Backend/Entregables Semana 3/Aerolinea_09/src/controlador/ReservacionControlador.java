package controlador;

import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import model.ReservacionModel;
import model.VueloModel;
import servicio.PasajeroServicio;
import servicio.ReservacionServicio;
import servicio.VueloServicio;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class ReservacionControlador {
    PasajeroServicio servicioPasajero = new PasajeroServicio();
    PasajeroControlador controladorPasajero = new PasajeroControlador();
    VueloServicio servicioVuelo = new VueloServicio();
    VueloModel modelVuelo = new VueloModel();
    ReservacionServicio servicioReservacion = new ReservacionServicio();
    ReservacionModel modelReservacion = new ReservacionModel();

    public void insertR() {
        Reservacion reserva = new Reservacion();
        String cc = JOptionPane.showInputDialog("Ingrese el documento de identidad del pasajero que desea realizar la reserva: ");
        Pasajero objPasajero = (Pasajero) servicioPasajero.findByCC(cc);
        if (objPasajero != null) {
            reserva.setId_pasajero(objPasajero.getId_pasajero());
        } else {
            Pasajero objP = (Pasajero) controladorPasajero.insertP(cc);
            reserva.setId_pasajero(objP.getId_pasajero());
        }

        boolean vueloCapacidadDisponible = false;
        while (!vueloCapacidadDisponible) {
            try {
                String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo: ");
                List<Object> listVuelos = servicioVuelo.findByDestino(destino);
                if (listVuelos != null) {
                    int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(servicioVuelo.getAll(listVuelos) + "\nIngrese el Id del Vuelo: "));
                    Vuelo objV = (Vuelo) modelVuelo.findById(id_vuelo);
                    if (objV != null) {
                        String listAsientosDisponibles = servicioReservacion.disponibilidadAsientos(objV.getId_vuelo());
                        if (listAsientosDisponibles != null) {
                            int columna = Integer.parseInt(JOptionPane.showInputDialog(listAsientosDisponibles + "\nIngrese la COLUMNA del asiento: "));
                            int fila = Integer.parseInt(JOptionPane.showInputDialog(listAsientosDisponibles + "\nIngrese la FILA del asiento: "));
                            reserva.setColumna(columna);
                            reserva.setFila(fila);
                            reserva.setId_vuelo(objV.getId_vuelo());
                            vueloCapacidadDisponible = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay asientos disponibles");
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El vuelo seleccionado no tiene asientos disponibles!");
            }
        }
        reserva.setFecha_reservacion(servicioVuelo.date());
        modelReservacion.insert(reserva);
    }
}

