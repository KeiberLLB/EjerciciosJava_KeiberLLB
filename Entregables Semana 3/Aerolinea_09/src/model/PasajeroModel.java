package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) object;
        try {
            String sql = "insert into pasajero (nombre, apellido, documento_identidad) values(?,?,?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPS.setString(1,objPasajero.getNombre());
            objPS.setString(2,objPasajero.getApellido());
            objPS.setString(3,objPasajero.getDocumento_identidad());


            objPS.execute();
            ResultSet objResult = objPS.getGeneratedKeys();
            while (objResult.next()) {
                objPasajero.setId_pasajero(objResult.getInt(1));
            }
            objPS.close();
            JOptionPane.showMessageDialog(null, "Pasajero registrado correctamente!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();

        return objPasajero;
    }

    @Override
    public boolean update(Object object) {
        boolean isUpdate = false;
        Pasajero objPasajero = (Pasajero) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "UPDATE pasajero SET nombre = ?, apellido = ?, documento_identidad = ? WHERE id_pasajero = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, objPasajero.getNombre());
            objPS.setString(2, objPasajero.getApellido());
            objPS.setString(3, objPasajero.getDocumento_identidad());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Información pasajero actualizada correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object object){
        boolean isDelete = false;
        Pasajero objPasajero = (Pasajero) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM pasajero WHERE id_pasajero = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objPasajero.getId_pasajero());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Pasajero eliminado");
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
        List<Object> listPasajeros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pasajero ORDER BY pasajero.id_pasajero ASC;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                listPasajeros.add(objPasajero);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajeros;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = null;
        try {
            String sql = "SELECT * FROM pasajero WHERE id_pasajero = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }
}
