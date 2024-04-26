package servicio;

import database.ConfigDB;
import entity.Avion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VueloServicio {

    public String getAll(List<Object> objectList) {
        String list = "Lista de Vuelos: \n";
        for (Object vuelo : objectList) {
            Vuelo objVuelo = (Vuelo) vuelo;
            list += objVuelo.toString() + "\n";
        }
        return list;
    }
    public List<Object> findByDestino(String destino) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaVuelos = null;
        try {
            String sql = "SELECT * FROM vuelo INNER JOIN avion on avion.id_avion = vuelo.id_avion WHERE vuelo.destino = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, destino);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                listaVuelos =new ArrayList<>();
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
                listaVuelos.add(objVuelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaVuelos;
    }
    public java.sql.Date date(){
        boolean dateCorrect = false;
        Date dateVuelo = null;
        while (!dateCorrect) {

            try {
                String inputDate = JOptionPane.showInputDialog("Ingrese la fecha del vuelo (YYYY-MM-DD):");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNacimiento = LocalDate.parse(inputDate, formatter);

                // Convertimos el String a un objeto Date
                dateVuelo = Date.valueOf(fechaNacimiento);
                dateCorrect = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Utilice el formato YYYY-MM-DD.\n" + e.getMessage());
            }
        }
        return dateVuelo;
    }
    public Time time(){
        boolean timeCorrect = false;
        Time time = null;
        while (!timeCorrect) {
            String horaStr = JOptionPane.showInputDialog(null, "Introduce la hora del vuelo (formato HH:mm:ss):");
            if (horaStr == null) {
                JOptionPane.showMessageDialog(null, "Hora inválida. Introduce una hora en formato HH:mm:ss");
            }
            try {
                // Convertir la hora al formato adecuado para Java
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                java.util.Date date = sdf.parse(horaStr);
                // Convertir la hora al formato adecuado para SQL (time)
                Time timestamp = new Time(date.getTime());
                time = timestamp;
                timeCorrect = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al convertir la hora: " + e.getMessage());
            }
        }
        return time;
    }
    public Object findByDate(Date date) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo =null;
        try {
            String sql = "SELECT * FROM vuelo INNER JOIN avion on avion.id_avion = vuelo.id_avion WHERE vuelo.fecha_salida = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setDate(1, date);
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
