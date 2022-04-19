import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        
     //---------------Parametros----------------------------------------

     Uniforme uniforme = new Uniforme();
     int limiteInf = 20;
     int limiteSup = 45;
     int tamañoN = 30;
     
     //---------------Generacion de la serie----------------------------------------

     uniforme.generarFrecuencia(limiteInf, limiteSup, tamañoN);
     ArrayList<Double> serie = uniforme.getSerie();
     System.out.println("SERIE: ");
         System.out.println("\nSerie: " + serie.toString());
     
     //---------------Cantidad de intervalos----------------------------------------

     uniforme.setCantIntervalos(5);
     
     //---------------Intervalos----------------------------------------

     uniforme.setIntervalos(limiteInf, limiteSup);
     ArrayList<ArrayList<Double>> intervalos = new ArrayList<ArrayList<Double>>();
     intervalos = uniforme.getIntervalos();
     System.out.println("\nINTERVALOS: ");
     System.out.println(intervalos.toString());
     
     }

     
}