package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoModel implements CRUD {
    public Object insert(Object obj) {
        //Establecer Conexión con la base de datos
        Connection objConnection = ConfigDB.openConnection();
        //Se cambia la clase del objeto recibido
        Medico objM = (Medico) obj;
        //Estructuración y ejecución comando SQL
        try {
            String sql = "insert into medico(nombre,apellidos,id_especialidad) values(?,?,?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPS.setString(1, objM.getNombre());
            objPS.setString(2, objM.getApellidos());
            objPS.setInt(3, objM.getId_especialidad());
            ****
            objPS.execute();
            ResultSet objResult = objPS.getGeneratedKeys();
            while (objResult.next()) {
                objE.setId_especialidad(objResult.getInt(1));
            }
            objPS.close();
            JOptionPane.showMessageDialog(null, "Especialidad guardada correctamente!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objE;
    }
}
