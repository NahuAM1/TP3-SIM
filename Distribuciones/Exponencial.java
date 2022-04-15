import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

public class Exponencial {
    private ArrayList<Double> serie;
    private double media;
    private double lambda;
    private int n;
    private Double minimo;
    private Double maximo;

	public ArrayList<Double> getSerie() {
		return this.serie;
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

	public Double getMinimo() {
		return this.minimo;
	}

	public Double getMaximo() {
		return this.maximo;
	}

    public Exponencial(double media, double lambda, int n) {
        this.media = media;
        this.lambda = lambda;
        if (media == 0 && lambda != 0){
            this.media = 1/lambda;
        }
        else{
            this.lambda = 1/media;
        }
        this.generarSerieDiscreta(n);
        Collections.sort(this.serie);
        this.minimo = this.serie.get(0);
        this.maximo = this.serie.get(this.serie.size() -1);
        this.n = this.serie.size();
    }

    public void generarSerieDiscreta(int n){
        for (int i=0; i< n; i++){
            double X = -1;
            double rnd = Math.random();
            double a = Math.log(1 - rnd);
            X = -media * a;
            this.serie.add(X);
        }
    }

}