package views;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.frame.StudentPanelFrame;

/**
 * Vue intermédiaire pour l'espace Étudiant.
 * Encapsule StudentPanelFrame pour respecter le pattern MVC.
 *
 * CORRECTIONS :
 *  ✅ sp.__setjTable1__()  → sp.setjTable1()   (underscores supprimés)
 *  ✅ sp.__setjTable2__()  → sp.setjTable2()
 *  ✅ sp.__setjTable3__()  → sp.setjTable4()   (inbox = table4, pas table3)
 *  ✅ setAllTables()       → paramètres renommés + mapping corrigé
 *  ✅ getStudentName()     → getjLabel28() (nom complet, pas getjLabel3 qui est l'ID)
 *  ✅ setStudentName()     → setjLabel28()
 *  ✅ getStudentID()       → getjLabel26() (ID étudiant, pas getjLabel8 qui est durée connexion)
 *  ✅ setStudentID()       → setjLabel26()
 *  ✅ setjTable4() dans la View → sp.setjTable4() correctement (inbox)
 */
public class StudentPanelView {

    private final StudentPanelFrame sp;

    public StudentPanelView() {
        sp = new StudentPanelFrame();
        sp.pack();
        sp.setLocationRelativeTo(null);
        sp.setResizable(false);
        sp.setVisible(true);
    }

    // =========================================================================
    // Affichage
    // =========================================================================

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(sp, message);
    }

    public void close() {
        sp.dispose();
    }

    /** Met à jour l'horloge affichée dans la sidebar. */
    public void setTime(String time) {
        sp.setjLabel37(time);
    }

    // =========================================================================
    // Configuration des tables (Todo, Matières, Inbox)
    // =========================================================================

    /**
     * Configure les 3 tables principales en une seule fois.
     * @param todoTable     table des tâches (Todo)
     * @param subjectsTable table des matières (Subjects)
     * @param inboxTable    table de la boîte de réception (Inbox)
     */
    public void setAllTables(JTable todoTable, JTable subjectsTable, JTable inboxTable) {
        setjTable1(todoTable);      // Todo
        setjTable2(subjectsTable);  // Matières
        setjTable4(inboxTable);     // Inbox  ← FIX : était setjTable4(jt3) appelant setjTable3
    }

    // =========================================================================
    // Listeners
    // =========================================================================

    public void addTableListener1(MouseListener listener) {
        sp.getjTable1().addMouseListener(listener);
    }

    /** Listener du bouton Déconnexion. */
    public void addButtonLogoutListener(ActionListener listener) {
        sp.getjButton1().addActionListener(listener);
    }

    /** Listener du bouton Envoyer (mail). */
    public void addButtonSendMailListener(ActionListener listener) {
        sp.getjButton2().addActionListener(listener);
    }

    /** Listener du bouton Ajouter (todo). */
    public void addButtonAddTodoListener(ActionListener listener) {
        sp.getjButton6().addActionListener(listener);
    }

    /** Listener du bouton Supprimer (todo). */
    public void addButtonRemoveTodoListener(ActionListener listener) {
        sp.getjButton7().addActionListener(listener);
    }

    /** Listener du bouton Calculer moyenne / Refresh. */
    public void addButtonRefreshListener(ActionListener listener) {
        sp.getjButton8().addActionListener(listener);
    }

    // =========================================================================
    // Getters / Setters — Infos étudiant
    // =========================================================================

    /**
     * ✅ FIX : getjLabel28() = Nom complet.
     * L'original utilisait getjLabel3() qui retourne l'ID/matricule, pas le nom.
     */
    public String getStudentName() {
        return sp.getjLabel28();
    }

    /** ✅ FIX : setjLabel28() = Nom complet. */
    public void setStudentName(String name) {
        sp.setjLabel28(name);
    }

    /**
     * ✅ FIX : getjLabel26() = ID étudiant.
     * L'original utilisait getjLabel8() qui retourne la durée de connexion.
     */
    public String getStudentID() {
        return sp.getjLabel26();
    }

    /** ✅ FIX : setjLabel26() = ID étudiant. */
    public void setStudentID(String id) {
        sp.setjLabel26(id);
    }

    /** Durée de connexion affichée dans la sidebar. */
    public String getConnectionTime() {
        return sp.getjLabel8();
    }

    /** Met à jour la durée de connexion affichée. */
    public void setConnectionTime(String duration) {
        sp.setjLabel8(duration);
    }

    /** Genre de l'étudiant. */
    public void setStudentGender(String gender) {
        sp.setjLabel30(gender);
    }

    // =========================================================================
    // Getters / Setters — Tables
    // =========================================================================

    // ── Todo ──────────────────────────────────────────────────────────────────
    public JTable getjTableTodo() {
        return sp.getjTable1();
    }

    /** ✅ FIX : appelle sp.setjTable1() sans underscores. */
    public void setjTable1(JTable table) {
        sp.setjTable1(table);
    }

    // ── Matières ──────────────────────────────────────────────────────────────
    public JTable getjTableSubjects() {
        return sp.getjTable2();
    }

    /** ✅ FIX : appelle sp.setjTable2() sans underscores. */
    public void setjTable2(JTable table) {
        sp.setjTable2(table);
    }

    // ── Cours ─────────────────────────────────────────────────────────────────
    public JTable getjTableCourses() {
        return sp.getjTable3();
    }

    public void setjTable3(JTable table) {
        sp.setjTable3(table);
    }

    // ── Inbox ─────────────────────────────────────────────────────────────────
    public JTable getjTableInbox() {
        return sp.getjTable4();
    }

    /**
     * ✅ FIX : l'original appelait sp.__setjTable3__() pour l'inbox,
     * ce qui était doublement faux (underscores + mauvaise table).
     * Inbox = table4.
     */
    public void setjTable4(JTable table) {
        sp.setjTable4(table);
    }

    // =========================================================================
    // Getters / Setters — Champs de saisie
    // =========================================================================

    /** Texte saisi dans le champ Todo. */
    public String getTodoInput() {
        return sp.getjTextField4();
    }

    /** Destinataire du mail. */
    public String getMailReceiver() {
        return sp.getjTextArea1();   // TextField1 = destinataire
    }

    /** Corps du message mail. */
    public String getMailMessage() {
        return sp.getjTextArea2();
    }

    /** Pré-remplit le corps du message. */
    public void setMailMessage(String text) {
        sp.setjTextArea2(text);
    }
}