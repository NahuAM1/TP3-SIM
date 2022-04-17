import java.util.ArrayList;abstract 

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

    

     public ArrayList<Double> GenerarSecuenciaBoxMuller(double N_muestra, double desviacion, double media){


        

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




}
