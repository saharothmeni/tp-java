package views;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.frame.TeacherPanelFrame;

public class TeacherPanelView {

    private final TeacherPanelFrame tp;

    // ════════════════════════════════════════════════════════════════════════
    // CONSTRUCTEUR
    // ════════════════════════════════════════════════════════════════════════
    public TeacherPanelView() {
        tp = new TeacherPanelFrame();
        tp.setResizable(false);
        tp.setLocationRelativeTo(null);
        tp.setVisible(true);
    }

    // ════════════════════════════════════════════════════════════════════════
    // VISIBILITÉ COMPOSANTS NOTE
    // ════════════════════════════════════════════════════════════════════════
    public void setNoteComponentsVisible(boolean b) {
        tp.getButton().setVisible(b);
        tp.getLabelAff().setVisible(b);
        tp.getFieldNote().setVisible(b);
        tp.getLabelNom().setVisible(b);
    }

    // ════════════════════════════════════════════════════════════════════════
    // MESSAGES UI
    // ════════════════════════════════════════════════════════════════════════
    public void displayMessage(String m) {
        JOptionPane.showMessageDialog(tp, m);
    }

    public void close() {
        tp.dispose();
    }

    // ════════════════════════════════════════════════════════════════════════
    // HORLOGE
    // CORRECTION : tp.setLabelTime() → maintenant défini dans Frame
    // ════════════════════════════════════════════════════════════════════════
    public void setTime(String time) {
        tp.setLabelTime(time);
    }

    // ════════════════════════════════════════════════════════════════════════
    // INFOS ENSEIGNANT
    // ════════════════════════════════════════════════════════════════════════
    public void setTeacherDetails(String id, String name, String gender) {
        tp.setjLabel26(id);
        tp.setjLabel28(name);
        tp.setjLabel30(gender);
    }

    // CORRECTION : tp.setjLabel8() → maintenant défini dans Frame
    public void setLoginDate(String date) {
        tp.setjLabel8(date);
    }

    // CORRECTION : setjLabel3 corrigé dans Frame (affiche bien "Utilisateur : xxx")
    public void setUserLabel(String user) {
        tp.setjLabel3(user);
    }

    // ════════════════════════════════════════════════════════════════════════
    // TABLES — Getters
    // ════════════════════════════════════════════════════════════════════════
    public JTable getStudentsTable() { return tp.getjTable3(); }
    public JTable getSubjectsTable() { return tp.getjTable2(); }
    public JTable getMessagesTable() { return tp.getjTable4(); }

    // ════════════════════════════════════════════════════════════════════════
    // TABLES — Setters
    // ════════════════════════════════════════════════════════════════════════
    public void setStudentsTable(JTable t) { tp.setjTable3(t); }
    public void setSubjectsTable(JTable t) { tp.setjTable2(t); }
    public void setMessagesTable(JTable t) { tp.setjTable4(t); }

    // ════════════════════════════════════════════════════════════════════════
    // NOTE
    // ════════════════════════════════════════════════════════════════════════
    public String getNote() {
        return tp.getNote();
    }

    // CORRECTION : tp.setNameNt() → maintenant défini dans Frame (alias de setName)
    public void setSelectedStudentName(String n) {
        tp.setNameNt(n);
    }

    // CORRECTION : tp.getLabelNomv() → maintenant défini dans Frame
    public String getSelectedStudentName() {
        return tp.getLabelNomv();
    }

    // ════════════════════════════════════════════════════════════════════════
    // MESSAGERIE
    // ════════════════════════════════════════════════════════════════════════
    public String getReceiver() {
        return tp.getjTextArea1();
    }

    public String getMessage() {
        return tp.getjTextArea2();
    }

    public void clearMessage() {
        tp.setjTextArea2("");
    }

    // ════════════════════════════════════════════════════════════════════════
    // LISTENERS — Boutons principaux
    // ════════════════════════════════════════════════════════════════════════
    public void addStudentTableListener(MouseListener e) {
        tp.getjTable3().addMouseListener(e);
    }

    public void addValidateNoteListener(ActionListener e) {
        tp.getButton().addActionListener(e);
    }

    public void addLogoutListener(ActionListener e) {
        tp.getjButton1().addActionListener(e);
    }

    public void addSendMessageListener(ActionListener e) {
        tp.getjButton2().addActionListener(e);
    }

    // ════════════════════════════════════════════════════════════════════════
    // LISTENERS — CRUD Étudiants
    // ════════════════════════════════════════════════════════════════════════
    public void addAddStudentListener(ActionListener e) {
        tp.getBtnAddStudent().addActionListener(e);
    }

    public void addEditStudentListener(ActionListener e) {
        tp.getBtnEditStudent().addActionListener(e);
    }

    public void addDeleteStudentListener(ActionListener e) {
        tp.getBtnDeleteStudent().addActionListener(e);
    }

    public void addRefreshStudentListener(ActionListener e) {
        tp.getBtnRefreshStudent().addActionListener(e);
    }

    // ════════════════════════════════════════════════════════════════════════
    // LISTENERS — CRUD Matières
    // ════════════════════════════════════════════════════════════════════════
    public void addAddSubjectListener(ActionListener e) {
        tp.getBtnAddSubject().addActionListener(e);
    }

    public void addEditSubjectListener(ActionListener e) {
        tp.getBtnEditSubject().addActionListener(e);
    }

    public void addDeleteSubjectListener(ActionListener e) {
        tp.getBtnDeleteSubject().addActionListener(e);
    }

    public void addRefreshSubjectListener(ActionListener e) {
        tp.getBtnRefreshSubject().addActionListener(e);
    }

    // ════════════════════════════════════════════════════════════════════════
    // ACCÈS DIRECT À LA FRAME
    // ════════════════════════════════════════════════════════════════════════
    public TeacherPanelFrame getFrame() { return tp; }
}