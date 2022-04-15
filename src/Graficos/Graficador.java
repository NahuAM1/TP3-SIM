package Graficos;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;


public class Graficador extends ApplicationFrame {

    public Graficador(ArrayList<Double> vector, int k, double min, double max, String distribucion){
        super("Grafico TP3" );
        JPanel chartPanel = crearPanel(vector, k, min, max, distribucion);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        setContentPane(chartPanel);
    }

    public static JPanel crearPanel(ArrayList<Double> vector, int k, double min, double max, String distribucion) {
        JFreeChart chart = crearChart(crearDataset(vector, k, min, max), distribucion);
        return new ChartPanel(chart);
    }

    private static JFreeChart crearChart(IntervalXYDataset dataset, String distribucion) {
        JFreeChart chart = ChartFactory.createHistogram(
                String.format("Histograma de la Distribucion %s", distribucion ),
                "Intervalos de Frecuencia",
                "Frecuencia Observada",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);

        try {
            ChartUtilities.saveChartAsJPEG(new File(""), chart, 500, 475);
        } catch (IOException e) {
            System.out.println("");
        }
        return chart;
    }

    private static IntervalXYDataset crearDataset(ArrayList<Double> vec, int k, double min, double max) {
        HistogramDataset dataset = new HistogramDataset();
        double[] vector = transformarArray(vec);
        dataset.addSeries("Valores Observados en el Intervalo", vector, k, min, max);
        return dataset;
    }

    private static double[] transformarArray(ArrayList<Double> vec){
        double[] vector = new double[vec.size()];
        for (int i = 0; i < vec.size(); i++) {
                vector[i] = vec.get(i);
        }
        return vector;
    }


}