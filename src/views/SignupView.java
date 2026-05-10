package views;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.frame.SignupFrame;

public class SignupView {
    private SignupFrame sf;

    public SignupView() {
        sf = new SignupFrame();
        sf.setVisible(true);
        sf.pack();
        sf.setResizable(false);
        sf.setLocationRelativeTo(null);
    }

    // --- Gestion de la fenêtre ---
    public void Close() {
        sf.dispose();
    }

    // --- Récupération des données ---
    public String getID()     { return sf.getID(); }        // ✅ sf.getID() existe
    public String getName()   { return sf.getName(); }      // ✅ sf.getName() existe
    public String genGender() { return sf.getGender(); }    // ✅ sf.getGender() existe
    public char[] getPass()   { return sf.getPassword(); }  // ✅ sf.getPassword() existe
    public char[] getrPass()  { return sf.getRPassword(); } // ✅ sf.getRPassword() existe

    // ✅ CORRIGÉ : sf.getCheck() existe dans SignupFrame
    public boolean getCheck() { return sf.getCheck(); }

    // ✅ CORRIGÉ : sf.getCapt() existe dans SignupFrame
    public String GetCapt()   { return sf.getCapt(); }

    // ✅ CORRIGÉ : sf.getOrCapt() existe dans SignupFrame
    public String GetOrCapt() { return sf.getOrCapt(); }

    // --- Listeners ---
    public void addButtonListener(ActionListener e) {
        sf.getButton().addActionListener(e);   // ✅ sf.getButton() existe
    }

    public void addButtonListener2(ActionListener e) {
        sf.getButton2().addActionListener(e);  // ✅ sf.getButton2() existe
    }

    // --- Affichage ---
    public void displayMessage(String m) {
        JOptionPane.showMessageDialog(sf, m);
    }
}