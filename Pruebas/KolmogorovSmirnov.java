package Pruebas;

import java.util.ArrayList;

public class KolmogorovSmirnov {

    public ArrayList<ArrayList<Double>> pruebaDeBondad(ArrayList<Double> muestras_generadas, int intervalos, String opcion) {
        ChiCuadrado chiC = new ChiCuadrado();
        ArrayList<ArrayList<Double>> tablaChi = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> tablaKS = new ArrayList<ArrayList<Double>>();
        double valor;
        ArrayList<Double> pO = new ArrayList<Double>();
        int tamañoN = muestras_generadas.size();
        ArrayList<Double> pE = new ArrayList<Double>();
        ArrayList<Double> poAC = new ArrayList<Double>();
        ArrayList<Double> peAC = new ArrayList<Double>();
        ArrayList<Double> absPoACPeAC = new ArrayList<Double>();

        tablaChi = chiC.pruebaDeBondad(muestras_generadas, intervalos, opcion);

        if (opcion.equals("Uniforme")) {
            for (int i = 0; i < 4; i++) {
                tablaKS.add(tablaChi.get(i));
            }

            for (int i = 0; i < intervalos; i++) {
                valor = (double) tablaKS.get(2).get(i) / tamañoN;
                pO.add(valor);
            }
            tablaKS.add(pO);

            for (int i = 0; i < intervalos; i++) {
                valor = (double) tablaKS.get(3).get(i) / tamañoN;
                pE.add(valor);
            }
            tablaKS.add(pE);

        }

        if (opcion.equals("Exponencial")) {
            for (int i = 0; i < 4; i++) {
                tablaKS.add(tablaChi.get(i));
            }

            for (int i = 0; i < intervalos; i++) {
                valor = (double) tablaKS.get(2).get(i) / tamañoN;
                pO.add(valor);
            }
            tablaKS.add(pO);

            for (int i = 0; i < intervalos; i++) {
                pE.add(tablaChi.get(5).get(i));
            }
            tablaKS.add(pE);

        }

        if (opcion.equals("Normal")) {
            for (int i = 0; i < 4; i++) {
                tablaKS.add(tablaChi.get(i));
            }

            for (int i = 0; i < intervalos; i++) {
                valor = (double) tablaKS.get(2).get(i) / tamañoN;
                pO.add(valor);
            }
            tablaKS.add(pO);

            for (int i = 0; i < intervalos; i++) {
                pE.add(tablaChi.get(4).get(i));
            }
            tablaKS.add(pE);

        }

        valor = 0;
        for (int i = 0; i < intervalos; i++) {
            valor += pO.get(i);
            poAC.add(valor);
        }
        tablaKS.add(poAC);

        valor = 0;
        for (int i = 0; i < intervalos; i++) {
            valor += pE.get(i);
            peAC.add(valor);
        }
        tablaKS.add(peAC);

        for (int i = 0; i < intervalos; i++) {
            valor = poAC.get(i) - peAC.get(i);
            valor = Math.abs(valor);
            absPoACPeAC.add(valor);
        }
        tablaKS.add(absPoACPeAC);

        double max = 0;
        valor = 0;
        for (int i = 0; i < absPoACPeAC.size(); i++) {
            if (absPoACPeAC.get(i) > valor) {
                max = absPoACPeAC.get(i);
                System.out.println("Anashe: " + max);
            }
        }

        return tablaKS;
    }
}
