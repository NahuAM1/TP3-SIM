import java.util.ArrayList;

public class Uniforme {
    //El atributo de la serie lo pasan como parametro
    //los atributos de los limites lo pasan como atributo
    private ArrayList<Double> serie;
    private int cantIntervalos;
    private ArrayList<ArrayList<Double>> intervalos;
    private ArrayList<Integer> fo; //Frecuencia Observada (Veces que aparece el valor en el intervalo)
    private ArrayList<Integer> fe; //Frecuencia Esperada (n/k) siendo n el tamaño de la muestra y k la cantidad de intervalos
                                   //!FIJARESE SI fe ES ENTERO O DOUBLE
    private ArrayList<Double> cValue; //Valor C (Fe - Fo)^2/Fo
    private ArrayList<Double> cACValue; //Valor C acumulado por cada intervalo


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
        ArrayList<ArrayList<Double>> intervalos = new ArrayList<ArrayList<Double>>();
        double ii = 0;
        double is = 0;
        for (int i = 0; i < this.cantIntervalos; i++) {
            intervalos.add(new ArrayList<Double>());
            is = (i/this.cantIntervalos) - 0.0001;
            intervalos.get(i).add(ii);
            intervalos.get(i).add(is);
            ii= (i/this.cantIntervalos);
        }
        this.intervalos = intervalos;
    }

    public ArrayList<Integer> getFo() {
        return this.fo;
    }

    public void setFo() {
        ArrayList<Integer> contadores = new ArrayList<Integer>();
        for (int i = 0; i < this.serie.size(); i++) {
            double num = serie.get(i);
            for (int j = 0; j < this.cantIntervalos; j++) {
                if (this.intervalos.get(j).contains(num)) {
                    contadores.set(j, contadores.get(j+1));
                }
            }

        }
        this.fo = contadores;
    }

    public ArrayList<Integer> getFe() {
        return this.fe;
    }

    public void setFe() {
        //valor = (n/k)
        ArrayList<Integer> fe = new ArrayList<Integer>();
        int valor = (this.serie.size())/cantIntervalos; //!SI NO ES INT HAY Q CAMBIAR ESTO A DOUBLE
        for (int i = 0; i < cantIntervalos; i++) {
            fe.add(valor);
        }
        this.fe = fe;
    }
    public ArrayList<Double> getCValue() {
        return this.cValue;
    }

    public void setCValue() {
        //valor = (Fe - Fo)^2/Fo
        double valor;
        ArrayList<Double> cValue = new ArrayList<Double>();
        for (int i = 0; i < this.cantIntervalos; i++) {
            valor = ((this.fe.get(i) - this.fo.get(i))^2)/this.fo.get(i);
            cValue.add(valor);
        }
        this.cValue = cValue;
    }

    public ArrayList<Double> getCACValue() {
        return this.cACValue;
    }

    public void setCACValue() {
        ArrayList<Double> cACValue = new ArrayList<Double>();
        double valor = 0;
        for (int i = 0; i < this.cantIntervalos; i++) {
            valor += this.cValue.get(i);
            cACValue.add(valor);
        }
        this.cACValue = cACValue;
    }
}
