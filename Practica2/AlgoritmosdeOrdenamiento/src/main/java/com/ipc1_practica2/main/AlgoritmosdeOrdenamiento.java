package com.ipc1_practica2.main;
import com.ipc1_practica2.controllers.ReportController;
import com.ipc1_practica2.views.StartFrame;
import javax.swing.SwingUtilities;

/**
 * @author Admin
 */
public class AlgoritmosdeOrdenamiento {
    public static void main(String[] args) {
        ReportController c_report = new ReportController();
        c_report.printProfileSystem();
        //c_report.generateReport();
        
        SwingUtilities.invokeLater(() -> {
            StartFrame startFrame = new StartFrame();
            startFrame.setVisible(true);
            
        });
    }
}
