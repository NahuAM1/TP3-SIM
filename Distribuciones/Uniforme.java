import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Uniforme {
    // El atributo de la serie lo pasan como parametro
    // los atributos de los limites lo pasan como atributo
    private ArrayList<Double> serie;
    private int cantIntervalos;
    private ArrayList<ArrayList<Double>> intervalos;


    public void generarFrecuencia(int a, int b, int n) {
        // ?En este caso se pide el tamaño de la muestra que desea generar
        double valor;
        ArrayList<Double> serie = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            valor = a + (Math.random() * (b - a));
            valor = this.truncarNumero(valor);
            serie.add(valor);
        }
        this.setSerie(serie);
    }

    public ArrayList<Double> getSerie() {
        return this.serie;
    }

    public void setSerie(ArrayList<Double> serie) {
        this.serie = serie;
    }

    public int getCantIntervalos() {
        return this.cantIntervalos;
    }

    public void setCantIntervalos(int cantIntervalos) {
        this.cantIntervalos = cantIntervalos;
    }

    public ArrayList<ArrayList<Double>> getIntervalos() {
        return this.intervalos;
    }

    public void setIntervalos(int a, int b) {
        // Aqui se le pasa como parametro el limite inferior del intervalo para que
        // distribuya
        // los intervalos desde ese punto
        ArrayList<ArrayList<Double>> intervalos = new ArrayList<ArrayList<Double>>();
        double limitInf = a;
        double limitSup;
        double dif = (double) (b - a) / this.cantIntervalos;
        for (int i = 0; i < this.cantIntervalos; i++) {
            limitSup = limitInf + dif;
            limitSup -= 0.0001;
            intervalos.add(new ArrayList<Double>(Arrays.asList(limitInf, limitSup)));
            limitInf = limitSup + 0.0001;
        }
        this.intervalos = intervalos;
    }

    public double truncarNumero(double valor) {
        // Este metodo lo que hace es truncar el valor que se le pasa por parametro a 4
        // decimales, en caso de querer menos o mas
        // se le quitan o agregan"#" a la linea de abajo por los que necesite.
        DecimalFormat df = new DecimalFormat("#.####");
        String valor_truncado = df.format(valor).replace(",", ".");
        return Double.parseDouble(valor_truncado);
    }

}
