import Distribuciones.*;
import Graficos.Graficador;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Prueba de metodos");
        // Poisson prueba = new Poisson(0, 25.0, 50);
        // System.out.println(prueba.getSerie());
        Convolucion conv = new Convolucion();
        conv.generarConvolucion(11,2,5000);
        Graficador grafico = new Graficador(conv.getSerie(), 100, conv.getMinimo(), conv.getMaximo(), "Normal");
        grafico.pack();
        grafico.setVisible(true);
    }
}
