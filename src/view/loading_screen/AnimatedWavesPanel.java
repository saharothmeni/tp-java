package view.loading_screen ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Ce panneau gère l'arrière-plan animé avec un dégradé et des vagues.
 * Il hérite de JPanel pour une compatibilité maximale avec WindowBuilder.
 */
public class AnimatedWavesPanel extends JPanel {
    
    private Timer animationTimer;
    private int frameCounter = 0;
    protected RenderingHints hints;

    public AnimatedWavesPanel() {
        // Initialisation du Timer (50 FPS pour une animation fluide)
        animationTimer = new Timer(20, ae -> repaint());
        
        // Configuration du rendu (Anti-aliasing pour des courbes lisses)
        hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        frameCounter++;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(hints);
        
        // Dessin du dégradé de fond manuel (remplace GradientPanel si absent)
        int w = getWidth();
        int h = getHeight();
        
        // Simuler le dégradé du GradientPanel original
        java.awt.GradientPaint gp = new java.awt.GradientPaint(0, 0, Color.decode("#434343"), 0, h, Color.decode("#000000"));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        // Dessin de la vague animée
        g2d.translate(0, -30);
        // Utilisation de frameCounter pour créer le mouvement
        drawAnimatedWave(g2d, h / 2f, 20.0f, w);
        g2d.translate(0, 30);
    }
    
    /**
     * Dessine une courbe de Bézier qui évolue avec le temps
     */
    private void drawAnimatedWave(Graphics2D g2, float baseY, float amplitude, float width) {
        g2.setColor(new Color(255, 255, 255, 50)); // Blanc transparent
        
        // Calcul du mouvement sinusoïdal basé sur frameCounter
        float offset = (float) Math.sin(frameCounter * 0.05) * amplitude;
        
        CubicCurve2D curve = new CubicCurve2D.Float(
            0, baseY + offset, 
            width / 4, baseY - offset * 2, 
            3 * width / 4, baseY + offset * 2, 
            width, baseY - offset
        );
        g2.draw(curve);
    }

    public void stop() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }
}