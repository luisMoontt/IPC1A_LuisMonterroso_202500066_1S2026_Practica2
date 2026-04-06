package com.ipc1_practica2.controllers;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
/**
 * @author Admin
 */

public class ReportController {
    private static boolean firstTime = true;
    private static int nly = 1;
    private String os;
    private String arch;
    private int nucleos;
    private long ramTotal;
    private long ramDisponible;
    private String modeloCpu;
    private double frecuencia;
    
    public void printProfileSystem(){
        this.os = System.getProperty("os.name");
        this.arch = System.getProperty("os.arch");
        
        this.nucleos = Runtime.getRuntime().availableProcessors();

        Runtime runtime = Runtime.getRuntime();
        this.ramTotal = runtime.totalMemory() / (1024 * 1024);
        this.ramDisponible = runtime.freeMemory() / (1024 * 1024);
        
        SystemInfo sInf = new SystemInfo();
        HardwareAbstractionLayer hal = sInf.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        this.modeloCpu = cpu.getProcessorIdentifier().getName();
        this.frecuencia = cpu.getMaxFreq() / 1_000_000;
        
        System.out.println("Sistema operativo: " + os + " (" + arch + ")");
        System.out.println("Nucleos/Hilos disponibles: " + nucleos);
        System.out.println("RAM total: " + ramTotal + " MB");
        System.out.println("RAM disponible: " + ramDisponible + " MB");
        System.out.println("Procesador: " + modeloCpu);
        System.out.println("Frecuencia: " + frecuencia + " MHz");  
    }
    
    public void generateReport(String serieI, String serieF,String algorithm, String order, int comparisons, int exchanges, int iterations, int speed, long totalTime){
        printProfileSystem();
        String filePath = "reporte.html";
    
        try {
            if (firstTime) {
                java.io.FileWriter fw = new java.io.FileWriter(filePath, false);
                java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);

                bw.write("<!DOCTYPE html>");
                bw.newLine();
                bw.write("<html><head><meta charset='UTF-8'>");
                bw.newLine();
                bw.write("<title>Reporte de Algoritmos</title>");
                bw.newLine();
                bw.write("<style>");
                bw.write("body { font-family: 'Courier New', monospace; padding: 20px; }");
                bw.write("table { width: 100%; font-family: 'Courier New', monospace; border-collapse: collapse; margin-bottom: 30px;}");
                bw.write("th { background: #D3D3D3; padding: 10px; text-align: left; }");
                bw.write("td { padding: 8px;  border-bottom: 1px solid #333; }");
                bw.write("</style></head><body>");
                bw.newLine();
                bw.write("<h1 style='font-weight:bold, text-align:center;' >REPORTE DE ORDENAMIENTOS</h1>");
                bw.newLine();

                bw.close();
                firstTime = false;
            }

            java.io.FileWriter fw = new java.io.FileWriter(filePath, true);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);

            
            bw.newLine();
            bw.write("<h2> NUMERO DE EJECUCIÓN: #"+nly+"</h2>");
            bw.write("<table>");
            bw.write("<tr><th>Resumen de Ejecución</th><th>  </th>");
            bw.write("<tr><td style='font-weight:bold' >Algoritmo y Orden</td><td>" + algorithm +" ("+order+") </td></tr>");
            bw.write("<tr><td style='font-weight:bold; background:#F5F5F5;' >ARREGLO Inicial</td><td  style='background:#F5F5F5;'>" + serieI + "</td></tr>");
            bw.write("<tr><td style='font-weight:bold; background:#F5F5F5;' >ARREGLO Final</td><td style='background:#F5F5F5;'>" + serieF + "</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Comparaciones</td><td>" + comparisons + "</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Intercambios</td><td>" + exchanges + "</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Iteraciones</td><td>" + iterations + "</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Velocidad de ejeción seleccionada</td><td>" + speed + " ms. </td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Tiempo total de ejeción</td><td>" + totalTime + " ms. </td></tr>");
            bw.write("<tr><th>Perfil del Sistema</th><th>  </th>");
            bw.write("<tr><td style='font-weight:bold' >Sistema Operativo</td><td>" + os + " (" + arch + ")</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Nucleos/Hilos</td><td>" + nucleos + "</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >RAM Total</td><td>" + ramTotal + " MB</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >RAM Disponible</td><td>" + ramDisponible + " MB</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Procesador</td><td>" + modeloCpu + "</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Frecuencia</td><td>" + frecuencia + " MHz</td></tr>");
            bw.write("<tr><td style='font-weight:bold' >Elementos procesados</td><td>" + (comparisons+exchanges+iterations) + "</td></tr>");
            bw.write("</table>");
            bw.newLine();

            bw.close();
            System.out.println("Reporte generado: " + filePath);
        
            nly++;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
