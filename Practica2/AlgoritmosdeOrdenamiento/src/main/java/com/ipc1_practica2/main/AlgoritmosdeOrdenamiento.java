package com.ipc1_practica2.main;
import com.ipc1_practica2.views.StartFrame;
import javax.swing.SwingUtilities;

/**
 * @author Admin
 */
public class AlgoritmosdeOrdenamiento {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartFrame startFrame = new StartFrame();
            startFrame.setVisible(true);
            
        });
    }
}
