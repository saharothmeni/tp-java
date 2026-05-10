package model ;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JOptionPane;

import entites.Etudiant;
import entites.Mail;

import dao.TacheDAOimpl;
import dao.EnseignantCoursDAOimpl;
import dao.EtudiantCoursDAOimpl;
import dao.MessagerieDAOimpl;

import Control.datacontrol.ControlData;
import Control.inputcontrol.VerifInput;
import Control.Exceptions.EmptyFieldException;
import Control.Exceptions.IDInvalidException;

public class EspaceEtudiant {

    // =========================
    // TABLES
    // =========================
    private JTable tableTodos;
    private JTable tableCoursDispo;
    private JTable tableMesCours;
    private JTable tableMessages;

    // =========================
    // DONNEES
    // =========================
    private String todoId;
    private String todoDescription;
    private String destinataireMsg;
    private String contenuMessage;

    private Etudiant etudiantConnecte;

    public EspaceEtudiant(Etudiant etd) {
        this.etudiantConnecte = etd;
    }

    // =========================
    // RAFRAICHIR DONNEES
    // =========================
    public void rafraichirDonnees() {

        try {
            int id = etudiantConnecte.getID();

            if (tableTodos != null)
                ControlData.serveData(tableTodos,
                        new TacheDAOimpl().getMesTaches(id));

            if (tableCoursDispo != null)
                ControlData.serveData(tableCoursDispo,
                        new EnseignantCoursDAOimpl().getbyNameEnscours());

            if (tableMesCours != null)
                ControlData.serveData(tableMesCours,
                        new EtudiantCoursDAOimpl().getEtudCours(id));

            if (tableMessages != null)
                ControlData.serveData(tableMessages,
                        new MessagerieDAOimpl().getMesMessages(id));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erreur chargement données");
            e.printStackTrace();
        }
    }

    // =========================
    // AJOUT TACHE
    // =========================
    public void ajouterTache()
            throws SQLException, EmptyFieldException {

        VerifInput.verifFIELDS(todoDescription);

        String dateActuelle =
                new SimpleDateFormat("dd/MM").format(new Date());

        new TacheDAOimpl().ajouterTache(
                new entites.Tache(
                        null,
                        etudiantConnecte.getID(),
                        dateActuelle,
                        todoDescription
                )
        );

        rafraichirDonnees();
    }

    // =========================
    // SUPPRIMER TACHE
    // =========================
    public void supprimerTache() throws SQLException {

        if (todoId == null || todoId.isEmpty()) return;

        new TacheDAOimpl().supprimerTache(
                new entites.Tache(Integer.parseInt(todoId))
        );

        rafraichirDonnees();
    }

    // =========================
    // ENVOYER MESSAGE
    // =========================
    public void envoyerMessage()
            throws SQLException, EmptyFieldException, IDInvalidException {

        VerifInput.verifFIELDS(destinataireMsg);
        VerifInput.verifFIELDS(contenuMessage);
        VerifInput.verifID(destinataireMsg);

        String dateActuelle =
                new SimpleDateFormat("dd/MM").format(new Date());

        Mail mail = new Mail(
                String.valueOf(etudiantConnecte.getID()),
                destinataireMsg,
                contenuMessage,
                dateActuelle
        );

        new MessagerieDAOimpl().envoyerMessage(mail);

        JOptionPane.showMessageDialog(null,
                "Message envoyé ✔");
    }

    // =========================
    // GETTERS / SETTERS
    // =========================
    public Etudiant getEtudiant() {
        return etudiantConnecte;
    }

    public void setTableTodos(JTable t) { this.tableTodos = t; }
    public void setTableCoursDispo(JTable t) { this.tableCoursDispo = t; }
    public void setTableMesCours(JTable t) { this.tableMesCours = t; }
    public void setTableMessages(JTable t) { this.tableMessages = t; }

    public void setTodoId(String id) { this.todoId = id; }
    public void setTodoDescription(String desc) { this.todoDescription = desc; }

    public void setDestinataireMsg(String dest) { this.destinataireMsg = dest; }
    public void setContenuMessage(String msg) { this.contenuMessage = msg; }
}