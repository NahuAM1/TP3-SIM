package Distribuciones;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;
public class Poisson {
    private ArrayList<Integer> serie;
    private double media;
    private double lambda;
    private int minimo;
    private int maximo;

	public ArrayList<Integer> getSerie() {
		return this.serie;
	}

	public double getMedia() {
		return this.media;
	}

	public double getLambda() {
		return this.lambda;
	}

	public int getMinimo() {
		return this.minimo;
	}

	public int getMaximo() {
		return this.maximo;
	}

    public Poisson(double media, double lambda, ArrayList<Integer> serie) {
        this.serie = serie;
        this.media = media;
        this.lambda = lambda;
        if (media == 0){
            this.media = lambda;
        }
        else{
            this.lambda = media;
        }
//        Collections.sort(this.serie);
        this.minimo = this.serie.get(0);
        this.minimo = this.serie.get(this.serie.size() -1);
    }


    public Poisson(double media, double lambda, int n) {
        this.serie = new ArrayList<Integer>(n);
        this.generarSerieDiscreta(n);
        this.media = media;
        this.lambda = lambda;
        if (media == 0){
            this.media = lambda;
        }
        else{
            this.lambda = media;
        }
        Collections.sort(this.serie);
        this.minimo = this.serie.get(0);
        this.minimo = this.serie.get(this.serie.size() -1);
    }

    public void generarSerieDiscreta(int n){
        double P = 1;
        int X = -1;
        double A = Math.exp(-this.lambda);
        System.out.println(A);
        for (int i=0; i< n ; i++){
            do {
                double U = Math.random();
                P = P * U;
                X = X + 1;
            }while (P>=A);
            this.serie.add(X);
        }
    }

}
