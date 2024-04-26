package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) object;
        try {
            String sql = "insert into avion (modelo, filas, columnas, capacidad) values(?,?,?,?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPS.setString(1, objAvion.getModelo());
            objPS.setInt(2, objAvion.getFilas());
            objPS.setInt(3, objAvion.getColumnas());
            objPS.setInt(4, objAvion.getCapacidad());
            objPS.execute();
            ResultSet objResult = objPS.getGeneratedKeys();
            while (objResult.next()) {
                objAvion.setId_avion(objResult.getInt(1));
            }
            objPS.close();
            JOptionPane.showMessageDialog(null, "Avión registrado correctamente!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();

        return objAvion;
    }

    @Override
    public boolean update(Object object) {
        boolean isUpdate = false;
        Avion objAvion = (Avion) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "UPDATE avion SET modelo = ?, filas = ?, columnas = ?, capacidad = ? WHERE id_avion = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, objAvion.getModelo());
            objPS.setInt(2, objAvion.getFilas());
            objPS.setInt(3, objAvion.getColumnas());
            objPS.setInt(4, objAvion.getCapacidad());
            objPS.setInt(5, objAvion.getId_avion());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Información avión actualizada correctamente");
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
        Avion objAvion = (Avion) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM avion WHERE id_avion = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objAvion.getId_avion());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Avión eliminado");
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
        List<Object> listAviones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM avion ORDER BY avion.id_avion ASC;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()){
                Avion objAvion = new Avion();
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
                listAviones.add(objAvion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listAviones;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = null;
        try {
            String sql = "SELECT * FROM avion WHERE id_avion = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objAvion = new Avion();
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;
    }
}
