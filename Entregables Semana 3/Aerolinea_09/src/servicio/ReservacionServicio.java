package servicio;

import database.ConfigDB;
import entity.Avion;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionServicio {

    public String getAll(List<Object> object) {
        String list = "Lista de Reservaciones: \n";
        for (Object cita : object) {
            Reservacion objReserva = (Reservacion) cita;
            list += objReserva.toString() + "\n";
        }
        return list;
    }

    public String disponibilidadAsientos(int idVuelo) {
        Connection objConnection = ConfigDB.openConnection();
        List<Reservacion> reservas = new ArrayList<>();
        String list = null;
        try {
            String sql = "SELECT * FROM reservacion\n" +
                    "INNER JOIN vuelo on vuelo.id_vuelo = reservacion.id_vuelo\n" +
                    "INNER JOIN avion on avion.id_avion = vuelo.id_avion WHERE id_vuelo = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, idVuelo);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                Reservacion objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("reservacion.id_reservacion"));
                objReservacion.setId_pasajero(objResult.getInt("reservacion.id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("reservacion.id_vuelo"));
                objReservacion.setFecha_reservacion(objResult.getDate("reservacion.fecha_reservacion"));
                objReservacion.setFila(objResult.getInt("reservacion.fila"));
                objReservacion.setColumna(objResult.getInt("reservacion.columna"));

                objAvion.setId_avion(objResult.getInt("avion.id_avion"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));
                objAvion.setColumnas(objResult.getInt("avion.columnas"));
                objAvion.setFilas(objResult.getInt("avion.filas"));

                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getDate("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("vuelo.hora_salida"));
                objVuelo.setobjAvion(objAvion);

                objReservacion.setObjVuelo(objVuelo);
                reservas.add(objReservacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        if(!reservas.isEmpty()){
            int contador=reservas.size();
            if (contador>reservas.get(0).getObjVuelo().getobjAvion().getCapacidad()){
                boolean[][] listaAsientos = new boolean[reservas.get(0).getObjVuelo().getobjAvion().getColumnas()][reservas.get(0).getObjVuelo().getobjAvion().getFilas()];

            }
        }


        ;


        for (Reservacion obj : reservas){
            listaAsientos[obj.getColumna()][obj.getFila()]=true;
            list = "Asientos disponibles: \n"+"indique (columna-asiento)";
            if (!reservas.isEmpty()) {
                objReservacion.getAsiento();

                boolean[][] listaAsientos = new boolean[objReservacion.getObjVuelo().getobjAvion().getColumnas()][objReservacion.getObjVuelo().getobjAvion().getFilas()];
                listaAsientos[reservas.getFIla()][reservas.getColumn()] = true;


                for (int i = 0; i < objReservacion.getObjVuelo().getobjAvion().getColumnas(); i++) {
                    for (int j = 0; j < objReservacion.getObjVuelo().getobjAvion().getFilas(); j++) {
                        if (!listaAsientos[i][j]) {
                            list += "(" + (i + 1) + " - " + (j + 1) + ")";
                        }
                    }
                }
            }
        }


        ConfigDB.closeConnection();
        return list;
    }
}
