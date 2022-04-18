package Distribuciones;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;
import java.util.Random;

public class Poisson {
    private ArrayList<Integer> serie = new ArrayList<Integer>();
    private double media;
    private double lambda;
    private int n;
    private int minimo;
    private int maximo;

	public ArrayList<Double> getSerie() {
        ArrayList<Double> vec = this.transformarSerie();
		return vec;
	}

	public double getMedia() {
		return this.media;
	}

    public int getN(){
        return this.n;
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

    public Poisson(double media, double lambda, int n) {
        this.media = media;
        this.lambda = lambda;
        if (media == 0 && lambda != 0){
            this.media = lambda;
        }
        else{
            this.lambda = media;
        }
        this.generarSerieDiscreta(n);
        Collections.sort(this.serie);
        this.minimo = this.serie.get(0);
        this.maximo = this.serie.get(this.serie.size() -1);
        this.n = this.serie.size();
    }

    public void generarSerieDiscreta(int n){
        for (int i=0; i<n; i++){
            double P = 1;
            int X = -1;
            float A = (float) Math.exp(-this.lambda);
            double U = Math.random();
            P *= U;
            X += 1;
            while (P >=A){
                U = Math.random();
                P *= U;
                X += 1;
            }
            this.serie.add(X);
        }
    }

    private ArrayList<Double> transformarSerie(){
        ArrayList<Double> vector = new ArrayList<Double>(this.serie.size());
        for (int i = 0; i < this.serie.size(); i++) {
            vector.add((double) this.serie.get(i));
        }
        return vector;
    }

}
