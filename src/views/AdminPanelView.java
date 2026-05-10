package views;

import java.awt.event.ActionListener;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import view.frame.AdminPanelFrame;

public class AdminPanelView {

    private AdminPanelFrame ap;

    public AdminPanelView() {
        ap = new AdminPanelFrame();
        ap.pack();
        ap.setResizable(false);
        ap.setLocationRelativeTo(null);
        ap.setVisible(true);
    }

    // ═══════════════════════════════════════════════════
    // GÉNÉRAL
    // ═══════════════════════════════════════════════════
    public void displayMessage(String m) {
        JOptionPane.showMessageDialog(ap, m);
    }

    public void close() {
        ap.dispose();
    }

    public void buttonRefrech(ActionListener e) {
        ap.getjButton13().addActionListener(e);
    }

    public void addButtonListener1(ActionListener e) {
        ap.getjButton1().addActionListener(e);
    }

    // ═══════════════════════════════════════════════════
    // TABLES — Getters
    //
    //  jTable1 → onglet Étudiants          (liste étudiants)
    //  jTable2 → onglet Enseignants         (liste enseignants)
    //  jTable7 → onglet Matières            (liste matières)
    //  jTable4 → onglet Cours Enseignants   (affectations ens-matière)  ← corrigé
    //  jTable6 → onglet Cours Étudiants     (inscriptions étud-matière)
    // ═══════════════════════════════════════════════════
    public JTable getjTable1() { return ap.getjTable1(); }
    public JTable getjTable2() { return ap.getjTable2(); }
    public JTable getjTable7() { return ap.getjTable7(); }
    public JTable getjTable5() { return ap.getjTable4(); } // ✅ affectations ens-matière = jTable4 dans la Frame
    public JTable getjTable6() { return ap.getjTable6(); }

    // ═══════════════════════════════════════════════════
    // TABLES — Setters
    // ═══════════════════════════════════════════════════
    public void setjTable1(JTable t) { ap.setjTable1(t); }
    public void setjTable2(JTable t) { ap.setjTable2(t); }
    public void setjTable7(JTable t) { ap.setjTable7(t); }
    public void setjTable5(JTable t) { ap.setjTable4(t); } // ✅ corrigé
    public void setjTable6(JTable t) { ap.setjTable6(t); }

    // ═══════════════════════════════════════════════════
    // CHAMPS TEXTE — Étudiants
    //   jTextField1 = ID, jTextField2 = Nom, jTextField3 = Genre
    // ═══════════════════════════════════════════════════
    public String getjTextField1() { return ap.getjTextField1(); }
    public String getjTextField2() { return ap.getjTextField2(); }
    public String getjTextField3() { return ap.getjTextField3(); }

    // ═══════════════════════════════════════════════════
    // CHAMPS TEXTE — Enseignants (onglet Teachers)
    //   jTextField5 = ID
    //   jTextField6 = Nom
    //   jTextField4 = Genre
    //   jTextField16 = Spécialité  ✅ NOUVEAU
    //   jTextField17 = Grade       ✅ NOUVEAU
    // ═══════════════════════════════════════════════════
    public String getEnseignantId()         { return ap.getjTextField5();  }
    public String getEnseignantNom()        { return ap.getjTextField6();  }
    public String getEnseignantGenre()      { return ap.getjTextField4();  }
    public String getEnseignantSpecialite() { return ap.getjTextField16(); } 
    public String getEnseignantGrade()      { return ap.getjTextField17(); } 

    // ═══════════════════════════════════════════════════
    // CHAMPS TEXTE — Cours Enseignants
    //   jTextField7 = ID Enseignant, jTextField8 = ID Matière
    // ═══════════════════════════════════════════════════
    public String getjTextField7() { return ap.getjTextField7(); }
    public String getjTextField8() { return ap.getjTextField8(); }

    // ═══════════════════════════════════════════════════
    // CHAMPS TEXTE — Cours Étudiants
    //   jTextField9 = ID Étudiant, jTextField10 = ID Matière
    // ═══════════════════════════════════════════════════
    public String getjTextField9()  { return ap.getjTextField9();  }
    public String getjTextField10() { return ap.getjTextField10(); }

    // ═══════════════════════════════════════════════════
    // CHAMPS TEXTE — Matières
    //   tf11=ID, tf12=Nom, tf15=Description, tf14=Volume, tf13=Coeff
    // ═══════════════════════════════════════════════════
    public String getID_mat()    { return ap.getjTextField11(); }
    public String getNom_mat()   { return ap.getjTextField12(); }
    public String getDesc_mat()  { return ap.getjTextField15(); }
    public String getVol_mat()   { return ap.getjTextField14(); }
    public String getCoeff_mat() { return ap.getjTextField13(); }

    // ═══════════════════════════════════════════════════
    // BOUTONS — CRUD Étudiants
    // ═══════════════════════════════════════════════════
    public void buttonAjoutEtduiant(ActionListener e)        { ap.getjButton9().addActionListener(e);  }
    public void buttonUpdateEtduiant(ActionListener e)       { ap.getjButton10().addActionListener(e); }
    public void buttonSuppEtduiant(ActionListener e)         { ap.getjButton11().addActionListener(e); }
    public void buttonFermetureEtudiant(ActionListener e)    { ap.getjButton12().addActionListener(e); }
    public void buttonAjoutMatiereEtudiant(ActionListener e) { ap.getjButton7().addActionListener(e);  }
    public void buttonSuppMatiereEtudiant(ActionListener e)  { ap.getjButton8().addActionListener(e);  }

    // ═══════════════════════════════════════════════════
    // BOUTONS — CRUD Enseignants
    // ═══════════════════════════════════════════════════
    public void buttonAjoutEnseignant(ActionListener e)         { ap.getjButton20().addActionListener(e); }
    public void buttonUpdateEnseignant(ActionListener e)        { ap.getjButton19().addActionListener(e); }
    public void buttonSuppEnseignant(ActionListener e)          { ap.getjButton18().addActionListener(e); }
    public void buttonFermetureEnseignant(ActionListener e)     { ap.getjButton17().addActionListener(e); }
    public void buttonAjoutMatiereEnseignant(ActionListener e)  { ap.getjButton2().addActionListener(e);  }
    public void buttonSuppMatiereEnseignant(ActionListener e)   { ap.getjButton4().addActionListener(e);  }

    // ═══════════════════════════════════════════════════
    // BOUTONS — CRUD Matières
    // ═══════════════════════════════════════════════════
    public void buttonAddMatiere(ActionListener e)    { ap.getjButtonAdd().addActionListener(e);    }
    public void buttonUpdateMatiere(ActionListener e) { ap.getjButtonUpdate().addActionListener(e); }
    public void buttonRemoveMatiere(ActionListener e) { ap.getjButtonRemove().addActionListener(e); }
    public void buttonCloseMatiere(ActionListener e)  { ap.getjButtonClose().addActionListener(e);  }
}