
import java.util.ArrayList;

public class KolmogorovSmirnov {

    public ArrayList<ArrayList<Double>> pruebaDeBondad(ArrayList<Double> muestras_generadas, int intervalos, String opcion) {
        ChiCuadrado chiC = new ChiCuadrado();
        ArrayList<ArrayList<Double>> tablaChi = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> tablaKS = new ArrayList<ArrayList<Double>>();
        double valor;
        int tamañoN = muestras_generadas.size();
        ArrayList<Double> absPoACPeAC = new ArrayList<Double>();

        tablaChi = chiC.pruebaDeBondad(muestras_generadas, intervalos, opcion);


        for (int i = 0; i < intervalos; i++) {
            ArrayList<Double> iteracion = new ArrayList<Double>();
            if (opcion.equals("Uniforme")) {
                iteracion.add(tablaChi.get(i).get(0));
                iteracion.add(tablaChi.get(i).get(1));
                iteracion.add(tablaChi.get(i).get(2));
                iteracion.add(tablaChi.get(i).get(3));

                valor = (double) iteracion.get(2) / tamañoN;
                iteracion.add(valor);

                valor = (double) iteracion.get(3) / tamañoN;
                iteracion.add(valor);
                tablaKS.add(iteracion);

            }

            if (opcion.equals("Exponencial")) {
                iteracion.add(tablaChi.get(i).get(0));
                iteracion.add(tablaChi.get(i).get(1));
                iteracion.add(tablaChi.get(i).get(3));
                iteracion.add(tablaChi.get(i).get(6));

                valor = (double) iteracion.get(2) / tamañoN;
                iteracion.add(valor);

                iteracion.add(tablaChi.get(i).get(5));

                tablaKS.add(iteracion);

            }

            if (opcion.equals("Normal")) {
                iteracion.add(tablaChi.get(i).get(0));
                iteracion.add(tablaChi.get(i).get(1));
                iteracion.add(tablaChi.get(i).get(3));
                iteracion.add(tablaChi.get(i).get(5));

                valor = (double) iteracion.get(2) / tamañoN;
                iteracion.add(valor);

                iteracion.add(tablaChi.get(i).get(4));

                tablaKS.add(iteracion);

            }
        }

        double peac = 0;
        double poac = 0;
        for (int i = 0; i < intervalos; i++) {
            poac += tablaKS.get(i).get(4);
            tablaKS.get(i).add(poac);

            peac += tablaKS.get(i).get(5);
            tablaKS.get(i).add(peac);

            valor = tablaKS.get(i).get(6) - tablaKS.get(i).get(7);
            valor = Math.abs(valor);
            tablaKS.get(i).add(valor);

        }

        double max = 0;
        valor = 0;
        for (int i = 0; i < absPoACPeAC.size(); i++) {
            if (absPoACPeAC.get(i) > valor) {
                max = absPoACPeAC.get(i);
            }
        }

        return tablaKS;
    }
}

