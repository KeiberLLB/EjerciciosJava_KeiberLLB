package servicio;

import database.ConfigDB;
import entity.Avion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AvionServicio {
    public String getAll(List<Object> objectList) {
        String list = "Lista de Aviones: \n";
        for (Object avion : objectList) {
            Avion objAvion = (Avion) avion;
            list += objAvion.toString() + "\n";
        }
        return list;
    }

    public Object findByCapacidad(int capacidad) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = null;
        try {
            String sql = "SELECT * FROM avion WHERE capacidad > ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, capacidad);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objAvion = new Avion();
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
                objAvion.setFilas(objResult.getInt("filas"));
                objAvion.setColumnas(objResult.getInt("columnas"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;
    }
}
