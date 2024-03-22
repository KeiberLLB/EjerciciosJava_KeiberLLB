package controladores;

import entity.Autor;
import model.AutorModel;

import javax.swing.*;

public class AutorControl {

    AutorModel objM = new AutorModel();

    public void insertA() {
        Autor autor = new Autor();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del autor a registrar: ");
        String nacionalidad = JOptionPane.showInputDialog("Ingresa la nacionalidad: ");

        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);

        autor = (Autor) this.objM.insert(autor);
        JOptionPane.showMessageDialog(null, autor);
    }
}
