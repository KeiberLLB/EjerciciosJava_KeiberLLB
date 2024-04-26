package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;
import entity.Libro;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroModel implements CRUD {

    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Libro obj = (Libro) object;
        try {
            String sql = "insert into libros(titulo,año_publicacion,precio,id_autor) values(?,?,?,?);";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPreparedStatement.setString(1, obj.getTitulo());
            objPreparedStatement.setInt(2, obj.getAño_publicacion());
            objPreparedStatement.setDouble(3, obj.getPrecio());
            objPreparedStatement.setInt(4, obj.getId_autor());
            objPreparedStatement.execute();
            ResultSet objResult = objPreparedStatement.getGeneratedKeys();
            while (objResult.next()) {
                obj.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Libro Guardado");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return obj;
    }

    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listLibros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM libros ORDER BY libros.id ASC;";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            ResultSet objResult = objPreparedStatement.executeQuery();
            while (objResult.next()) {
                Libro objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAño_publicacion(objResult.getInt("año_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));
                listLibros.add(objLibro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listLibros;
    }

    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Libro objLibro = null;

        try {
            String sql = "SELECT * FROM libros WHERE id = ?;";
            PreparedStatement objPreparedS = objConnection.prepareStatement(sql);
            objPreparedS.setInt(1, id);
            ResultSet objResult = objPreparedS.executeQuery();

            while (objResult.next()) {
                objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAño_publicacion(objResult.getInt("año_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objLibro;
    }

    public boolean update(Object object) {
        boolean isUpdate = false;
        Libro objLibro = (Libro) object;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "UPDATE libros SET titulo = ?, año_publicacion = ?, precio = ?, id_autor = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objLibro.getTitulo());
            objPrepared.setInt(2, objLibro.getAño_publicacion());
            objPrepared.setDouble(3, objLibro.getPrecio());
            objPrepared.setInt(4, objLibro.getId_autor());
            objPrepared.setInt(5, objLibro.getId());

            int totalAffectetedRows = objPrepared.executeUpdate();
            if (totalAffectetedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Libro actualizado correctamente");
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
        Libro objLibro = (Libro) object;
        try {
            String sql = "DELETE FROM libros WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, objLibro.getId());
            int totalAffectedRows = objPrepared.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Libro Eliminado Correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public ArrayList<Libro> getByName(String name) {
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Libro> listLibros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM libros WHERE titulo like ? ;";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, "%" + name + "%");
            ResultSet objResult = objPreparedStatement.executeQuery();
            while (objResult.next()) {
                Libro objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setAño_publicacion(objResult.getInt("año_publicacion"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));
                listLibros.add(objLibro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listLibros;
    }
}
