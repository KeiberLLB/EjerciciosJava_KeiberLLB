package Ejercicio04;

public class Cuadrado extends Rectangulo{
    public Cuadrado(double lado){
        super(lado,lado);
    }
    //no es necesario sobrescribir calcularArea(); porque al
    //implementar de Rectángulo ya estamos obteniendo la funcionalidad
}
