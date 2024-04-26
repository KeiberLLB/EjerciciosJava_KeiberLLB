package entity;

import java.sql.Time;
import java.sql.Date;

public class Vuelo {
    private int id_vuelo;
    private String destino;
    private Date fecha_salida;
    private Time hora_salida;
    private int id_avion;
    private Avion objAvion;
    public Vuelo() {
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public Avion getobjAvion() {
        return objAvion;
    }

    public void setobjAvion(Avion objAvion) {
        this.objAvion = objAvion;
    }

    @Override
    public String toString() {
        return "Vuelo\n" +
                "   Id vuelo= " + id_vuelo + "\n" +
                "   Destino= " + destino + "\n" +
                "   Fecha Salida= " + fecha_salida + "\n" +
                "   Hora Salida= " + hora_salida + "\n" +
                "       Modelo Avión = " + objAvion.getModelo() + "\n" +
                "       Capacidad =" + objAvion.getCapacidad() + "\n";
    }
}
