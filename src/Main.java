import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        
     //---------------Parametros----------------------------------------

     Uniforme uniforme = new Uniforme();
     int limiteInf = 0;
     int limiteSup = 1;
     int tamañoN = 30;
     
     //---------------Generacion de la serie----------------------------------------

     uniforme.generarFrecuencia(limiteInf, limiteSup, tamañoN);
     ArrayList<Double> serie = uniforme.getSerie();
     System.out.println("SERIE: ");
     for (int i = 0; i < serie.size(); i++) {
         System.out.println("Numero " + i + ": " + serie.get(i));
     }
     
     //---------------Cantidad de intervalos----------------------------------------

     uniforme.setCantIntervalos(5);
     
     //---------------Intervalos----------------------------------------

     uniforme.setIntervalos(limiteInf);
     ArrayList<ArrayList<Double>> intervalos = new ArrayList<ArrayList<Double>>();
     intervalos = uniforme.getIntervalos();
     System.out.println("\nINTERVALOS: ");
     System.out.println(intervalos.toString());
     
     //---------------Fo----------------------------------------

     uniforme.setFo();
     ArrayList<Integer> fo = new ArrayList<Integer>();
     fo = uniforme.getFo();
     System.out.println("\nFo: ");
     System.out.println(fo.toString());
     
     //---------------Fe----------------------------------------

     uniforme.setFe();
     ArrayList<Double> fe = new ArrayList<Double>();
     fe = uniforme.getFe();
     System.out.println("\nFe: ");
     System.out.println(fe.toString());

     //---------------CValue----------------------------------------

     uniforme.setCValue();
     ArrayList<Double> CValue = new ArrayList<Double>();
     CValue = uniforme.getCValue();
     System.out.println("\nCValue: ");
     System.out.println(CValue.toString());
     
     //---------------CACValue----------------------------------------

     uniforme.setCACValue();
     ArrayList<Double> CACValue = new ArrayList<Double>();
     CACValue = uniforme.getCACValue();
     System.out.println("\nCACValue");
     System.out.println(CACValue.toString() );
          
     //---------------Po----------------------------------------

     uniforme.setPo();
     ArrayList<Double> Po = new ArrayList<Double>();
     Po = uniforme.getPo();
     System.out.println("\nPo: ");
     System.out.println(Po.toString());
               
     //---------------Pe----------------------------------------

     uniforme.setPe();
     ArrayList<Double> Pe = new ArrayList<Double>();
     Pe = uniforme.getPe();
     System.out.println("\nPe: ");
     System.out.println(Pe.toString());         

     //---------------PoAC----------------------------------------

     uniforme.setPoAC();
     ArrayList<Double> PoAC = new ArrayList<Double>();
     PoAC = uniforme.getPoAC();
     System.out.println("\nPoAC: ");
     System.out.println(PoAC.toString());   

     //---------------PeAC----------------------------------------

     uniforme.setPeAC();
     ArrayList<Double> PeAC = new ArrayList<Double>();
     PeAC = uniforme.getPeAC();
     System.out.println("\nPeAC: ");
     System.out.println(PeAC.toString());
     
     //---------------|Po(AC)-Pe(AC)|----------------------------------------

     uniforme.setabsPoACPeAC();
     ArrayList<Double> absPoACPeAC = new ArrayList<Double>();
     absPoACPeAC = uniforme.getabsPoACPeAC();
     System.out.println("\nAbsPoACPeAC: ");
     System.out.println(absPoACPeAC.toString());
     
     //---------------MAX----------------------------------------

     uniforme.setMax();
     double max;
     max = uniforme.getMax();
     System.out.println("\n El maximo es: " + max);
     
     //---------------Tabla k-s ----------------------------------------
     double tablaks;
     tablaks = uniforme.tablaks(5, tamañoN, uniforme.getSerie());
     System.out.println("\nEl max generado por la prueba de ks es: " + tablaks);

     }

     
}