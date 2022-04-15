package Distribuciones;

import java.util.ArrayList;
import java.lang.Math;

public class Convolucion {
    private double media;
    private double desviacion;
    private ArrayList<Double> serie;

    public Convolucion(double m, double d, double n){
        this.media = m;
        this.desviacion = d;
        this.serie = new ArrayList<Double>();
        for (int i=0; i< n; i++){
            double suma = this.generarRNDaux();
            double z = (((suma - 6) * this.desviacion) + this.media);
            this.serie.add(z);
        }
    }

	public double getMedia() {
		return this.media;
	}

	public double getDesviacion() {
		return this.desviacion;
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

    private Double generarRNDaux(){
        double suma = 0;
        for (int i=0; i < 12; i++){
            double rnd = Math.random();
            suma += rnd;
        }
        return suma;
    }

}