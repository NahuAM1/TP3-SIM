import Distribuciones.Poisson;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Prueba de metodos");
        Poisson prueba = new Poisson(0, 15.04, 50);

        System.out.println(prueba.getSerie());
    }
}
