package Ejercicio04;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;

public class Ejercicio_04 {
    /*Figuras Geométricas: Crea una clase abstracta Figura con un método
    abstracto calcularArea(). Luego, extiende esta clase con algunas figuras
    específicas como Círculo, Rectángulo, y Cuadrado, implementando el
    cálculo del área en cada una. Utiliza el concepto de herencia y polimorfismo.*/
    public static void main(String[] args) {

        //TEST 1: Calcular el área de un círculo
        Circulo objCirculo = new Circulo(40);
        System.out.println("El area del círculo es: " + objCirculo.calcularArea());

        //TEST 2: Calcular el área de un rectángulo
        Rectangulo objRectangulo = new Rectangulo(20, 40);
        System.out.println("El area del rectángulo es: " + objRectangulo.calcularArea());

        //TEST 3: Calcular área de un cuadrado
        Cuadrado objCuadrado = new Cuadrado(40);
        System.out.println("El área del cuadrado es: " + objCuadrado.calcularArea());

    }
}
