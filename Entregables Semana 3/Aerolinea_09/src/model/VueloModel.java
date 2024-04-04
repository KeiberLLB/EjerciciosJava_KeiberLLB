package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {
    @Override
    public Object insert(Object object) {
        //Establecer Conexión con la base de datos
        Connection objConnection = ConfigDB.openConnection();
        //Se cambia la clase del objeto recibido
        Vuelo objVuelo = (Vuelo) object;
        //Estructuración y ejecución comando SQL
        try {
            String sql = "insert into vuelo(destino, fecha_salida, hora_salida, id_avion) values(?, ?, ?, ?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPS.setString(1, objVuelo.getDestino());
            objPS.setDate(2,objVuelo.getFecha_salida());
            objPS.setTime(3,objVuelo.getHora_salida());
            objPS.setInt(4, objVuelo.getId_avion());
            objPS.execute();
            ResultSet objResult = objPS.getGeneratedKeys();
            while (objResult.next()) {
                objVuelo.setId_vuelo(objResult.getInt(1));
            }
            objPS.close();
            JOptionPane.showMessageDialog(null, "Vuelo registrado correctamente!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public boolean update(Object object) {
        boolean isUpdate = false;
        Vuelo objVuelo = (Vuelo) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "UPDATE vuelo SET destino = ?, fecha_salida = ?, hora_salida = ?, id_avion = ? WHERE id_vuelo = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, objVuelo.getDestino());
            objPS.setDate(2,objVuelo.getFecha_salida());
            objPS.setTime(3,objVuelo.getHora_salida());
            objPS.setInt(4, objVuelo.getId_avion());
            objPS.setInt(5,objVuelo.getId_avion());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Vuelo actualizado correctamente");
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
        Vuelo objVuelo = (Vuelo) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM vuelo WHERE id_vuelo = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objVuelo.getId_vuelo());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Vuelo eliminado");
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
        List<Object> listVuelos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM vuelo INNER JOIN avion on vuelo.id_avion = avion.id_avion ORDER BY vuelo.fecha_salida ASC;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                Vuelo objVuelo = new Vuelo();
                Avion objAvion = new Avion();

                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getDate("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("vuelo.hora_salida"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));

                objAvion.setId_avion(objResult.getInt("avion.id_avion"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));

                objVuelo.setobjAvion(objAvion);
                listVuelos.add(objVuelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelos;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo =null;
        try {
            String sql = "SELECT * FROM vuelo INNER JOIN avion on avion.id_avion = vuelo.id_avion WHERE id_vuelo = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objVuelo = new Vuelo();
                Avion objAvion = new Avion();
                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getDate("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("vuelo.hora_salida"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));

                objAvion.setId_avion(objResult.getInt("avion.id_avion"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));

                objVuelo.setobjAvion(objAvion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }
}
