package com.example.tp3;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.util.ArrayList;

public class InterfazController {

    private final ObservableList<FilaTabla> datosTablaSuperior = FXCollections.observableArrayList();
    private final ObservableList<FilaTabla> datosTablaInferior = FXCollections.observableArrayList();
    private final ObservableList<FilaTabla> datosTablaSerie = FXCollections.observableArrayList();
    String opcion;

    @FXML
    private CheckBox checkUniforme = new CheckBox();

    @FXML
    private CheckBox checkNormal = new CheckBox();

    @FXML
    private CheckBox checkExponencial = new CheckBox();

    @FXML
    private CheckBox checkPoisson = new CheckBox();

    @FXML
    private CheckBox checkBoxMuller = new CheckBox();

    @FXML
    private CheckBox checkConvolucion = new CheckBox();

    @FXML
    private TextField muestras = new TextField();

    @FXML
    private TextField limSuperior = new TextField();

    @FXML
    private TextField limInferior = new TextField();

    @FXML
    private TextField mediaNormal = new TextField();

    @FXML
    private TextField desviacion = new TextField();

    @FXML
    private TextField lambdaOMediaCampo = new TextField();

    @FXML
    private ChoiceBox choiceBoxLambdaMedia = new ChoiceBox();

    @FXML
    private ChoiceBox choiceBoxIntervalos = new ChoiceBox();

    @FXML
    private Button botonChiCuadrado = new Button();

    @FXML
    private Button botonKS = new Button();

    @FXML
    private Button botonGenerar = new Button();

    @FXML
    private TableView<FilaTabla> tablaSuperior = new TableView<>();

    @FXML
    private TableView<FilaTabla> tablaInferior = new TableView<>();

    @FXML
    private TableView<FilaTabla> tablaSerie = new TableView<>();

    @FXML
    public void initialize() {
        desactivarCamposYBotones();
        cargarChoiceBox();

        tablaSuperior.getItems().clear();
        tablaSuperior.getColumns().clear();

        setearTabla();
    }

    private void activarChecks() {
        checkUniforme.setDisable(false);
        checkNormal.setDisable(false);
        checkExponencial.setDisable(false);
        checkPoisson.setDisable(false);
    }

    private void activarBotones() {
        botonKS.setDisable(false);
        botonChiCuadrado.setDisable(false);
        botonGenerar.setDisable(false);
    }

    private void desactivarCamposYBotones() {
        limSuperior.setDisable(true);
        limInferior.setDisable(true);
        mediaNormal.setDisable(true);
        desviacion.setDisable(true);
        checkBoxMuller.setDisable(true);
        checkConvolucion.setDisable(true);
        choiceBoxLambdaMedia.setDisable(true);
        lambdaOMediaCampo.setDisable(true);
        botonGenerar.setDisable(true);
        botonChiCuadrado.setDisable(true);
        botonKS.setDisable(true);
        muestras.setDisable(true);
    }

    private void cargarChoiceBox() {
        ObservableList <String> observableListMediaLambda = FXCollections.observableArrayList();
        observableListMediaLambda.add("Lambda");
        observableListMediaLambda.add("Media");
        choiceBoxLambdaMedia.setItems(observableListMediaLambda);

        ObservableList <String> observableListIntervalos = FXCollections.observableArrayList();
        observableListIntervalos.add("8");
        observableListIntervalos.add("10");
        observableListIntervalos.add("15");
        observableListIntervalos.add("20");
        choiceBoxIntervalos.setItems(observableListIntervalos);

    }

    public void checkUniforme() {
        if (checkUniforme.isSelected()) {
            muestras.setDisable(false);
            limInferior.setDisable(false);
            limSuperior.setDisable(false);
            // Desactivar los demas checks
            checkNormal.setDisable(true);
            checkExponencial.setDisable(true);
            checkPoisson.setDisable(true);
            activarBotones();
            opcion = "Uniforme";
        }
        else
        {
            activarChecks();
            desactivarCamposYBotones();
        }
    }

    public void checkNormal() {
        if(checkNormal.isSelected()) {
            muestras.setDisable(false);
            mediaNormal.setDisable(false);
            desviacion.setDisable(false);
            checkBoxMuller.setDisable(false);
            checkConvolucion.setDisable(false);
            checkUniforme.setDisable(true);
            checkExponencial.setDisable(true);
            checkPoisson.setDisable(true);
            activarBotones();
            opcion = "Normal";
        }
        else
        {
            activarChecks();
            desactivarCamposYBotones();
        }

    }

    public void checkExponencial() {
        if(checkExponencial.isSelected()) {
            muestras.setDisable(false);
            choiceBoxLambdaMedia.setDisable(false);
            lambdaOMediaCampo.setDisable(false);
            checkUniforme.setDisable(true);
            checkNormal.setDisable(true);
            checkPoisson.setDisable(true);
            activarBotones();
            opcion = "Exponencial";
        }
        else
        {
            activarChecks();
            desactivarCamposYBotones();
        }

    }

    public void checkPoisson() {
        if(checkPoisson.isSelected()) {
            muestras.setDisable(false);
            choiceBoxLambdaMedia.setDisable(false);
            lambdaOMediaCampo.setDisable(false);
            checkUniforme.setDisable(true);
            checkNormal.setDisable(true);
            checkExponencial.setDisable(true);
            activarBotones();
            opcion = "Poisson";
        }
        else
        {
            activarChecks();
            desactivarCamposYBotones();
        }

    }

    public void checkBoxMuller() {
        if(checkBoxMuller.isSelected()) {
            checkConvolucion.setDisable(true);
        }
        else
        {
            checkConvolucion.setDisable(false);
        }

    }

    public void checkConvolucion() {
        if(checkConvolucion.isSelected()) {
            checkBoxMuller.setDisable(true);
        }
        else
        {
            checkBoxMuller.setDisable(false);
        }

    }

    @FXML
    public void botonChiCuadrado() {}

    @FXML
    public void botonGenerar() {
        validarCampoVacio();
    }

    @FXML
    public void botonKS() {}

    private boolean validarTexto(String datos) {
        boolean r = datos.matches("[1-9][0-9]*+0?");
        return r;
    }

    public void mostrarMensajeCampoVacio() {
        JOptionPane.showMessageDialog(null, "No hay valores cargados", "Error!", 0);
    }

    public void mostrarMensajeTextoMalCargado() {
        JOptionPane.showMessageDialog(null, "Los valores cargador no pueden ser 0, negativos o texto", "Error!", 0);
    }

    private void validarCampoVacio() {

        boolean campoVacio = false;

        if (!muestras.isDisable() && !campoVacio) {
            campoVacio = muestras.getText().equals("");
        }

        if (!limSuperior.isDisable() && !campoVacio) {
            campoVacio = limSuperior.getText().equals("");
        }

        if (!limInferior.isDisable() && !campoVacio) {
            campoVacio = limInferior.getText().equals("");
        }

        if (!mediaNormal.isDisable() && !campoVacio) {
            campoVacio = mediaNormal.getText().equals("");
        }

        if (!desviacion.isDisable() && !campoVacio) {
            campoVacio = desviacion.getText().equals("");
        }

        if (!lambdaOMediaCampo.isDisable() && !campoVacio) {
            campoVacio = lambdaOMediaCampo.getText().equals("");
        }

        if (campoVacio)
            mostrarMensajeCampoVacio();
        else
            validarTextoIngresado();


        /*
        if (!.isDisable() && !campoVacio) {
            campoVacio = .getText().equals("");
        }
         */


    }

    public void validarTextoIngresado() {

        boolean campoMalIngresado = false;

        if (!muestras.isDisable() && !campoMalIngresado) {
            campoMalIngresado = !validarTexto(muestras.getText());
        }

        if (!limSuperior.isDisable() && !campoMalIngresado) {
            campoMalIngresado = !validarTexto(limSuperior.getText());
        }

        if (!limInferior.isDisable() && !campoMalIngresado) {
            campoMalIngresado = !validarTexto(limInferior.getText());
        }

        if (!mediaNormal.isDisable() && !campoMalIngresado) {
            campoMalIngresado = !validarTexto(mediaNormal.getText());
        }

        if (!desviacion.isDisable() && !campoMalIngresado) {
            campoMalIngresado = !validarTexto(desviacion.getText());
        }

        if (!lambdaOMediaCampo.isDisable() && !campoMalIngresado) {
            campoMalIngresado = !validarTexto(lambdaOMediaCampo.getText());
        }

        if (campoMalIngresado)
            mostrarMensajeTextoMalCargado();

        else
        {
            setearTabla();
            /**
             * CARGAR DATOS CARGAR DATOS CARGAR DATOS CARGAR DATOS CARGAR DATOS
             */
        }

        /*
        if (!.isDisable() && !campoMalIngresado) {
            campoMalIngresado = !validarTexto(.getText());
        }
         */

    }

    public static class FilaTabla {
        private SimpleDoubleProperty minimo;
        private SimpleDoubleProperty maximo;
        private SimpleDoubleProperty minimo_Chi;
        private SimpleDoubleProperty maximo_Chi;
        private SimpleDoubleProperty minimo_KS;
        private SimpleDoubleProperty maximo_KS;
        private SimpleDoubleProperty fo;
        private SimpleDoubleProperty fe;
        private SimpleDoubleProperty fo_Chi;
        private SimpleDoubleProperty fe_Chi;
        private SimpleDoubleProperty fo_KS;
        private SimpleDoubleProperty fe_KS;
        private SimpleDoubleProperty c;
        private SimpleDoubleProperty c_Ac;
        private SimpleDoubleProperty marca_clase;
        private SimpleDoubleProperty c_mc;
        private SimpleDoubleProperty c_Pac;
        private SimpleDoubleProperty p;
        private SimpleDoubleProperty valor;
        private SimpleDoubleProperty serie;
        private SimpleDoubleProperty po;
        private SimpleDoubleProperty pe;
        private SimpleDoubleProperty po_Ac;
        private SimpleDoubleProperty pe_Ac;
        private SimpleDoubleProperty abs_po_pe;
        private SimpleDoubleProperty max;

        public double getMinimo_KS() {
            return minimo_KS.get();
        }

        public SimpleDoubleProperty minimo_KSProperty() {
            return minimo_KS;
        }

        public void setMinimo_KS(double minimo_KS) {
            this.minimo_KS = new SimpleDoubleProperty(minimo_KS);
        }

        public double getMaximo_KS() {
            return maximo_KS.get();
        }

        public SimpleDoubleProperty maximo_KSProperty() {
            return maximo_KS;
        }

        public void setMaximo_KS(double maximo_KS) {
            this.maximo_KS = new SimpleDoubleProperty(maximo_KS);
        }

        public double getFo_KS() {
            return fo_KS.get();
        }

        public SimpleDoubleProperty fo_KSProperty() {
            return fo_KS;
        }

        public void setFo_KS(double fo_KS) {
            this.fo_KS = new SimpleDoubleProperty(fo_KS);
        }

        public double getFe_KS() {
            return fe_KS.get();
        }

        public SimpleDoubleProperty fe_KSProperty() {
            return fe_KS;
        }

        public void setFe_KS(double fe_KS) {
            this.fe_KS = new SimpleDoubleProperty(fe_KS);
        }

        public double getPo() {
            return po.get();
        }

        public SimpleDoubleProperty poProperty() {
            return po;
        }

        public void setPo(double po) {
            this.po = new SimpleDoubleProperty(po);
        }

        public double getPe() {
            return pe.get();
        }

        public SimpleDoubleProperty peProperty() {
            return pe;
        }

        public void setPe(double pe) {
            this.pe = new SimpleDoubleProperty(pe);
        }

        public double getPo_Ac() {
            return po_Ac.get();
        }

        public SimpleDoubleProperty po_AcProperty() {
            return po_Ac;
        }

        public void setPo_Ac(double po_Ac) {
            this.po_Ac = new SimpleDoubleProperty(po_Ac);
        }

        public double getPe_Ac() {
            return pe_Ac.get();
        }

        public SimpleDoubleProperty pe_AcProperty() {
            return pe_Ac;
        }

        public void setPe_Ac(double pe_Ac) {
            this.pe_Ac = new SimpleDoubleProperty(pe_Ac);
        }

        public double getAbs_po_pe() {
            return abs_po_pe.get();
        }

        public SimpleDoubleProperty abs_po_peProperty() {
            return abs_po_pe;
        }

        public void setAbs_po_pe(double abs_po_pe) {
            this.abs_po_pe = new SimpleDoubleProperty(abs_po_pe);
        }

        public double getMax() {
            return max.get();
        }

        public SimpleDoubleProperty maxProperty() {
            return max;
        }

        public void setMax(double max) {
            this.max = new SimpleDoubleProperty(max);
        }

        public double getSerie() {
            return serie.get();
        }

        public SimpleDoubleProperty serieProperty() {
            return serie;
        }

        public void setSerie(double serie) {
            this.serie = new SimpleDoubleProperty(serie);
        }

        public double getValor() {
            return valor.get();
        }

        public SimpleDoubleProperty valorProperty() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = new SimpleDoubleProperty(valor);
        }

        public double getMinimo() {
            return minimo.get();
        }

        public SimpleDoubleProperty minimoProperty() {
            return minimo;
        }

        public void setMinimo(double minimo) {
            this.minimo = new SimpleDoubleProperty(minimo);
        }

        public double getMaximo() {
            return maximo.get();
        }

        public SimpleDoubleProperty maximoProperty() {
            return maximo;
        }

        public void setMaximo(double maximo) {
            this.maximo = new SimpleDoubleProperty(maximo);
        }

        public double getMinimo_Chi() {
            return minimo_Chi.get();
        }

        public SimpleDoubleProperty minimo_ChiProperty() {
            return minimo_Chi;
        }

        public void setMinimo_Chi(double minimo_Chi) {
            this.minimo_Chi = new SimpleDoubleProperty(minimo_Chi);
        }

        public double getMaximo_Chi() {
            return maximo_Chi.get();
        }

        public SimpleDoubleProperty maximo_ChiProperty() {
            return maximo_Chi;
        }

        public void setMaximo_Chi(double maximo_Chi) {
            this.maximo_Chi = new SimpleDoubleProperty(maximo_Chi);
        }

        public double getFo() {
            return fo.get();
        }

        public SimpleDoubleProperty foProperty() {
            return fo;
        }

        public void setFo(double fo) {
            this.fo = new SimpleDoubleProperty(fo);
        }

        public double getFe() {
            return fe.get();
        }

        public SimpleDoubleProperty feProperty() {
            return fe;
        }

        public void setFe(double fe) {
            this.fe = new SimpleDoubleProperty(fe);
        }

        public double getFo_Chi() {
            return fo_Chi.get();
        }

        public SimpleDoubleProperty fo_ChiProperty() {
            return fo_Chi;
        }

        public void setFo_Chi(double fo_Chi) {
            this.fo_Chi = new SimpleDoubleProperty(fo_Chi);
        }

        public double getFe_Chi() {
            return fe_Chi.get();
        }

        public SimpleDoubleProperty fe_ChiProperty() {
            return fe_Chi;
        }

        public void setFe_Chi(double fe_Chi) {
            this.fe_Chi = new SimpleDoubleProperty(fe_Chi);
        }

        public double getC() {
            return c.get();
        }

        public SimpleDoubleProperty cProperty() {
            return c;
        }

        public void setC(double c) {
            this.c = new SimpleDoubleProperty(c);
        }

        public double getC_Ac() {
            return c_Ac.get();
        }

        public SimpleDoubleProperty c_AcProperty() {
            return c_Ac;
        }

        public void setC_Ac(double c_Ac) {
            this.c_Ac = new SimpleDoubleProperty(c_Ac);
        }

        public double getMarca_clase() {
            return marca_clase.get();
        }

        public SimpleDoubleProperty marca_claseProperty() {
            return marca_clase;
        }

        public void setMarca_clase(double marca_clase) {
            this.marca_clase = new SimpleDoubleProperty(marca_clase);
        }

        public double getC_mc() {
            return c_mc.get();
        }

        public SimpleDoubleProperty c_mcProperty() {
            return c_mc;
        }

        public void setC_mc(double c_mc) {
            this.c_mc = new SimpleDoubleProperty(c_mc);
        }

        public double getC_Pac() {
            return c_Pac.get();
        }

        public SimpleDoubleProperty c_PacProperty() {
            return c_Pac;
        }

        public void setC_Pac(double c_Pac) {
            this.c_Pac = new SimpleDoubleProperty(c_Pac);
        }

        public double getP() {
            return p.get();
        }

        public SimpleDoubleProperty pProperty() {
            return p;
        }

        public void setP(double p) {
            this.p = new SimpleDoubleProperty(p);
        }

    }

    public void setearTabla() {

        TableColumn<FilaTabla, String> serie = new TableColumn<>("Serie");
        serie.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("serie"));
        tablaSerie.getColumns().add(serie);

        if (checkExponencial.isSelected()) {
            TableColumn<FilaTabla, String> min = new TableColumn<>("Desde");
            TableColumn<FilaTabla, String> max = new TableColumn<>("Hasta");
            TableColumn<FilaTabla, String> marca_clase = new TableColumn<>("Marca Clase");
            TableColumn<FilaTabla, String> fo = new TableColumn<>("Fo");
            TableColumn<FilaTabla, String> c_mc = new TableColumn<>("P() c/mc");
            TableColumn<FilaTabla, String> c_Pac = new TableColumn<>("P() c/Pac");
            TableColumn<FilaTabla, String> fe = new TableColumn<>("Fe");

            min.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("minimo"));
            max.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("maximo"));
            marca_clase.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("marca_clase"));
            fo.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fo"));
            c_mc.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("c_mc"));
            c_Pac.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("c_Pac"));
            fe.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fe"));

            tablaSuperior.getColumns().addAll(min, max, marca_clase, fo, c_mc, c_Pac, fe);
           }

        if (checkNormal.isSelected()) {
            TableColumn<FilaTabla, String> min = new TableColumn<>("Desde");
            TableColumn<FilaTabla, String> max = new TableColumn<>("Hasta");
            TableColumn<FilaTabla, String> marca_clase = new TableColumn<>("Marca Clase");
            TableColumn<FilaTabla, String> fo = new TableColumn<>("Fo");
            TableColumn<FilaTabla, String> p = new TableColumn<>("P()");
            TableColumn<FilaTabla, String> fe = new TableColumn<>("Fe");

            min.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("minimo"));
            max.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("maximo"));
            marca_clase.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("marca_clase"));
            fo.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fo"));
            p.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("p"));
            fe.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fe"));

            tablaSuperior.getColumns().addAll(min, max, marca_clase, fo, p, fe);
        }

        if (checkPoisson.isSelected()) {
            TableColumn<FilaTabla, String> valor = new TableColumn<>("Valor");
            TableColumn<FilaTabla, String> fo = new TableColumn<>("Fo");
            TableColumn<FilaTabla, String> p = new TableColumn<>("P()");
            TableColumn<FilaTabla, String> fe = new TableColumn<>("Fe");

            valor.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("valor"));
            fo.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fo"));
            p.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("p"));
            fe.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fe"));

            tablaSuperior.getColumns().addAll(valor, fo, p, fe);

        }
    }

    public void setearTablaChi() {

        TableColumn<FilaTabla, String> min_Chi = new TableColumn<>("Desde");
        TableColumn<FilaTabla, String> max_Chi = new TableColumn<>("Hasta");
        TableColumn<FilaTabla, String> fo_Chi = new TableColumn<>("Fo");
        TableColumn<FilaTabla, String> fe_Chi = new TableColumn<>("Fe");
        TableColumn<FilaTabla, String> c = new TableColumn<>("C");
        TableColumn<FilaTabla, String> c_Ac = new TableColumn<>("C(Ac)");

        min_Chi.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("minimo_Chi"));
        max_Chi.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("maximo_Chi"));
        fo_Chi.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fo_Chi"));
        fe_Chi.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fe_Chi"));
        c.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("c"));
        c_Ac.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("c_Ac"));

        tablaInferior.getColumns().addAll(min_Chi, max_Chi, fo_Chi, fe_Chi, c, c_Ac);
    }

    public void setearTablaKS() {
        TableColumn<FilaTabla, String> min_KS = new TableColumn<>("Desde");
        TableColumn<FilaTabla, String> max_KS = new TableColumn<>("Hasta");
        TableColumn<FilaTabla, String> fo_KS = new TableColumn<>("Fo");
        TableColumn<FilaTabla, String> fe_KS = new TableColumn<>("Fe");
        TableColumn<FilaTabla, String> po = new TableColumn<>("Po");
        TableColumn<FilaTabla, String> pe = new TableColumn<>("Pe");
        TableColumn<FilaTabla, String> po_Ac = new TableColumn<>("Po (Ac)");
        TableColumn<FilaTabla, String> pe_Ac = new TableColumn<>("Pe (Ac)");
        TableColumn<FilaTabla, String> abs_po_pe = new TableColumn<>("|Po(AC)-Pe(AC)|");
        TableColumn<FilaTabla, String> max = new TableColumn<>("MAX");

        min_KS.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("min_KS"));
        max_KS.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("max_KS"));
        fo_KS.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fo_KS"));
        fe_KS.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("fe_KS"));
        po.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("po"));
        pe.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("pe"));
        po_Ac.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("po_Ac"));
        pe_Ac.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("pe_Ac"));
        abs_po_pe.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("abs_po_pe"));
        max.setCellValueFactory(new PropertyValueFactory<FilaTabla, String>("max"));

        tablaInferior.getColumns().addAll(min_KS, max_KS, fo_KS, fe_KS, po, pe, po_Ac, pe_Ac, abs_po_pe, max);
    }


    public void cargarTabla(ArrayList<Double> numeros, ArrayList<ArrayList<Double>> datos, ArrayList<ArrayList<Double>> datos2) {

        for (int i = 0; i < numeros.size(); i++) {

            FilaTabla filaSerie = new FilaTabla();
            filaSerie.setSerie(numeros.get(i));

            tablaSerie.setItems(datosTablaSerie);
            datosTablaSerie.add(filaSerie);
        }

        if (checkUniforme.isSelected()) {

            for (int i = 0; i < datos.size(); i++) {

                FilaTabla fila = new FilaTabla();

                ArrayList<Double> iteracion = datos.get(i);

                fila.setMinimo_Chi(iteracion.get(0));
                fila.setMaximo(iteracion.get(1));
                fila.setFo_Chi(iteracion.get(2));
                fila.setFe_Chi(iteracion.get(3));
                fila.setC(iteracion.get(4));
                fila.setC_Ac(iteracion.get(5));

                tablaSuperior.setItems(datosTablaSuperior);
                datosTablaSuperior.add(fila);
            }
        }

        if (checkExponencial.isSelected()) {

            for (int i = 0; i < datos.size(); i++) {

                FilaTabla fila = new FilaTabla();

                ArrayList<Double> iteracion = datos.get(i);

                fila.setMinimo(iteracion.get(0));
                fila.setMaximo(iteracion.get(1));
                fila.setMarca_clase(iteracion.get(2));
                fila.setFo(iteracion.get(3));
                fila.setC_mc(iteracion.get(4));
                fila.setC_Pac(iteracion.get(5));
                fila.setFe(iteracion.get(6));

                tablaSuperior.setItems(datosTablaSuperior);
                datosTablaSuperior.add(fila);
            }

            for (int i = 0; i < datos2.size(); i++) {

                FilaTabla fila = new FilaTabla();

                ArrayList<Double> iteracion = datos.get(i);

                fila.setMinimo_Chi(iteracion.get(0));
                fila.setMaximo_Chi(iteracion.get(1));
                fila.setFo_Chi(iteracion.get(2));
                fila.setFe_Chi(iteracion.get(3));
                fila.setC(iteracion.get(4));
                fila.setC_Ac(iteracion.get(5));

                tablaInferior.setItems(datosTablaInferior);
                datosTablaInferior.add(fila);
            }
        }

        if (checkNormal.isSelected()) {

            for (int i = 0; i < datos.size(); i++) {

                FilaTabla fila = new FilaTabla();

                ArrayList<Double> iteracion = datos.get(i);

                fila.setMinimo(iteracion.get(0));
                fila.setMaximo(iteracion.get(1));
                fila.setFo(iteracion.get(2));
                fila.setP(iteracion.get(3));
                fila.setFe(iteracion.get(4));

            }

            for (int i = 0; i < datos2.size(); i++) {

                FilaTabla fila = new FilaTabla();

                ArrayList<Double> iteracion = datos.get(i);

                fila.setMinimo_Chi(iteracion.get(0));
                fila.setMaximo_Chi(iteracion.get(1));
                fila.setFo_Chi(iteracion.get(2));
                fila.setFe_Chi(iteracion.get(3));
                fila.setC(iteracion.get(4));
                fila.setC_Ac(iteracion.get(5));

                tablaInferior.setItems(datosTablaInferior);
                datosTablaInferior.add(fila);
            }
        }

        if (checkPoisson.isSelected()) {

            for (int i = 0; i < datos.size(); i++) {

                FilaTabla fila = new FilaTabla();

                ArrayList<Double> iteracion = datos.get(i);

                fila.setValor(iteracion.get(0));
                fila.setFo(iteracion.get(1));
                fila.setP(iteracion.get(2));
                fila.setFe(iteracion.get(3));
            }

            for (int i = 0; i < datos2.size(); i++) {

                FilaTabla fila = new FilaTabla();

                ArrayList<Double> iteracion = datos.get(i);

                fila.setMinimo_Chi(iteracion.get(0));
                fila.setMaximo_Chi(iteracion.get(1));
                fila.setFo_Chi(iteracion.get(2));
                fila.setFe_Chi(iteracion.get(3));
                fila.setC(iteracion.get(4));
                fila.setC_Ac(iteracion.get(5));

                tablaInferior.setItems(datosTablaInferior);
                datosTablaInferior.add(fila);
            }
        }
    }

}