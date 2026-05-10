package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.Timer;

import Control.Exceptions.EmptyFieldException;
import Control.Exceptions.IDInvalidException;
import Control.tools.Generator;
import model.EspaceEtudiant;
import view.frame.StudentPanelFrame;
import views.StudentPanelView;

public class EspaceEtudiantController {

    private EspaceEtudiant model;
    private StudentPanelView view;

    public EspaceEtudiantController(EspaceEtudiant model, StudentPanelView view) {

        this.model = model;
        this.view = view;

        // =========================
        // HORLOGE
        // =========================
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            view.setTime(Generator.updateTime());
        });
        timer.start();

        // =========================
        // LIAISON TABLES
        // =========================
        model.setTableTodos(view.getjTableTodo());
        model.setTableCoursDispo(view.getjTableSubjects());
        model.setTableMessages(view.getjTableInbox());

        // =========================
        // INFOS ETUDIANT
        // =========================
        view.setStudentName(model.getEtudiant().getName());
        view.setStudentID(String.valueOf(model.getEtudiant().getID()));

        // =========================
        // CHARGEMENT INITIAL
        // =========================
        model.rafraichirDonnees();

        // =========================
        // AJOUT TODO (comme ancien bouton 6)
        // =========================
        view.addButtonAddTodoListener(e -> {
            try {
                model.setTodoDescription(view.getTodoInput());
                model.ajouterTache();
            } catch (SQLException ex) {
                view.displayMessage("Erreur SQL");
            } catch (EmptyFieldException ex) {
                view.displayMessage("Champ vide");
            }
        });

        // =========================
        // SUPPRIMER TODO (comme bouton 7)
        // =========================
        view.addButtonRemoveTodoListener(e -> {
            try {
                model.supprimerTache();
            } catch (SQLException ex) {
                view.displayMessage("Erreur suppression");
            }
        });

        // =========================
        // ENVOYER MESSAGE (bouton 2)
        // =========================
        view.addButtonSendMailListener(e -> {
            try {
                model.setDestinataireMsg(""); // ⚠️ à remplacer si tu ajoutes champ ID
                model.setContenuMessage(view.getMailMessage());

                model.envoyerMessage();

                view.displayMessage("Message envoyé ✔");

            } catch (SQLException ex) {
                view.displayMessage("Erreur SQL");
            } catch (EmptyFieldException ex) {
                view.displayMessage("Champs vides");
            } catch (IDInvalidException ex) {
                view.displayMessage("ID invalide");
            }
        });

        // =========================
        // REFRESH (bouton 8)
        // =========================
        view.addButtonRefreshListener(e -> {
            model.rafraichirDonnees();
        });

        // =========================
        // CLICK TABLE (comme ancien)
        // =========================
        view.addTableListener1(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JTable table = view.getjTableTodo();
                int row = table.getSelectedRow();

                if (row != -1) {
                    Object value = table.getValueAt(row, 0); // ID
                    model.setTodoId(String.valueOf(value));
                }
            }
        });

        // =========================
        // LOGOUT (bouton 1)
        // =========================
        view.addButtonLogoutListener(e -> {
            view.displayMessage("Déconnexion...");
            view.close();

            // new LoginController(...)
        });
    }
}