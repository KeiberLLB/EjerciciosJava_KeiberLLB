package model;

import database.CRUD;
import database.ConfigDB;
import entity.Reservacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public Object findById(int id) {
        return null;
    }
}
