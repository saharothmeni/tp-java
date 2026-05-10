package view.loading_screen;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import Control.datacontrol.Singleton_Dbconnect;

public class SplashScreen extends JFrame {

    private JPanel contentPane;
    private AnimationSurface animationSurface;
    private JLabel lbStatus;

    public SplashScreen(Object object, boolean b) {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 931, 510);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(221, 221, 221));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Initialisation de la surface d'animation
        animationSurface = new AnimationSurface();
        animationSurface.setBounds(0, 0, 931, 510);
        contentPane.add(animationSurface);
        animationSurface.setLayout(null);

        // Label Status
        lbStatus = new JLabel("Initialisation...");
    }

}