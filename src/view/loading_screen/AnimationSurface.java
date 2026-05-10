package view.loading_screen;



import java.awt.*;
import java.awt.geom.*;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationSurface extends JPanel {
    private int counter = 0;
    private Timer timer = new Timer(20, ae -> repaint());

    public AnimationSurface() {
        setOpaque(false); // Pour voir le dégradé du fond si nécessaire
        start();
    }

    public void start() { timer.start(); }
    public void stop() { timer.stop(); }

    @Override
    protected void paintComponent(Graphics g) {
        counter++;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        super.paintComponent(g2);

        // Logique de dessin des courbes (identique à l'original)
        drawWave(g2, 20.0f, -10.0f, 5.0f); 
    }

    private void drawWave(Graphics2D g2, float y1, float y1_offset, float speed) {
        // ... (votre code de dessin CubicCurve2D ici)
    }
}
