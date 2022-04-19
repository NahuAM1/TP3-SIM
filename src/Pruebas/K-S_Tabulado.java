
public class K-S_Tabulado {
    private ArrayList<Double> ks_value = new ArrayList<Double>();
    

    public K-S_Tabulado(){
        this.ks_value = {0.975, 0.84189, 0.70760, 0.62394, 0.56328, 0.51926, 0.48342, 0.45427, 0.43001, 0.40925, 0.39122, 0.37543, 0.36143, 0.34890, 0.33750, 0.32733, 0.31769, 0.30936, 0.30143, 0.29408,
                    0.22425, 0.22743, 0.23076, 0.23424, 0.23788, 0.24170, 0.24571, 0.24993, 0.25438, 0.25908, 0.26404, 0.26931, 0.27490 , 0.28087, 0.28724};
    }

    public double getTabulado(int n){
        if (n<=35){
            return this.ks_value.get(n-1); 
        }
        else{
            double calcular = 1.36/(Math.sqrt(n))
            return truncarNumero(calcular);
        }
    }

    private Double truncarNumero(double valor) {
        // Este metodo lo que hace es truncar el valor que se le pasa por parametro a 4 decimales, en caso de querer menos o mas
        // se le quitan o agregan"#" a la linea de abajo por los que necesite.
        DecimalFormat df = new DecimalFormat("#.####");
        String valor_truncado = df.format(valor).replace(",", ".");
        return Double.parseDouble(valor_truncado);
    }

}