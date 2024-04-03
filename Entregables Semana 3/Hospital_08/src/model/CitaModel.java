package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {
    public Object insert(Object obj){
        Connection objConnection = ConfigDB.openConnection();
        Cita objC = (Cita) obj;
        try {
            String sql = "INSERT INTO cita(id_paciente, id_medico, fecha_cita, hora_cita, motivo) values(?, ?, ?, ?, ?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPS.setInt(1, objC.getId_paciente());
            objPS.setInt(2,objC.getId_cita());
            objPS.setDate(3,(Date) objC.getFecha_cita());
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objC;
    }

    @Override
    public boolean update(Object object) {
        boolean isUpdate = false;
        Cita objC = (Cita) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "UPDATE cita SET id_medico = ?, fecha_cita = ?, hora_cita = ?, motivo = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1,objC.getId_medico());
            objPS.setDate(2,(Date) objC.getFecha_cita());
            objPS.setTime(3,objC.getHora_cita());
            objPS.setString(4,objC.getMotivo());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected>0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Cita actualizada correctamente");
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        boolean isDelete = false;
        Cita objC = (Cita) object;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM cita WHERE id_cita = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objC.getId_cita());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Cita eliminada");
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
        List<Object> listCitas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cita \n" +
                    "INNER JOIN medico on cita.id_medico = medico.id_medico\n" +
                    "inner JOIN paciente on cita.id_paciente = paciente.id_paciente\n" +
                    ";";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                Cita objC = new Cita();

                Paciente objP = new Paciente();
                Medico objM = new Medico();
                objC.setId_cita(objResult.getInt("cita.id_cita"));
                objC.setId_medico(objResult.getInt("cita.id_medico"));
                objC.setId_paciente(objResult.getInt("cita.id_paciente"));
                objC.setFecha_cita(objResult.getDate("cita.fecha_cita"));
                objC.setHora_cita(objResult.getTime("cita.hora_cita"));
                objC.setMotivo(objResult.getString("cita.motivo"));

                objP.setId_paciente(objResult.getInt("paciente.id_paciente"));
                objP.setNombre(objResult.getString("paciente.nombre"));
                objP.setApellidos(objResult.getString("paciente.apellidos"));
                objP.setFecha_nacimiento(objResult.getDate("paciente.fecha_nacimiento"));
                objP.setDocumento_identidad(objResult.getString("paciente.fecha_nacimiento"));
                //control +alt+shift + j selecciona todas las coincidencias
                objM.setId_medico(objResult.getInt("medico.id_medico"));
                objM.setNombre(objResult.getString("medico.nombre"));
                objM.setApellidos(objResult.getString("medico.apellidos"));
                objM.setId_especialidad(objResult.getInt("medico.id_especialidad"));

                objC.setObjM(objM);
                objC.setObjP(objP);

                listCitas.add(objC);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCitas;
    }

    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objC = null;
        try {
            String sql = "SELECT * FROM cita INNER JOIN medico on cita.id_medico = medico.id_medico inner JOIN paciente on cita.id_paciente = paciente.id_paciente WHERE id_cita = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objC = new Cita();
                Paciente objP = new Paciente();
                Medico objM = new Medico();
                objC.setId_cita(objResult.getInt("cita.id_cita"));
                objC.setId_medico(objResult.getInt("cita.id_medico"));
                objC.setId_paciente(objResult.getInt("cita.id_paciente"));
                objC.setFecha_cita(objResult.getDate("cita.fecha_cita"));
                objC.setHora_cita(objResult.getTime("cita.hora_cita"));
                objC.setMotivo(objResult.getString("cita.motivo"));

                objP.setId_paciente(objResult.getInt("paciente.id_paciente"));
                objP.setNombre(objResult.getString("paciente.nombre"));
                objP.setApellidos(objResult.getString("paciente.apellidos"));
                objP.setFecha_nacimiento(objResult.getDate("paciente.fecha_nacimiento"));
                objP.setDocumento_identidad(objResult.getString("paciente.fecha_nacimiento"));
                //control +alt+shift + j selecciona todas las coincidencias
                objM.setId_medico(objResult.getInt("medico.id_medico"));
                objM.setNombre(objResult.getString("medico.nombre"));
                objM.setApellidos(objResult.getString("medico.apellidos"));
                objM.setId_especialidad(objResult.getInt("medico.id_especialidad"));

                objC.setObjM(objM);
                objC.setObjP(objP);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objC;
    }
}
