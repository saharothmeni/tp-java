package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.Timer;

import model.EspaceEnseignant;
import model.LoginModel;
import views.LoginView;
import views.TeacherPanelView;

import dao.MatiereDAOimpl;
import dao.EtudiantCoursDAOimpl;

import Control.datacontrol.ResolveNameSystem;
import Control.tools.Generator;
import Control.Exceptions.EmptyFieldException;
import Control.Exceptions.IDInvalidException;
import Control.Exceptions.NoteInvalidException;

public class EspaceEnseignantController {

    private EspaceEnseignant model;
    private TeacherPanelView view;

    public EspaceEnseignantController(EspaceEnseignant model,
                                      TeacherPanelView view) throws SQLException {
        this.model = model;
        this.view  = view;

        // =========================
        // INITIALISATION SESSION
        // =========================
        initializeSession();

        // =========================
        // CHARGEMENT DONNEES
        // =========================
        model.rafraichirDonnees();
        view.setNoteComponentsVisible(false);

        // =========================
        // HORLOGE
        // =========================
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            view.setTime(Generator.updateTime());
        });
        timer.start();

        // =========================
        // SELECTION ETUDIANT
        // =========================
        view.addStudentTableListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JTable table = (JTable) e.getSource();
                    int row = table.getSelectedRow();
                    String value = table.getValueAt(row, 0).toString();
                    view.setNoteComponentsVisible(true);
                    view.setSelectedStudentName(value);
                } catch (Exception ex) {
                    view.displayMessage("Veuillez sélectionner un étudiant");
                }
            }
        });

        // =========================
        // VALIDER NOTE
        // =========================
        view.addValidateNoteListener((ActionEvent e) -> {
            try {
                model.setNoteSaisie(view.getNote());
                model.validerNote();

                String idEtudiant = ResolveNameSystem.getID(
                    view.getSelectedStudentName()
                );

                // ✅ CORRIGÉ — ordre : getIDMat(idEtudiant, idEnseignant)
                int idMat = new MatiereDAOimpl().getIDMat(
                    Integer.parseInt(idEtudiant),
                    model.getEnseignant().getId()
                );

                // ✅ updateNote existe maintenant dans EtudiantCoursDAOimpl
                new EtudiantCoursDAOimpl().updateNote(
                    idMat,
                    Integer.parseInt(idEtudiant),
                    Double.parseDouble(model.getNote())
                );

                model.rafraichirDonnees();
                view.displayMessage("Note mise à jour ✔");

            } catch (NoteInvalidException ex) {
                view.displayMessage("Note invalide (doit être entre 0 et 20)");
            } catch (SQLException ex) {
                view.displayMessage("Erreur base de données");
                ex.printStackTrace();
            } catch (Exception ex) {
                view.displayMessage("Erreur : " + ex.getMessage());
            }
        });

        // =========================
        // ENVOYER MESSAGE
        // =========================
        view.addSendMessageListener((ActionEvent e) -> {
            try {
                model.setDestinataireMsg(view.getReceiver());
                model.setContenuMessage(view.getMessage());
                model.validerMessage();
                model.envoyerMessage();
                view.displayMessage("Message envoyé ✔");
                view.clearMessage();
            } catch (EmptyFieldException ex) {
                view.displayMessage("Champs vides");
            } catch (IDInvalidException ex) {
                view.displayMessage("ID invalide");
            }
        });

        // =========================
        // LOGOUT
        // =========================
        view.addLogoutListener((ActionEvent e) -> {
            view.displayMessage(
                model.getEnseignant().getNom() + " déconnecté"
            );
            view.close();
            new LoginController(new LoginModel(), new LoginView());
        });
    }

    // =========================
    // INITIALISATION SESSION
    // =========================
    private void initializeSession() {
        model.setNoteSaisie("");
        model.setDestinataireMsg("");
        model.setContenuMessage("");

        view.setTeacherDetails(
            String.valueOf(model.getEnseignant().getId()),
            model.getEnseignant().getNom(),
            String.valueOf(model.getEnseignant().getGenre())
        );

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        view.setLoginDate(sdf.format(new Date()));
        view.setUserLabel(model.getEnseignant().getNom());
    }
}