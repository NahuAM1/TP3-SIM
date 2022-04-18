import Distribuciones.*;
import Graficos.Graficador;

import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Prueba de metodos");
        Poisson prueba = new Poisson(0, 100, 5000);
        System.out.println(prueba.getSerie());
//        Convolucion prueba = new Convolucion();
//        prueba.generarConvolucion(1035,2,5000);
        Graficador grafico = new Graficador(prueba.getSerie(), 100, prueba.getMinimo(), prueba.getMaximo(), "Poisson");
        grafico.pack();
        grafico.setVisible(true);
    }
}
