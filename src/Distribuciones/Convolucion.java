package Generadores;

import java.util.ArrayList;

public class Normal{

    private double media;
    private double desviacion;
    private int cantintervalos;
    private int N_muestra;
    private ArrayList<Double> secuencia;

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getDesviacion() {
        return desviacion;
    }

    public void setDesviacion(double desviacion) {
        this.desviacion = desviacion;
    }


    public int getCantintervalos() {
        return cantintervalos;
    }

    public void setCantintervalos(int cantintervalos) {
        this.cantintervalos = cantintervalos;
    }

    public int getN_muestra() {
        return N_muestra;
    }

    public void setN_muestra(int n_muestra) {
        N_muestra = n_muestra;
    }

    public double getMinimo(){
        ArrayList<Double> lista = this.secuencia;
        lista.sort(null);
        return lista.get(0);
    }

    public double getMaximo(){
        ArrayList<Double> lista = this.secuencia;
        lista.sort(null);
        return lista.get(lista.size()-1);
    }

    public ArrayList<Double> getSecuencia() {
        return this.secuencia;
    }

    public double getMediaSecuencia(){
        double suma =0;
        for (int i=0; i<this.secuencia.size(); i++){
            suma += this.secuencia.get(i);
        }
        return (suma/this.secuencia.size());
    }

    public ArrayList<Double> generarSecuenciaBoxMuller(double N_muestra, double desviacion, double media){


        for (int i = 0;i < this.N_muestra ;i++) {

            double rnd1 = Math.random();
            double rnd2 = Math.random();
            double N = Math.sqrt(-2 * Math.log(rnd1)) * Math.cos(2 * Math.PI * rnd2) * desviacion + media;
            double N2 = Math.sqrt(-2 * Math.log(rnd1)) * Math.sin(2 * Math.PI * rnd2) * desviacion + media;
            this.secuencia.add(N);
            this.secuencia.add(N2);
        }
        return secuencia;

    }

    public void generarConvolucion(double m, double d, double n){
        this.media = m;
        this.desviacion = d;
        this.secuencia = new ArrayList<Double>();
        for (int i=0; i< n; i++){
            double suma = this.generarRNDaux();
            double z = (((suma - 6) * this.desviacion) + this.media);
            z = this.truncarNumero(z);
            this.secuencia.add(z);
        }
    }

    private Double generarRNDaux(){
        double suma = 0;
        for (int i=0; i < 12; i++){
            double rnd = Math.random();
            suma += rnd;
        }
        return suma;
    }

    private Double truncarNumero(double valor) {
        // Este metodo lo que hace es truncar el valor que se le pasa por parametro a 4 decimales, en caso de querer menos o mas
        // se le quitan o agregan"#" a la linea de abajo por los que necesite.
        DecimalFormat df = new DecimalFormat("#.####");
        String valor_truncado = df.format(valor).replace(",", ".");
        return Double.parseDouble(valor_truncado);
    }
}
