package controladores;

import javax.swing.*;

public class MenuControlador {
    public void menuPrincipal(){
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Registros:
                    2. Actualizaciones:
                    3. Citas:
                    4. Salir
                    """);
            switch (option){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
            }
        }while (!option.equals("3"));
    }

    public  void menuRegistros(){

    }

}
