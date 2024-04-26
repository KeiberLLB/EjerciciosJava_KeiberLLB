package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorModel implements CRUD {
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Autor obj = (Autor) object;
        try {
            String sql = "insert into autores(nombre,nacionalidad) values(?,?);";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //asignar los signos de interrogación
            objPreparedStatement.setString(1, obj.getNombre());
            objPreparedStatement.setString(2, obj.getNacionalidad());
            //a continuación; objPreparadStatement.execute() solo ejecuta la linea
            objPreparedStatement.execute();
            ResultSet objResult = (ResultSet) objPreparedStatement.getGeneratedKeys();
            while (objResult.next()) {
                obj.setId(objResult.getInt(1));
            }
            //cerramos el objPreparedStatement
            objPreparedStatement.close();
            JOptionPane.showMessageDialog(null, "Autor Guardado");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return obj;
    }

    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listAutores = new ArrayList<>();
        try {
            String sql = "SELECT * FROM autores ORDER BY autores.id ASC;";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            ResultSet objResult = objPreparedStatement.executeQuery();
            while (objResult.next()) {
                Autor objAutor = new Autor();
                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getString("nombre"));
                objAutor.setNacionalidad(objResult.getString("nacionalidad"));
                listAutores.add(objAutor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listAutores;
    }

    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Autor objAutor = null;

        try {
            String sql = "SELECT * FROM autores WHERE id = ?;";
            PreparedStatement objPreparedS = objConnection.prepareStatement(sql);
            objPreparedS.setInt(1, id);
            ResultSet objResult = objPreparedS.executeQuery();

            while (objResult.next()) {
                objAutor = new Autor();
                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getString("nombre"));
                objAutor.setNacionalidad(objResult.getString("nacionalidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAutor;
    }

    public boolean update(Object object) {
        boolean isUpdate = false;
        Autor objAutor = (Autor) object;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "UPDATE autores SET nombre = ?, nacionalidad = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objAutor.getNombre());
            objPrepared.setString(2, objAutor.getNacionalidad());
            objPrepared.setInt(3, objAutor.getId());

            int totalAffectetedRows = objPrepared.executeUpdate();
            if (totalAffectetedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Autor actualizado correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    public boolean delete(Object object) {
        boolean isDeleted = false;
        Connection objConnection = ConfigDB.openConnection();
        Autor objAutor = (Autor) object;
        try {
            String sql = "DELETE FROM autores WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, objAutor.getId());
            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Autor Eliminado Correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public ArrayList<Autor> getByName(String name) {
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Autor> listAutores = new ArrayList<>();
        try {
            String sql = "SELECT * FROM autores WHERE nombre like ? ;";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, "%" + name + "%");
            ResultSet objResult = objPreparedStatement.executeQuery();
            while (objResult.next()) {
                Autor objAutor = new Autor();
                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getString("nombre"));
                objAutor.setNacionalidad(objResult.getString("nacionalidad"));
                listAutores.add(objAutor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listAutores;
    }

}
