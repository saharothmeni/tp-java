package main;

import view.loading_screen.SplashScreen;
import model.LoginModel;
import views.LoginView;
import Controllers.LoginController;

/**
 *
 * @author Sahar_Othmeni
 */
public class Projet {
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Projet.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        ////////////////////////////////////////////////////////////////
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SplashScreen sp = new SplashScreen(null, true);
                sp.setLocationRelativeTo(null);
                sp.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                sp.setVisible(true);

                LoginModel loginmodel = new LoginModel();
                LoginView loginview = new LoginView();
                new LoginController(loginmodel, loginview);
            }
        });
    }
}