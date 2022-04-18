package Pruebas;
public class KolmogorovSmirnov {
    
    ArrayList<ArrayList<Double>> pruebaDeBondad(ArrayList<Double> muestras_generadas, int intervalos, String opcion) {
        ChiCuadrado chiC = new ChiCuadrado();
        ArrayList<ArrayList<Double>> tablaChi = new ArrayList<ArrayList<Double>>();
        tablaChi = chiC.pruebaDeBondad(muestras_generadas, intervalos, opcion);        
    }
}
