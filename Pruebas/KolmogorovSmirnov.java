public class KolmogorovSmirnov {
    private int cantIntervalos;
    private ArrayList<ArrayList<Double>> intervalos;
    private ArrayList<Integer> fo; // Frecuencia Observada (Veces que aparece el valor en el intervalo)
    private ArrayList<Double> fe;
    private ArrayList<Double> po;
    private ArrayList<Double> pe;
    private ArrayList<Double> poAC;
    private ArrayList<Double> peAC;
    private ArrayList<Double> absPoACPeAC;
    private double max;

    public int getCantIntervalos() {
        return this.cantIntervalos;
    }

    public void setCantIntervalos(int cantIntervalos) {
        this.cantIntervalos = cantIntervalos;
    }

    public ArrayList<ArrayList<Double>> getIntervalos() {
        return this.intervalos;
    }

    public void setIntervalos(int a) {
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

    public void setFo(ArrayList<Integer> fo) {
        this.fo = fo;
    }

    public ArrayList<Double> getFe() {
        return this.fe;
    }

    public void setFe(ArrayList<Double> fe) {
        this.fe = fe;
    }

    public ArrayList<Double> getPo() {
        return this.po;
    }

    public void setPo(ArrayList<Double> po) {
        this.po = po;
    }

    public ArrayList<Double> getPe() {
        return this.pe;
    }

    public void setPe(ArrayList<Double> pe) {
        this.pe = pe;
    }

    public ArrayList<Double> getPoAC() {
        return this.poAC;
    }

    public void setPoAC(ArrayList<Double> poAC) {
        this.poAC = poAC;
    }

    public ArrayList<Double> getPeAC() {
        return this.peAC;
    }

    public void setPeAC(ArrayList<Double> peAC) {
        this.peAC = peAC;
    }

    public ArrayList<Double> getAbsPoACPeAC() {
        return this.absPoACPeAC;
    }

    public void setAbsPoACPeAC(ArrayList<Double> absPoACPeAC) {
        this.absPoACPeAC = absPoACPeAC;
    }

    public double getMax() {
        return this.max;
    }

    public void setMax(double max) {
        this.max = max;
    }


    
}
