import java.util.ArrayList;


public class Exponencial {
    private ArrayList<Double> serie;
    private int cantidadIntervalos;
    private ArrayList<ArrayList<Double>> intervalos;
    private int frecuenciaObservada;
    private int frecuenciaEsperada;
    private double c;
    private double cAcumulado;

	public ArrayList<Double> getSerie() {
		return this.serie;
	}

	public void setSerie(ArrayList<Double> serie) {
		this.serie = serie;
	}

	public int getCantidadIntervalos() {
		return this.cantidadIntervalos;
	}

	public void setCantidadIntervalos(int cantidadIntervalos) {
		this.cantidadIntervalos = cantidadIntervalos;
	}

	public ArrayList<ArrayList<Double>> getIntervalos() {
		return this.intervalos;
	}

	public void setIntervalos(ArrayList<ArrayList<Double>> intervalos) {
        this.intervalos = intervalos;
    }
	}

	public int getFrecuenciaObservada() {
		return this.frecuenciaObservada;
	}

	public void setFrecuenciaObservada(int frecuenciaObservada) {
		this.frecuenciaObservada = frecuenciaObservada;
	}

	public int getFrecuenciaEsperada() {
		return this.frecuenciaEsperada;
	}

	public void setFrecuenciaEsperada(int frecuenciaEsperada) {
		this.frecuenciaEsperada = frecuenciaEsperada;
	}

	public double getC() {
		return this.c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getCAcumulado() {
		return this.cAcumulado;
	}

	public void setCAcumulado(double cAcumulado) {
		this.cAcumulado = cAcumulado;
	}




    
}
