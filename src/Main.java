import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
     Uniforme uniforme = new Uniforme();
     int limiteInf = 10;
     int limiteSup = 20;
     int tamañoN = 500;
     uniforme.generarFrecuencia(limiteInf, limiteSup, tamañoN);
     ArrayList<Double> serie = uniforme.getSerie();
     System.out.println("SERIE: ");
     for (int i = 0; i < serie.size(); i++) {
         System.out.println("Numero " + i + ": " + serie.get(i));
     }
     uniforme.setCantIntervalos(8);
     uniforme.setIntervalos(limiteInf);
     ArrayList<ArrayList<Double>> intervalos = new ArrayList<ArrayList<Double>>();
     intervalos = uniforme.getIntervalos();
     System.out.println("\nINTERVALOS: ");
     System.out.println(intervalos.toString());
     uniforme.setFo();
     ArrayList<Integer> fo = new ArrayList<Integer>();
     fo = uniforme.getFo();
     System.out.println("\nFo: ");
     System.out.println(fo.toString());
     }
     
}