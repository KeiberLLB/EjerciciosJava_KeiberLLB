package controladores;

import entity.Autor;
import entity.Libro;
import model.AutorModel;
import model.LibroModel;

import javax.swing.*;

public class LibroControl {
    AutorModel objAM = new AutorModel();
    LibroModel objM = new LibroModel();

    public void insertL() {
        Libro libro = new Libro();
        String titulo = JOptionPane.showInputDialog("Ingrese el titulo del libro: ");
        double precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el precio del libro: "));
        int año_publicacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año del libro: "));
        String listaAutores = "";
        for (Object obj : objAM.findAll()) {
            Autor objA = (Autor) obj;
            listaAutores += objA.toString() + "\n";
        }
        int idAutor = Integer.parseInt(JOptionPane.showInputDialog(listaAutores + "Ingresa el Id del autor: "));

        libro.setTitulo(titulo);
        libro.setPrecio(precio);
        libro.setAño_publicacion(año_publicacion);
        libro.setId_autor(idAutor);

        libro = (Libro) this.objM.insert(libro);
        JOptionPane.showMessageDialog(null, libro);
    }

    public void updateL() {
        String listaLibros = "";
        for (Object obj : objM.findAll()) {
            Libro objL = (Libro) obj;
            listaLibros += objL.toString() + "\n";
        }
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaLibros + "Ingresa el Id del libro: "));
        Libro objLibro = (Libro) this.objM.findById(idUpdate);

        if (objLibro == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            String listaAutores = "";
            for (Object obj : objAM.findAll()) {
                Autor objA = (Autor) obj;
                listaAutores += objA.toString() + "\n";
            }
            int idAutor = Integer.parseInt(JOptionPane.showInputDialog(listaAutores + "Ingresa el Id del autor: "));
            objLibro.setId_autor(idAutor);
            objLibro.setTitulo(JOptionPane.showInputDialog(null, "Ingrese el nuevo titulo del libro si es necesario", objLibro.getTitulo()));
            objLibro.setPrecio(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio", objLibro.getPrecio())));
            objLibro.setAño_publicacion(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo año de publicacion si es necesario: ", objLibro.getAño_publicacion())));
            this.objM.update(objLibro);
        }
    }

    public void deleteL() {
        String listaLibros = "Lista de libros ... \n";
        for (Object obj : objM.findAll()) {
            Libro objL = (Libro) obj;
            listaLibros += objL.toString() + "\n";
        }
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listaLibros + "Ingrese el Id del libro a eliminar: "));
        Libro objLibro = (Libro) this.objM.findById(idDelete);
        if (objLibro == null) {
            JOptionPane.showMessageDialog(null, "Libro no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro? : \n" + objLibro.toString());
            if (confirm == 0) {
                this.objM.delete(objLibro);
            }
        }
    }

    public void allL() {
        String listaLibros = "";
        for (Object obj : objM.findAll()) {
            Libro objL = (Libro) obj;
            listaLibros += objL.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, "Autores: \n" + listaLibros);
    }

    public void buscarXnombreL() {
        String search = JOptionPane.showInputDialog("Ingresa el titulo de la busqueda: ");
        String list = "Libros\n";
        if (!this.objM.getByName(search).isEmpty()) {
            for (Libro libro : this.objM.getByName(search)) {
                list += libro.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, "Resultado de la busqueda ...\n" + list);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
        }
    }
}
