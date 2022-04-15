import Distribuciones.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Prueba de metodos");
        // Poisson prueba = new Poisson(0, 25.0, 50);
        // System.out.println(prueba.getSerie());
        Convolucion conv = new Convolucion(11, 0.3, 30);
        System.out.println(conv.getSerie());
        System.out.println(conv.getMediaSerie());
    }
}
