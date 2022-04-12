import java.util.ArrayList;

public class Uniforme {
    //El atributo de la serie lo pasan como parametro
    //los atributos de los limites lo pasan como atributo
    private ArrayList<Double> serie;
    private int cantIntervalos;
    private ArrayList<ArrayList<Double>> intervalos;
    private int fo; //Frecuencia Observada (Veces que aparece el valor en el intervalo)
    private int fe; //Frecuencia Esperada (n/k) siendo n el tamaño de la muestra y k la cantidad de intervalos
    private double cValue; //Valor C (Fe - Fo)^2/Fo
    private double cACValue; //Valor C acumulado por cada intervalo


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

    public void setIntervalos() {
        ArrayList intervalos = new ArrayList<ArrayList<Double>>();
        double ii = 0;
        double is = 0;
        for (int i = 0; i < this.cantIntervalos; i++) {
            intervalos.add(new ArrayList());
            is = ii + (i/this.cantIntervalos) - 0.0001;
            intervalos.get(i).add(ii);
            intervalos.get(i).add(is);
            ii= is;
        }
        this.intervalos = intervalos;
    }

    public int getFo() {
        return this.fo;
    }

    public void setFo(int fo) {
        this.fo = fo;
    }

    public int getFe() {
        return this.fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }
    public double getCValue() {
        return this.cValue;
    }

    public void setCValue(double cValue) {
        this.cValue = cValue;
    }

    public double getCACValue() {
        return this.cACValue;
    }

    public void setCACValue(double cACValue) {
        this.cACValue = cACValue;
    }
}
