package view.loading_screen;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JPanel;

public class GradientPanel extends JPanel {

    private final Color gradientStart;
    private final Color gradientEnd;

    public GradientPanel(Color gradientStart, Color gradientEnd) {
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
        setOpaque(false); // Permet de voir le dessin personnalisé correctement
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Important pour le bon fonctionnement de Swing
        
        int height = getHeight();
        int width = getWidth();
        Graphics2D g2 = (Graphics2D) g;
        
        // Création d'un dégradé fluide sur toute la hauteur
        GradientPaint painter = new GradientPaint(0, 0, gradientStart, 0, height, gradientEnd);
        
        Paint oldPainter = g2.getPaint();
        g2.setPaint(painter);
        
        // On remplit tout le panneau
        g2.fillRect(0, 0, width, height);

        g2.setPaint(oldPainter);
    }
}