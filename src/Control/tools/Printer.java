package Control.tools; // Package mis à jour pour correspondre à src/Control/tools

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import javax.swing.JFrame;

/**
 * Classe utilitaire pour l'impression de composants graphiques.
 * Utile pour imprimer le relevé de notes d'un étudiant.
 * @author SGI
 */
public class Printer {

    /**
     * Méthode permettant d'imprimer le contenu d'une JFrame donnée.
     * @param f La fenêtre (JFrame) à imprimer.
     */
    public static void printFrame(JFrame f) {
        PrinterJob job = PrinterJob.getPrinterJob();
        
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat pf, int pageIndex) {
                // On ne gère qu'une seule page pour le relevé
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) g;
                
                // Déplacement vers la zone imprimable de la page
                g2.translate(pf.getImageableX(), pf.getImageableY());
                
                // Impression du contenu de la fenêtre
                f.getContentPane().printAll(g2);

                return Printable.PAGE_EXISTS;
            }
        });

        // Affichage de la boîte de dialogue d'impression standard
        if (job.printDialog()) {
            try {
                job.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}