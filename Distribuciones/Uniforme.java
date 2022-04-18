import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Uniforme {
    // El atributo de la serie lo pasan como parametro
    // los atributos de los limites lo pasan como atributo
    private ArrayList<Double> serie;
    private int cantIntervalos;
    private ArrayList<ArrayList<Double>> intervalos;
    private ArrayList<Integer> fo; // Frecuencia Observada (Veces que aparece el valor en el intervalo)
    private ArrayList<Double> fe; // Frecuencia Esperada (n/k) siendo n el tamaño de la muestra y k la cantidad de
                                  // intervalos
                                  // !FIJARESE SI fe ES ENTERO O DOUBLE
    private ArrayList<Double> cValue; // Valor C (Fe - Fo)^2/Fo
    private ArrayList<Double> cACValue; // Valor C acumulado por cada intervalo
    private ArrayList<Double> po;
    private ArrayList<Double> pe;
    private ArrayList<Double> poAC;
    private ArrayList<Double> peAC;
    private ArrayList<Double> absPoACPeAC;
    private double max;

    public void generarFrecuencia(int a, int b, int n) {
        // ?En este caso se pide el tamaño de la muestra que desea generar
        double valor;
        ArrayList<Double> serie = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            valor = a + (Math.random() * (b - a));
            valor = this.truncarNumero(valor);
            serie.add(valor);
        }
        this.setSerie(serie);
    }

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

    public void setIntervalos(int a, int b) {
        // Aqui se le pasa como parametro el limite inferior del intervalo para que
        // distribuya
        // los intervalos desde ese punto
        ArrayList<ArrayList<Double>> intervalos = new ArrayList<ArrayList<Double>>();
        double limitInf = a;
        double limitSup;
        double dif = (double) (b - a) / this.cantIntervalos;
        for (int i = 0; i < this.cantIntervalos; i++) {
            limitSup = limitInf + dif;
            limitSup -= 0.0001;
            intervalos.add(new ArrayList<Double>(Arrays.asList(limitInf, limitSup)));
            limitInf = limitSup + 0.0001;
        }
        this.intervalos = intervalos;
    }

    public ArrayList<Integer> getFo() {
        return this.fo;
    }

    public void setFo() {
        ArrayList<Integer> contadores = new ArrayList<Integer>();
        for (int i = 0; i < this.cantIntervalos; i++) {
            contadores.add(0);
        }
        for (int i = 0; i < this.serie.size(); i++) {
            double num = serie.get(i);
            for (int j = 0; j < this.cantIntervalos; j++) {
                if (num >= this.intervalos.get(j).get(0) && num <= this.intervalos.get(j).get(1)) {
                    contadores.set(j, contadores.get(j) + 1);
                }
            }

        }
        this.fo = contadores;
    }

    public ArrayList<Double> getFe() {
        return this.fe;
    }

    public void setFe() {
        // valor = (n/k)
        ArrayList<Double> fe = new ArrayList<Double>();
        double valor = (this.serie.size()) / cantIntervalos; // !SI NO ES INT HAY Q CAMBIAR ESTO A DOUBLE
        for (int i = 0; i < this.cantIntervalos; i++) {
            fe.add(valor);
        }
        this.fe = fe;
    }

    public ArrayList<Double> getCValue() {
        return this.cValue;
    }

    public void setCValue() {
        // valor = (Fo - Fe)^2/Fe
        double valor;
        ArrayList<Double> cValue = new ArrayList<Double>();
        for (int i = 0; i < this.cantIntervalos; i++) {
            valor = Math.pow((this.fo.get(i) - this.fe.get(i)), 2);
            valor = valor / this.fe.get(i);
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

    public ArrayList<Double> getPo() {
        return this.po;
    }

    public void setPo() {
        ArrayList<Double> po = new ArrayList<Double>();
        double valor;
        for (int i = 0; i < this.cantIntervalos; i++) {
            valor = (double) this.fo.get(i) / this.serie.size();
            po.add(valor);
        }
        this.po = po;
    }

    public ArrayList<Double> getPe() {
        return this.pe;
    }

    public void setPe() {
        ArrayList<Double> pe = new ArrayList<Double>();
        double valor;
        for (int i = 0; i < this.cantIntervalos; i++) {
            valor = (double) this.fe.get(i) / this.serie.size();
            pe.add(valor);
        }
        this.pe = pe;
    }

    public ArrayList<Double> getPoAC() {
        return this.poAC;
    }

    public void setPoAC() {
        ArrayList<Double> poAC = new ArrayList<Double>();
        double valor = 0;
        for (int i = 0; i < this.cantIntervalos; i++) {
            valor += this.po.get(i);
            poAC.add(valor);
        }
        this.poAC = poAC;
    }

    public ArrayList<Double> getPeAC() {
        return this.peAC;
    }

    public void setPeAC() {
        ArrayList<Double> peAC = new ArrayList<Double>();
        double valor = 0;
        for (int i = 0; i < this.cantIntervalos; i++) {
            valor += this.pe.get(i);
            peAC.add(valor);
        }
        this.peAC = peAC;
    }

    public ArrayList<Double> getabsPoACPeAC() {
        return this.absPoACPeAC;
    }

    public void setabsPoACPeAC() {
        ArrayList<Double> absPoACPeAC = new ArrayList<Double>();
        double valor;
        for (int i = 0; i < this.cantIntervalos; i++) {
            valor = this.poAC.get(i) - this.peAC.get(i);
            valor = Math.abs(valor);
            absPoACPeAC.add(valor);
        }
        this.absPoACPeAC = absPoACPeAC;
    }

    public double getMax() {
        return this.max;
    }

    public void setMax() {
        double valor = 0;
        for (int i = 0; i < this.absPoACPeAC.size(); i++) {
            if (this.absPoACPeAC.get(i) > valor) {
                valor = this.absPoACPeAC.get(i);
            }
        }
        this.max = valor;
    }

    public double truncarNumero(double valor) {
        // Este metodo lo que hace es truncar el valor que se le pasa por parametro a 4
        // decimales, en caso de querer menos o mas
        // se le quitan o agregan"#" a la linea de abajo por los que necesite.
        DecimalFormat df = new DecimalFormat("#.####");
        String valor_truncado = df.format(valor).replace(",", ".");
        return Double.parseDouble(valor_truncado);
    }

    public double tablaks(int cantIntervalos, int n, ArrayList<Double> serie) {
        // Esta metodo lo que va a hacer es generar la tabla de k-s y devolvera el
        // maximo, es decir el valor final

        ArrayList<ArrayList<Double>> intervalos = new ArrayList<ArrayList<Double>>();
        ArrayList<Integer> fO = new ArrayList<Integer>();
        ArrayList<Double> fE = new ArrayList<Double>();
        ArrayList<Double> pO = new ArrayList<Double>();
        ArrayList<Double> pE = new ArrayList<Double>();
        ArrayList<Double> poAC = new ArrayList<Double>();
        ArrayList<Double> peAC = new ArrayList<Double>();
        ArrayList<Double> absPoACPeAC = new ArrayList<Double>();

        double limitInf = cantIntervalos;
        double limitSup;
        for (int i = 0; i < cantIntervalos; i++) {
            limitSup = (double) (i + 1) / cantIntervalos;
            limitSup -= 0.0001;
            limitSup += cantIntervalos;
            intervalos.add(new ArrayList<Double>(Arrays.asList(limitInf, limitSup)));
            limitInf = (double) (i + 1) / cantIntervalos;
            limitInf += cantIntervalos;
        }

        for (int i = 0; i < cantIntervalos; i++) {
            fO.add(0);
        }
        for (int i = 0; i < n; i++) {
            double num = serie.get(i);
            for (int j = 0; j < cantIntervalos; j++) {
                if (num >= intervalos.get(j).get(0) && num <= intervalos.get(j).get(1)) {
                    fO.set(j, fO.get(j) + 1);
                }
            }

        }

        double valor = (n) / cantIntervalos;
        for (int i = 0; i < cantIntervalos; i++) {
            fE.add(valor);
        }

        for (int i = 0; i < cantIntervalos; i++) {
            valor = (double) fO.get(i) / n;
            pO.add(valor);
        }

        for (int i = 0; i < cantIntervalos; i++) {
            valor = (double) fE.get(i) / n;
            pE.add(valor);
        }

        valor = 0;
        for (int i = 0; i < cantIntervalos; i++) {
            valor += pO.get(i);
            poAC.add(valor);
        }

        valor = 0;
        for (int i = 0; i < cantIntervalos; i++) {
            valor += pE.get(i);
            peAC.add(valor);
        }

        for (int i = 0; i < cantIntervalos; i++) {
            valor = poAC.get(i) - peAC.get(i);
            valor = Math.abs(valor);
            absPoACPeAC.add(valor);
        }

        double max = 0;
        for (int i = 0; i < absPoACPeAC.size(); i++) {
            if (absPoACPeAC.get(i) > valor) {
                max = absPoACPeAC.get(i);
                System.out.println("Anashe: " + max);
            }
        }

        return max;

    }
}
