package Distribuciones;

import java.util.ArrayList;
import java.lang.Math;
import java.text.DecimalFormat;


public class Convolucion {
    private double media;
    private double desviacion;
    private ArrayList<Double> serie;

    public double getMedia() {
		return this.media;
	}

	public double getDesviacion() {
		return this.desviacion;
	}

    public double getMinimo(){
        ArrayList<Double> lista = this.serie;
        lista.sort(null);
        return lista.get(0);
    }

    public double getMaximo(){
        ArrayList<Double> lista = this.serie;
        lista.sort(null);
        return lista.get(lista.size()-1);
    }

	public ArrayList<Double> getSerie() {
		return this.serie;
	}

    public double getMediaSerie(){
        double suma =0;
        for (int i=0; i<this.serie.size(); i++){
            suma += this.serie.get(i);
        }
        return (suma/this.serie.size());
    }

    public void generarConvolucion(double m, double d, double n){
        this.media = m;
        this.desviacion = d;
        this.serie = new ArrayList<Double>();
        for (int i=0; i< n; i++){
            double suma = this.generarRNDaux();
            double z = (((suma - 6) * this.desviacion) + this.media);
            z = this.truncarNumero(z);
            this.serie.add(z);
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