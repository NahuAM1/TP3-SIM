package Pruebas;

import java.util.*;
import java.util.ArrayList;

public class ChiCuadrado {
    private int n; // Cantidad de muestras
    private double k; // Recomendado -> K = Raiz de n, es la cantidad de intervalos
    private int v; // Grados de libertad
    private double fe; // Frecuencia esperada
    private double fo; // Frecuencia observada
    private double chi_calculado; // chi acumulado que se debe comparar para evaluar la bondad de la prueba
    private double varianza; //varianza rdo de la prueba
    private double desviacion; //desviacion rdo de la prueba
    private double min;
    private double max;
    private double media;
    private double lambda;
    private double exp = Math.exp(1);
    private double pi = Math.PI;
    private double marca_clase;
    private double probabilidad;
    private double p_cmc;
    private double p_Pac;
    private int ultimo_indice = 0;
    private double c_value = 0;
    private double c_valueAc = 0;// valor c acumulado
    private double factorCorreccion = 0.0001;



    /**
     *  UNIFORME: Numero de intervalos K = raiz de n (muestras)
     *  POISSON: Valores, hay que ver cuantas veces se repite cada uno y cual es su frecuencia observada y esperada
     *
     */


    public Double getFo() {
        return fo;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public void setNValue(ArrayList muestras_generas) {
        this.n = muestras_generas.size();
    }

    public void obtenerKTeorico() {
        k = (float) Math.sqrt(n); // Calculo de K
        k = Math.round(k); // Redondeo de K
    }

    public void setKValue(int k_value){
        k = k_value;
    }

    public double getVarianza() {
        return change(this.varianza, 4);
    }

    public double getDesviacion() {
        return this.desviacion;
    }



    //prueba de bondad sobre Hipótesis nula Uniforme
    public ArrayList<ArrayList<Double>> pruebaDeBondad(ArrayList<Double> muestras_generadas, int intervalos, String opcion) {

        this.k = intervalos;
        this.setNValue(muestras_generadas);

        ArrayList<ArrayList<Double>> tabla = new ArrayList<>();

        Collections.sort(muestras_generadas);

        /**
         *  PRUEBA DE CHI CUADRADO PARA MUESTRAS CON DISTRIBUCION UNIFORME CREO, ES EL QUE HICIMOS PARA EL TP1
         */

        if (opcion.equals("Uniforme")) {


            this.fe = this.n / this.k; // Cálculo de la frecuencia esperada, cantidad de muestras sobre cantidad de intervalos
            this.validarFeMinima();
            this.setKValue(intervalos);

            this.min = 0;
            this.max = (double) (1 / this.k);

            // Ordenar los números pseudoaleatorios para definir su intervalo correspondiente

            //Cálculo de GL, varianza y desviación
            this.v = (int) this.k - 1; // Grados de libertad

            for (int i = 0; i < this.k; i++) { // Por cada iteración calculada

                ArrayList<Double> iteracion = new ArrayList<>();

                fo = 0; // Por cada intervalo se reinicia la frecuencia observada

                for (int j = ultimo_indice; j < this.n; j++) { // Recorre las muestras generadas ya ordenadas, el último índice es para que empiece desde el intervalo anterior
                    double numero = (double) muestras_generadas.get(j); // Obtiene la muestra que se encuentra en primer lugar
                    if (min <= numero && numero < max) // Si el número está dentro del intervalo se suma uno a la frecuencia observada de este
                    {
                        fo++;
                        ultimo_indice = j;
                    }
                }

                c_value = (Math.pow((fo - this.fe), 2)) / this.fe; // Calculo del valor de c
                c_valueAc = c_valueAc + c_value; // Acumulamos el valor de c

                c_value = change(c_value, 4);
                c_valueAc = change(c_valueAc, 4);
                calcularMarcaClase(muestras_generadas);
                this.chi_calculado = c_valueAc;


                // Se guardan los valores de la iteración completa en un array
                iteracion.add(change(min, 4));
                iteracion.add(change(max, 4));
                iteracion.add(fo); //A su vez tmb es la FA
                iteracion.add(change(fe, 4));
                iteracion.add(c_value);
                iteracion.add(c_valueAc);

                // Se guarda ese array en otro array para pasarlo a la tabla
                tabla.add(iteracion);
                min = max; // Se actualiza el minimo del intervalo
                max = (Double) (min + (1 / this.k)); // Se actualiza el maximo del intervalo
            }
        }

        for (int i = 0; i < tabla.size(); i++) {
            ArrayList<Double> fila = tabla.get(i);
            System.out.println("");
            for (int j = 0; j < fila.size(); j++) {
                System.out.print(fila.get(j) + "   ");
            }
        }

        /**
         *  PRUEBA DE CHI CUADRADO PARA DISTRIBUCION EXPONENCIAL
         */

        if (opcion.equals("Exponencial")) {


            setNValue(muestras_generadas);
            calcularLambda(muestras_generadas);


            min = 0; // CAMBIO
            max = 1; // CAMBIO

            ArrayList<Double> filaRecuperada = new ArrayList<>();


            // DEFINIR COMO SE VA A EMPEZAR CON EL MINIMO Y EL MAXIMO


            for (int i = 0; i < k; i++) {

                ArrayList<Double> iteracion = new ArrayList<>(); // Fila que contiene la iteracion que se va a guardar en la tabla

                for (int j = ultimo_indice; j < this.n; j++) { // Recorre las muestras generadas ya ordenadas, el último índice es para que empiece desde el intervalo anterior
                    double numero = (double) muestras_generadas.get(j); // Obtiene la muestra que se encuentra en primer lugar

                    if (min <= numero && numero < max) // Si el número está dentro del intervalo se suma uno a la frecuencia observada de este
                    {
                        fo++;
                        ultimo_indice = j;
                    }
                } // CAMBIO

                    calcularMarcaClase(muestras_generadas);
                    calcularP_cmc();
                    calcularP_pac();

                    fe = p_Pac * n;

                    // TRUNCAR TODOS LOS VALORES

                    truncarValoresTablaChi();

                    iteracion.add(min);
                    iteracion.add(max);
                    iteracion.add(change(marca_clase + factorCorreccion, 4)); // CAMBIO
                    iteracion.add(fo);
                    iteracion.add(p_cmc);
                    iteracion.add(p_Pac);
                    iteracion.add(fe);

                    tabla.add(iteracion);

                    // ESTO PREGUNTAR
                    min = max;
                    max = max + 1;
                    fo = 0; // CAMBIO

            }

            // Estas variables se van a usar para recuperar los valores que nos interesan ver para completar la tabla de chi cuadrado
            double minimoAnterior = 0;
            // Las dos frecuencias se van a ir sumando en caso de que fe < 5
            double foSumada = 0;
            double feSumada = 0;
            // Guardamos el lugar donde fue agregada la ultima iteracion
            int casillaUltimoAgregado = -1;
            // Si la bandera esta en false es porque la fe < 5 y tenemos que guardar el minimo del intervalo
            boolean flag = true;


        }

        if (opcion.equals("Normal")) {
            setNValue(muestras_generadas);
            calcularDesviacion(muestras_generadas);

            ArrayList<Double> filaRecuperada = new ArrayList<>();

            // DEFINIR COMO SE VA A EMPEZAR CON EL MINIMO Y EL MAXIMO

            min = 0;
            max = 1;

            for (int i = 0; i < k; i++) {

                ArrayList<Double> iteracion = new ArrayList<>();

                for (int j = ultimo_indice; j < this.n; j++) { // Recorre las muestras generadas ya ordenadas, el último índice es para que empiece desde el intervalo anterior
                    double numero = (double) muestras_generadas.get(j); // Obtiene la muestra que se encuentra en primer lugar


                    if (min <= numero && numero < max) // Si el número está dentro del intervalo se suma uno a la frecuencia observada de este
                    {
                        fo++;
                        ultimo_indice = j;
                    }
                }

                    calcularMarcaClase(muestras_generadas);
                    calcularProbabilidadNormal();

                    fe = probabilidad * n;

                    truncarValoresTablaChi();

                    iteracion.add(min);
                    iteracion.add(max);
                    iteracion.add(change(marca_clase + factorCorreccion, 4));
                    iteracion.add(fo);
                    iteracion.add(probabilidad);
                    iteracion.add(fe);

                    tabla.add(iteracion);

                    // ESTO PREGUNTAR
                    min = max;
                    max = max + 1;
                    fo = 0; // CAMBIO

            }

            double minimoAnterior = 0;
            double foSumada = 0;
            double feSumada = 0;
            int casillaUltimoAgregado = -1;
            boolean flag = true;



            mostrarTablas(tabla);

        }

        if (opcion.equals("Poisson")) {
            setNValue(muestras_generadas);
            calcularMedia(muestras_generadas);

            ArrayList<Double> muestras_unicas = new ArrayList<>();

            // Recorremos todas las muestras y las guardamos en una ArrayList donde no esten repetidos los numeros
            for (Double muestra_generada : muestras_generadas) {
                if (!muestras_unicas.contains(muestra_generada)) {
                    muestras_unicas.add(muestra_generada);
                }
            }

            // Nos quedamos con el maximo y el minimo de los numeros que aparecieron en los datos
            double minimo = muestras_unicas.get(0);
            double maximo = muestras_unicas.get(muestras_unicas.size() - 1);

            // Calculamos la cantidad de iteraciones que se tienen que hacer
            double cantidadIteraciones = maximo - minimo;

            // Limpiamos el ArrayList para llenarlo con los datos correctos
            muestras_unicas.clear();

            // Se llena el ArrayList con todos los numeros desde el minimo hasta el maximo incluido
            for (int i = 0; i <= cantidadIteraciones; i++) {
                muestras_unicas.add(minimo);
                minimo++;
            }


            for (int i = 0; i < muestras_unicas.size(); i++)
            {
                ArrayList<Double> iteracion = new ArrayList<>();

                for (int j = 0; j < muestras_generadas.size(); j++)
                {
                    double valor = muestras_generadas.get(j);

                    if (muestras_generadas.get(j).equals(muestras_unicas.get(i)))
                    {
                        fo += 1;
                    }
                }

                calcularMedia(muestras_generadas);
                calcularProbabilidadPoisson(muestras_unicas.get(i));
                fe = Math.round(probabilidad * n);

                truncarValoresTablaChi();

                iteracion.add(muestras_unicas.get(i));
                iteracion.add(fo);
                iteracion.add(probabilidad);
                iteracion.add(fe);

                tabla.add(iteracion);

                min = max;
                max = max + 1;
                fo = 0; // CAMBIO
            }
            mostrarTablas(tabla);
        }
        return tabla;
    }

    //Devolver el resultado de evaluar la serie en Chi cuadrado
    public boolean evaluarBondad(double confianza, int gl) {
        //obtenemos el chi_tabu
        double chi_tab = this.getChiValue(confianza, gl);
        //retornamos la comparación de los chi para saber si NO se rechaza o si la H0

        if (this.chi_calculado <= chi_tab) {
            return true;
        }
        return false;
    }

//    public double getChiTabulado(double confianza, int gl) {
//        ChiTabulado chi = new ChiTabulado();
//        double alfa = confianza / 100;
//        return chi.getValue(alfa, gl);
//    }

    public ArrayList<ArrayList<Double>> calcularTablaChiCuadrado(ArrayList<ArrayList<Double>> tabla, String opcion) {

        ArrayList<Double> filaRecuperada = new ArrayList<>();
        ArrayList<ArrayList<Double>> tabla2 = new ArrayList<>();

        double minimoAnterior = 0;
        double foSumada = 0;
        double feSumada = 0;
        int casillaUltimoAgregado = -1;
        boolean flag = true;

        if (opcion.equals("Exponencial")) {
            // Recorremos la tabla donde agregamos todas las iteraciones
            for (int i = 0; i < tabla.size(); i++) {

                // Creamos una nueva variable iteracion donde se van a guardar los valores que van a parar a la tabla chi cuadrado
                ArrayList<Double> iteracion2 = new ArrayList<>();

                // Recuperamos fila por fila de la tabla
                filaRecuperada = tabla.get(i);

                // Valores que nos interesan para hacer la tabla chi cuadrado
                min = filaRecuperada.get(0);
                fo = filaRecuperada.get(3);
                fe = filaRecuperada.get(6);

                // Acumulamos los valores de la frecuencia esperada y observada
                foSumada = fo + foSumada;
                feSumada = fe + feSumada;

                // Si la frecuencia esperada es menor que 5
                if ((feSumada) < 5) {
                    if (flag) {
                        minimoAnterior = min; // Guardamos el valor del minimo del intervalo
                        flag = false; // Hacemos falsa la bandera para que no se nos cambie el valor del intervalo
                    }

                    // Si llegamos a la ultima iteracion y la misma tiene una fe < 5, tenemos que juntar los datos con la ultima
                    // iteracion que haya tenido fe > 5, osea la de arriba
                    if (i + 1 == tabla.size()) {

                        // Creamos una nueva iteracion que va a reemplazar los datos de la ultima iteracion con fe > 5
                        ArrayList<Double> iteracion3 = new ArrayList<>();

                        // Removemos de la tabla la ultima iteracion agregada y la guardamos en una variabla
                        iteracion2 = tabla2.remove(casillaUltimoAgregado);
                        // Recuperamos los valores de la misma
                        min = iteracion2.get(0);
                        fo = iteracion2.get(2);
                        fe = iteracion2.get(3);
                        // Restamos del "c" acumulado el valor de la ultima iteracion que acabamos de borrar de la tabla
                        c_valueAc = c_valueAc - iteracion2.get(4);

                        // Actualizamos el valor de las fe y fo, con los valores de la utlima fila (la que tenia fe < 5)
                        fo = fo + foSumada;
                        fe = fe + feSumada;

                        // Calculamos los nuevos valores de "c" y "c" acumulado
                        calcularC_Value();
                        calcularC_ValueAc();
                        // Cargamos los valores nuevos en la iteracion
                        iteracion3.add(0, min);
                        iteracion3.add(1, filaRecuperada.get(1)); // Actualizamos el maximo del intevalo
                        iteracion3.add(2, fo);
                        iteracion3.add(3, fe);
                        iteracion3.add(4, change(c_value, 4));
                        iteracion3.add(5, change(c_valueAc, 4));

                        // Agregamos la iteracion a la tabla
                        tabla2.add(iteracion3);
                    }
                }
                else // Si la fe > 5
                {
                    fe = feSumada;
                    fo = foSumada;
                    max = filaRecuperada.get(1);
                    calcularC_Value();
                    calcularC_ValueAc();

                    truncarValoresTablaChi();

                    // Si la bandera no se activo es porque la iteracion tenia fe > 5, por lo que el minimo es el correspondiente a su fila
                    if (flag) {
                        iteracion2.add(min);
                    }
                    else // Si la bandera se activo significa que se tuvieron que recorrer varias iteraciones, por lo que el minimo es el de la primera, el cual guardamos antes
                    {
                        iteracion2.add(minimoAnterior);
                    }

                    iteracion2.add(max);
                    iteracion2.add(fo);
                    iteracion2.add(fe);
                    iteracion2.add(c_value);
                    iteracion2.add(c_valueAc);

                    tabla2.add(iteracion2);
                    casillaUltimoAgregado++;

                    minimoAnterior = 0;
                    foSumada = 0;
                    feSumada = 0;
                    flag = true;
                }
            }
        }

        if (opcion.equals("Normal")) {
            for (int i = 0; i < tabla.size(); i++) {

                ArrayList<Double> iteracion2 = new ArrayList<>();

                filaRecuperada = tabla.get(i);
                min = filaRecuperada.get(0);
                fo = filaRecuperada.get(3);
                fe = filaRecuperada.get(5);

                foSumada = fo + foSumada;
                feSumada = fe + feSumada;

                if ((feSumada) < 5) {
                    if (flag) {
                        minimoAnterior = min;
                        flag = false;
                    }

                    // Si llegamos a la ultima iteracion y la misma tiene una fe < 5, tenemos que juntar los datos con la ultima
                    // iteracion que haya tenido fe > 5, osea la de arriba
                    if (i + 1 == tabla.size()) {

                        // Creamos una nueva iteracion que va a reemplazar los datos de la ultima iteracion con fe > 5
                        ArrayList<Double> iteracion3 = new ArrayList<>();

                        // Removemos de la tabla la ultima iteracion agregada y la guardamos en una variabla
                        iteracion2 = tabla2.remove(casillaUltimoAgregado);
                        // Recuperamos los valores de la misma
                        min = iteracion2.get(0);
                        fo = iteracion2.get(2);
                        fe = iteracion2.get(3);
                        // Restamos del "c" acumulado el valor de la ultima iteracion que acabamos de borrar de la tabla
                        c_valueAc = c_valueAc - iteracion2.get(4);

                        // Actualizamos el valor de las fe y fo, con los valores de la utlima fila (la que tenia fe < 5)
                        fo = fo + foSumada;
                        fe = fe + feSumada;

                        // Calculamos los nuevos valores de "c" y "c" acumulado
                        calcularC_Value();
                        calcularC_ValueAc();
                        // Cargamos los valores nuevos en la iteracion
                        iteracion3.add(0, min);
                        iteracion3.add(1, filaRecuperada.get(1)); // Actualizamos el maximo del intevalo
                        iteracion3.add(2, fo);
                        iteracion3.add(3, fe);
                        iteracion3.add(4, change(c_value, 4));
                        iteracion3.add(5, change(c_valueAc, 4));

                        // Agregamos la iteracion a la tabla
                        tabla2.add(iteracion3);
                    }

                }
                else // Si la fe > 5
                {
                    fe = feSumada;
                    fo = foSumada;
                    max = filaRecuperada.get(1);
                    calcularC_Value();
                    calcularC_ValueAc();

                    truncarValoresTablaChi();

                    // Si la bandera no se activo es porque la iteracion tenia fe > 5, por lo que el minimo es el correspondiente a su fila
                    if (flag) {
                        iteracion2.add(min);
                    }
                    else // Si la bandera se activo significa que se tuvieron que recorrer varias iteraciones, por lo que el minimo es el de la primera, el cual guardamos antes
                    {
                        iteracion2.add(minimoAnterior);
                    }

                    iteracion2.add(max);
                    iteracion2.add(fo);
                    iteracion2.add(fe);
                    iteracion2.add(c_value);
                    iteracion2.add(c_valueAc);

                    tabla2.add(iteracion2);
                    casillaUltimoAgregado++;

                    minimoAnterior = 0;
                    foSumada = 0;
                    feSumada = 0;
                    flag = true;
                }
            }
        }

        if (opcion.equals("Poisson")) {
            for (int i = 0; i < tabla.size(); i++) {

                ArrayList<Double> iteracion2 = new ArrayList<>();

                filaRecuperada = tabla.get(i);
                min = filaRecuperada.get(0);
                fo = filaRecuperada.get(1);
                fe = filaRecuperada.get(3);

                foSumada = fo + foSumada;
                feSumada = fe + feSumada;


                if (feSumada < 5) {
                    if (flag) {
                        minimoAnterior = min;
                        flag = false;
                    }

                    // Si llegamos a la ultima iteracion y la misma tiene una fe < 5, tenemos que juntar los datos con la ultima
                    // iteracion que haya tenido fe > 5, osea la de arriba
                    if (i + 1 == tabla.size()) {

                        // Creamos una nueva iteracion que va a reemplazar los datos de la ultima iteracion con fe > 5
                        ArrayList<Double> iteracion3 = new ArrayList<>();

                        // Removemos de la tabla la ultima iteracion agregada y la guardamos en una variabla
                        iteracion2 = tabla2.remove(casillaUltimoAgregado);
                        // Recuperamos los valores de la misma
                        min = iteracion2.get(0);
                        fo = iteracion2.get(2);
                        fe = iteracion2.get(3);
                        // Restamos del "c" acumulado el valor de la ultima iteracion que acabamos de borrar de la tabla
                        c_valueAc = c_valueAc - iteracion2.get(4);

                        // Actualizamos el valor de las fe y fo, con los valores de la utlima fila (la que tenia fe < 5)
                        fo = fo + foSumada;
                        fe = fe + feSumada;

                        // Calculamos los nuevos valores de "c" y "c" acumulado
                        calcularC_Value();
                        calcularC_ValueAc();
                        // Cargamos los valores nuevos en la iteracion
                        iteracion3.add(0, min);
                        iteracion3.add(1, filaRecuperada.get(0)); // Actualizamos el maximo del intevalo
                        iteracion3.add(2, fo);
                        iteracion3.add(3, fe);
                        iteracion3.add(4, change(c_value, 4));
                        iteracion3.add(5, change(c_valueAc, 4));

                        // Agregamos la iteracion a la tabla
                        tabla2.add(iteracion3);
                    }
                }
                else // Si la fe > 5
                {
                    fe = feSumada;
                    fo = foSumada;
                    max = filaRecuperada.get(0);
                    calcularC_Value();
                    calcularC_ValueAc();

                    truncarValoresTablaChi();

                    // Se controla si la bandera se activo o no porque si no se activo la variable "minimoAnterior" no va a tener ningun valor
                    // Si la bandera no se activo es porque la iteracion tenia fe > 5, por lo que el minimo es el correspondiente a su fila
                    if (flag) {
                        iteracion2.add(min);
                        iteracion2.add(max);
                    }
                    else // Si la bandera se activo significa que se tuvieron que recorrer varias iteraciones, por lo que el minimo es el de la primera, el cual guardamos antes
                    {
                        iteracion2.add(minimoAnterior);
                        iteracion2.add(max);
                    }

                    iteracion2.add(fo);
                    iteracion2.add(fe);
                    iteracion2.add(c_value);
                    iteracion2.add(c_valueAc);

                    tabla2.add(iteracion2);
                    casillaUltimoAgregado++;

                    minimoAnterior = 0;
                    foSumada = 0;
                    feSumada = 0;
                    flag = true;
                }

            }
        }

        return tabla2;
    }


    public void mostrarTablas(ArrayList<ArrayList<Double>> tabla){
        for (int i = 0; i < tabla.size(); i++) {
            ArrayList<Double> fila = tabla.get(i);
            System.out.println("");
            for (int j = 0; j < fila.size(); j++) {
                System.out.print(fila.get(j) + "   ");
            }
        }
    }

    public void truncarValoresTablaChi() {
        min = change(min, 4);
        max = change(max, 4);
        fo = change(fo, 4);
        fe = change(fe, 4);
        c_value = change(c_value,4);
        c_valueAc = change(c_valueAc,4);
        p_cmc = change(p_cmc, 4);
        p_Pac = change(p_Pac,4);
        probabilidad = change(probabilidad, 4);
    }

    public void calcularLambda(ArrayList<Double> muestras_generadas) {
        double suma = 0;

        for (Double muestras_generada : muestras_generadas) {
            suma += muestras_generada;
        }

        this.media = (suma / n);
        this.lambda = (1 / media);
    }

    public void calcularDesviacion(ArrayList<Double> muestras_generadas) {
        double suma = 0;
        double desviacion_individual = 0;

        for (Double muestra_generada : muestras_generadas) {
            suma += muestra_generada;
        }

        this.media = (suma / n);

        for (Double muestra_generada : muestras_generadas) {
            desviacion_individual += Math.pow((muestra_generada - media), 2);
        }

        this.varianza = desviacion_individual / (n - 1);
        this.desviacion = change(Math.sqrt(varianza), 4);
    }

    public void calcularMedia(ArrayList<Double> muestras_generadas) {
        double suma = 0;

        for (Double muestra_generada : muestras_generadas) {
            suma += muestra_generada;
        }

        this.media = change((suma / n), 4);
    }

    public void calcularMarcaClase(ArrayList<Double> muestras_generadas) {
        this.marca_clase = change((min + (max - factorCorreccion)) / 2, 4);
    }

    public void calcularP_cmc() {
        this.p_cmc = (lambda * Math.pow(exp, (-lambda * marca_clase)) * ((max - factorCorreccion) - min));
    }

    public void calcularP_pac() {
        this.p_Pac = ((1 - Math.pow(exp, (-lambda * (max - factorCorreccion)))) - (1 - Math.pow(exp, (-lambda * min))));
    }

    public void calcularProbabilidadNormal() {
        this.probabilidad = ((1 / (desviacion * Math.sqrt(( 2 * pi )))) * Math.pow(exp, ((-0.5) * Math.pow(((marca_clase - media) / desviacion), 2)) * ((max - factorCorreccion) - min)));
    }

    public void calcularProbabilidadPoisson(double valor) {
        valor = (int) valor;
        this.probabilidad = ((Math.pow(media, valor)) * Math.pow(exp, (-media))) / factorial(valor);
    }

    public void calcularC_Value() {
        c_value = (Math.pow((fo - fe), 2)) / fe; // Calculo del valor de c
    }

    public void calcularC_ValueAc() {
        c_valueAc = c_value + c_valueAc;
    }

    //Devolver el chi value tabulado para una confianza y GL determinados
    public double getChiValue(double confianza, int gl) {
        ChiTabulado chi = new ChiTabulado();
        double alfa = confianza / 100;
        return chi.getValue(alfa, gl);
    }

    // Método para truncar
    private static double change(double value, int decimalpoint) // Value = valor a truncar, Decimalpoint = Cantidad decimales
    {
        // Using the pow() method
        value = value * Math.pow(10, decimalpoint);
        value = Math.floor(value);
        value = value / Math.pow(10, decimalpoint);
        return value;
    }

    public double factorial (double numero) {
        if (numero==0)
        {
            return 1;
        }
        else
        {
            return numero * factorial(numero-1);
        }
    }

    /**
     * ESTO NO SE SI LO TENEMOS QUE USAR, PORQUE SI SE DISMINUYEN LOS INTERVALOS PERO HAY QUE AGRUPARLOS
     */
    // Validación de fe >= 5, si no se achican el nro de intervalos
    private void validarFeMinima() {
        if (this.fe < 5){
            double k_inicial = this.k;
            while ((this.n / k_inicial) < 5.0) {
                k_inicial -= 1;
            }
            this.k = k_inicial;
            this.fe = (this.n / k_inicial);
        }
    }
}
