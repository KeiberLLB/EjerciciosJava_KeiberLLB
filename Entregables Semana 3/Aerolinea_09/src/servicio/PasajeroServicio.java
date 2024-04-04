package servicio;

import database.ConfigDB;
import entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PasajeroServicio {
    public Object findByCC(String cc) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = null;
        try {
            String sql = "SELECT * FROM pasajero WHERE documento_identidad = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, cc);
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

    public String getAll(List<Object> objectList) {
        String list = "Lista de Pasajeros: \n";
        for (Object pasajero : objectList) {
            Pasajero objPasajero = (Pasajero) pasajero;
            list += objPasajero.toString() + "\n";
        }
        return list;
    }
}
