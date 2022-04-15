import java.util.ArrayList;abstract 

public class Normal{

	private double media;
	private double desviacion;
	private ArrayList<Double> serieRND;
	private int cantintervalos;
	private int N_muestra;

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

    public ArrayList<Double> getSerieRND() {
        return serieRND;
    }

    public void setSerieRND(ArrayList<Double> serieRND) {
        this.serieRND = serieRND;
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



     public ArrayList<Double> GenerarSecuencia(ArrayList<Double> serieRND, double desviacion, double media){


        ArrayList<Double> secuencia = new ArrayList<>();

        for (int i = 0;i < this.serieRND.size();i++) {
            if (i % 2 == 0) {
                double rnd1 = serieRND.get(i);
                double rnd2 = serieRND.get(i+1);
                double N = Math.sqrt(-2 * Math.log(rnd1)) * Math.cos(2 * Math.PI * rnd2) * desviacion * media;
                double N2 = Math.sqrt(-2 * Math.log(rnd1)) * Math.sin(2 * Math.PI * rnd2) * desviacion * media;
                System.out.println("N " + N);
                System.out.println("N2 " + N2);


            }
        }
        return secuencia;



    }










}