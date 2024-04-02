package controladores;

import javax.swing.*;

public class MenuControlador {
    public void menuPrincipal() {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Registros:
                    2. Actualizaciones:
                    3. Informacion:
                    4. Citas:
                    5. Salir
                    """);
            switch (option) {
                case "1":
                    menuRegistros();
                    break;
                case "2":
                    menuActualizaciones();
                    break;
                case "3":
                    break;
                case "4":
                    break;
            }
        } while (!option.equals("5"));
    }

    EspecialidadControlador objE = new EspecialidadControlador();
    MedicoControlador objM = new MedicoControlador();
    PacienteControlador objP = new PacienteControlador();

    public void menuRegistros() {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU REGISTRO
                    1. Nueva Especialidad
                    2. Nuevo Medico
                    3. Nuevo Paciente
                    4. Salir
                    """);
            switch (option) {
                case "1":
                    objE.insertE();
                    break;
                case "2":
                    objM.insertM();
                    break;
                case "3":
                    objP.insertP();
                    break;
            }
        } while (!option.equals("4"));
    }
    public void menuActualizaciones() {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU ACTUALIZACIONES
                    1. Actualizar Especialidad
                    2. Actualizar Medico
                    3. Actualizar Paciente
                    4. Salir
                    """);
            switch (option) {
                case "1":
                    objE.updateE();
                    break;
                case "2":
                    objM.updateM();
                    break;
                case "3":
                    objP.updateP();
                    break;
            }
        } while (!option.equals("4"));
    }
}
