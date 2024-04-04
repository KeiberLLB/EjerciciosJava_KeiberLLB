package servicio;

import entity.Avion;

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
}
