package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {

    public String ejecutar() {
        String sql = "SELECT * FROM reservacion\n" +
                "INNER JOIN pasajero on pasajero.id_pasajero = reservacion.id_pasajero\n" +
                "inner JOIN vuelo on vuelo.id_vuelo = reservacion.id_vuelo\n" +
                "inner join avion on avion.id_avion = vuelo.id_avion\n";
        return sql;
    }

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) object;
        try {
            String sql = "INSERT INTO reservacion(id_pasajero, id_vuelo, fecha_reservacion, asiento) values(?, ?, ?, ?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPS.setInt(1, objReservacion.getId_pasajero());
            objPS.setInt(2, objReservacion.getId_vuelo());
            objPS.setDate(3, objReservacion.getFecha_reservacion());
            objPS.setString(4, objReservacion.getAsiento());

            objPS.execute();
            ResultSet objResult = objPS.getGeneratedKeys();
            while (objResult.next()) {
                objReservacion.setId_reservacion(objResult.getInt(1));
            }
            objPS.close();
            JOptionPane.showMessageDialog(null, "Reserva creada correctamente!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objReservacion;
    }

    @Override
    public boolean update(Object object) {
        boolean isUpdate = false;
        Reservacion objReservacion = (Reservacion) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "UPDATE reservacion SET id_pasajero = ?, id_vuelo = ?, fecha_reservacion = ?, String asiento = ? WHERE id_reservacion = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objReservacion.getId_pasajero());
            objPS.setInt(2, objReservacion.getId_vuelo());
            objPS.setDate(3, objReservacion.getFecha_reservacion());
            objPS.setString(4, objReservacion.getAsiento());
            objPS.setInt(5, objReservacion.getId_reservacion());

            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente\n" + "La fecha se la reserva cambio a la actual modificación");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        boolean isDelete = false;
        Reservacion objReservacion = (Reservacion) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM reservacion WHERE id_reservacion = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objReservacion.getId_reservacion());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Reserva eliminada");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listReservaciones = new ArrayList<>();
        try {
            String sql = ejecutar() + ";";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                Reservacion objReservacion = new Reservacion();
                Pasajero objPasajero = new Pasajero();
                Vuelo objVuelo = new Vuelo();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("reservacion.id_reservacion"));
                objReservacion.setId_pasajero(objResult.getInt("reservacion.id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("reservacion.id_vuelo"));
                objReservacion.setFecha_reservacion(objResult.getDate("reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("reservacion.asiento"));

                objPasajero.setId_pasajero(objResult.getInt("pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("pasajero.nombre"));
                objPasajero.setApellido(objResult.getString("pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("pasajero.documento_identidad"));

                objAvion.setId_avion(objResult.getInt("avion.id_avion"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));

                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getDate("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("vuelo.hora_salida"));
                objVuelo.setobjAvion(objAvion);

                objReservacion.setObjPasajero(objPasajero);
                objReservacion.setObjVuelo(objVuelo);

                listReservaciones.add(objReservacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listReservaciones;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Reservacion objReservacion = new Reservacion();
        try {
            String sql = ejecutar() + "WHERE id_reservacion = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objReservacion = new Reservacion();
                Pasajero objPasajero = new Pasajero();
                Vuelo objVuelo = new Vuelo();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("reservacion.id_reservacion"));
                objReservacion.setId_pasajero(objResult.getInt("reservacion.id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("reservacion.id_vuelo"));
                objReservacion.setFecha_reservacion(objResult.getDate("reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("reservacion.asiento"));

                objPasajero.setId_pasajero(objResult.getInt("pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("pasajero.nombre"));
                objPasajero.setApellido(objResult.getString("pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("pasajero.documento_identidad"));

                objAvion.setId_avion(objResult.getInt("avion.id_avion"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));

                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getDate("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("vuelo.hora_salida"));
                objVuelo.setobjAvion(objAvion);

                objReservacion.setObjPasajero(objPasajero);
                objReservacion.setObjVuelo(objVuelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objReservacion;
    }
}
