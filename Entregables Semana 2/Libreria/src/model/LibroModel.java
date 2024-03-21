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
            JOptionPane.showMessageDialog(null, "Coder insertion was successful");

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
            String sql = "SELECT * FROM libros ORDER BY libres.id ASC;";
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
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
        }

        ConfigDB.closeConnection();
        return listLibros;
    }

    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        return null;
    }

    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        return false;
    }

    public boolean delete(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        return false;
    }
}
