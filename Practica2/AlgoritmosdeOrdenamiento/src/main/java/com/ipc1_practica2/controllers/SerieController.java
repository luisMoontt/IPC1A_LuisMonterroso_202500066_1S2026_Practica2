package com.ipc1_practica2.controllers;

import com.ipc1_practica2.models.Serie;
import com.ipc1_practica2.views.MainModule;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * @author Admin
 */
public class SerieController {
    private Serie serie;
    private MainModule v_mainModule;
    
    public SerieController(MainModule v_mainModule){
        this.serie = new Serie();
        this.v_mainModule = v_mainModule;
    }
    
    public void loadSerie(int[] numbers, int t_algorithm, boolean t_order, int speed) {
        serie.setNumbers(numbers.clone());
        serie.setT_algorithm(t_algorithm);
        serie.setT_order(t_order);
        serie.setSpeed(speed);
        serie.setN_comparison(0);
        serie.setN_exchange(0);
        serie.setN_iteration(0);
    }
    
    public void startAlgoritm() throws InterruptedException {
        long start = System.currentTimeMillis();
        switch (serie.getT_algorithm()){
            case 0 :
                bubbleSort();
                long end = System.currentTimeMillis();
                v_mainModule.loadTextArea(" ");
                v_mainModule.loadTextArea(" | Tiempo total de ejecucion: "+(end-start)+" ms. | ");
                break;
            case 1 :
                
                break;
            case 2 :
                
                break;
        }
    }
    
    public int[] readNumbers(String textSerie){
        if (!textSerie.trim().matches("\\d+(,\\s*\\d+)*")) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Formato incorrecto. Use: 1,2,3 o 1, 2, 3");
            return new int[0];
        }
        
        String[] validNums = textSerie.trim().split(",");
        int[] numbers = new int[validNums.length];

        for (int i = 0; i < validNums.length; i++) {
            numbers[i] = Integer.parseInt(validNums[i].trim());
        }

        return numbers;
    }
    
    public String extractText(File textFile){
        StringBuilder content = new StringBuilder();
        
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(textFile));
            String linea;
            while ((linea = reader.readLine()) != null) {
                content.append(linea);
            }
            reader.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        String text = content.toString().trim();

        if (text.matches("\\d+(,\\s*\\d+)*")) {
            return text;
        } else {
            JOptionPane.showMessageDialog(null, "El archivo no tiene el formato correcto");
            return null;
        }
    }
    public String generateNumbers(int n){
        java.util.Random random = new java.util.Random();
        boolean[] used = new boolean[101];
        
        StringBuilder text = new StringBuilder();
        int count = 0;

        while (count < n) {
            int num = random.nextInt(101);

            if (!used[num]) {
                used[num] = true;
                text.append(num);
                if (count < n - 1) {
                    text.append(",");
                }
                count++;
            }
        }
        return text.toString();
    }
    // ALGORTMOS
    public void bubbleSort() throws InterruptedException{
        int[] numbers = serie.getNumbers();
        int n = numbers.length;
        if(serie.isT_order()){
            v_mainModule.loadTextArea(" INICIANDO BUBBLE SORT (ASCENDENTE)");
        } else {
            v_mainModule.loadTextArea(" INICIANDO BUBBLE SORT (DESCENDENTE)");
        }
        
        for (int i = 0; i < (n-1); i++) {
            v_mainModule.loadTextArea(" -- Iteracion "+(i+1)+" --");
            serie.setN_iteration(serie.getN_iteration() + 1);

            for (int j = 0; j < (n-i-1); j++) {
                serie.setN_comparison(serie.getN_comparison() + 1);
                boolean exchange = serie.isT_order() ? numbers[j]>numbers[j + 1] : numbers[j]<numbers[j + 1]; 
                
                updateGraph(serie.getNumbers().clone(), j, j+1,1);
                
                Thread.sleep(serie.getSpeed()/2);
                
                if (exchange) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    
                    serie.setN_exchange(serie.getN_exchange() + 1);
                    v_mainModule.loadTextArea(" Intercambiando "+numbers[j]+" y "+numbers[j+1]+". ");
                    
                    updateGraph(serie.getNumbers().clone(), j, j+1,2);
                    Thread.sleep(serie.getSpeed());
                }
            }
        }
        updateGraph(serie.getNumbers().clone(), 0, 0,3);
    }
    
    public void updateGraph(int[] numbers, int n1, int n2, int action){
        javax.swing.SwingUtilities.invokeLater(() -> {
            v_mainModule.loadGraph(numbers, n1, n2, action);
        });
    }
    
    public Serie getSerie() { return serie; }
    
    public int[] getNumbers()     { return serie.getNumbers(); }
    public int getComparaciones() { return serie.getN_comparison(); }
    public int getIntercambios()  { return serie.getN_exchange(); }
    public int getIteraciones()   { return serie.getN_iteration(); }
    
    public static ChartPanel calculateGraph(int[] numbers, int n1, int n2, int action){
        Color blue_normal  = new Color(0, 191, 255);
        Color green_ready = new Color(26,188,140);
        Color yellow_comparing = new Color(255,183,3);
        Color red_exchange = new Color(255,90,109);
        
        int[] data = new int[10];
        
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < numbers.length; i++) {
            dataset.addValue(numbers[i], "valor", String.valueOf(i+1));
        }
        
        JFreeChart chart = ChartFactory.createBarChart(
            "",                        // título
            "",                        // eje X label
            "",                        // eje Y label
            dataset,
            PlotOrientation.VERTICAL,
            false,                     // leyenda
            false,                     // tooltips
            false                      // URLs
        );
        
        chart.setBackgroundPaint(null);
        chart.setBorderVisible(false);
        chart.setBackgroundImageAlpha(0.0f);
        
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new java.awt.Color(0, 0, 0, 0));
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);
        org.jfree.chart.axis.NumberAxis rangeAxis = (org.jfree.chart.axis.NumberAxis) plot.getRangeAxis();  
        rangeAxis.setUpperMargin(0.15);
        
        plot.getDomainAxis().setTickLabelPaint(Color.WHITE);
        plot.getDomainAxis().setAxisLinePaint(Color.WHITE);
        plot.getDomainAxis().setTickMarkPaint(Color.WHITE);
        plot.getRangeAxis().setVisible(false);
        plot.setInsets(new org.jfree.chart.ui.RectangleInsets(0, 10, 10, 10));
        
        BarRenderer renderer = new BarRenderer() {
            @Override
            public java.awt.Paint getItemPaint(int row, int column) {
                if (action == 1) {
                    if (column == n1 || column == n2) {
                        return yellow_comparing;
                    }
                } else if (action == 2) {
                    if (column == n1 || column == n2) {
                        return red_exchange;
                    }
                } else if (action == 3) {
                    return green_ready;
                } else if (action == 0) {
                    return blue_normal;
                }
                return blue_normal;
            }
        };
        renderer.setDrawBarOutline(false);
        renderer.setShadowVisible(false);
        renderer.setMaximumBarWidth(0.08);
        renderer.setDefaultItemLabelGenerator(
            new org.jfree.chart.labels.StandardCategoryItemLabelGenerator()
        );
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelPaint(Color.LIGHT_GRAY);
        
        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(chart);
        
        panel.setMouseWheelEnabled(false);
        panel.setPopupMenu(null);
        panel.setOpaque(false);
        panel.setBackground(new java.awt.Color(0, 0, 0, 0));
        panel.setBorder(null);
        
        return panel;  
    }  
}
