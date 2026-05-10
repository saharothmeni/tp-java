package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JOptionPane;

import Control.datacontrol.ControlData;
import Control.inputcontrol.VerifInput;
import Control.Exceptions.EmptyFieldException;
import Control.Exceptions.IDInvalidException;
import Control.Exceptions.NoteInvalidException;

import dao.EnseignantCoursDAOimpl;
import dao.MessagerieDAOimpl;

import entites.Enseignant;

/**
 * Modèle espace enseignant
 *
 * Tables utilisées :
 *   enseignant             : id | nom | genre | specialite | grade
 *   affectationenseignant  : id_enseignant | id_matiere | annee_universitaire
 *   inscriptionmodule      : id_etudiant   | id_matiere | note_controle | note_examen
 *   etudiant               : id | name | gender
 */
public class EspaceEnseignant {

    private JTable tableMatieres;
    private JTable tableEtudiants;
    private JTable tableMessages;

    private String destinataireMsg;
    private String contenuMessage;
    private String noteSaisie;

    private Enseignant enseignantConnecte;

    public EspaceEnseignant(Enseignant ens) {
        this.enseignantConnecte = ens;
    }

    // ─────────────────────────────────────────────────────────────
    // RAFRAICHIR DONNÉES
    // ─────────────────────────────────────────────────────────────
    public void rafraichirDonnees() {

        if (enseignantConnecte == null) return;

        int idEns = enseignantConnecte.getId();

        try {
            // Matières affectées à cet enseignant
            // → table affectationenseignant JOIN matiere
            if (tableMatieres != null)
                ControlData.serveData(tableMatieres,
                    new EnseignantCoursDAOimpl().getMoncours(idEns));

            // Étudiants partageant au moins une matière avec cet enseignant
            // → affectationenseignant JOIN inscriptionmodule JOIN etudiant
            if (tableEtudiants != null)
                ControlData.serveData(tableEtudiants,
                    new EnseignantCoursDAOimpl().getEtudiantsMemeCours(idEns));

            // Messages reçus par cet enseignant
            if (tableMessages != null)
                ControlData.secureServeData(tableMessages,
                    new MessagerieDAOimpl().getMesMessages(idEns), idEns);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Erreur chargement données : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // VALIDATION MESSAGE
    // ─────────────────────────────────────────────────────────────
    public void validerMessage() throws EmptyFieldException, IDInvalidException {
        VerifInput.verifFIELDS(destinataireMsg);
        VerifInput.verifFIELDS(contenuMessage);
        VerifInput.verifID(destinataireMsg);
    }

    // ─────────────────────────────────────────────────────────────
    // VALIDATION NOTE
    // ─────────────────────────────────────────────────────────────
    public void validerNote() throws NoteInvalidException {
        try {
            double note = Double.parseDouble(noteSaisie);
            VerifInput.verifNote(note);
        } catch (NumberFormatException e) {
            throw new NoteInvalidException("Note invalide : doit être un nombre !");
        }
    }

    // ─────────────────────────────────────────────────────────────
    // ENVOYER MESSAGE
    // ─────────────────────────────────────────────────────────────
    public void envoyerMessage() {

        try {
            validerMessage();

            String dateActuelle =
                new SimpleDateFormat("dd/MM").format(new Date());

            ControlData.sendMessage(
                destinataireMsg,
                String.valueOf(enseignantConnecte.getId()),
                contenuMessage,
                dateActuelle
            );

            JOptionPane.showMessageDialog(null, "Message envoyé ✔");

        } catch (EmptyFieldException | IDInvalidException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur base de données !");
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // GETTERS / SETTERS
    // ─────────────────────────────────────────────────────────────
    public Enseignant getEnseignant()        { return enseignantConnecte; }
    public String     getNote()              { return this.noteSaisie; }

    public void setTableMatieres(JTable t)   { this.tableMatieres  = t; }
    public void setTableEtudiants(JTable t)  { this.tableEtudiants = t; }
    public void setTableMessages(JTable t)   { this.tableMessages  = t; }

    public void setNoteSaisie(String note)      { this.noteSaisie      = note; }
    public void setDestinataireMsg(String dest) { this.destinataireMsg = dest; }
    public void setContenuMessage(String msg)   { this.contenuMessage  = msg;  }
}