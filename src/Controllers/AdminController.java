package Controllers;
import java.awt.event.ActionEvent;
import model.GestionnaireDonneesAdmin;
import views.AdminPanelView;
import Control.Exceptions.*;

import java.awt.event.ActionEvent;

public class AdminController {

    private final AdminPanelView view;
    private final GestionnaireDonneesAdmin model;

    public AdminController(GestionnaireDonneesAdmin model, AdminPanelView view) {
        this.model = model;
        this.view  = view;
        configurerLiaisons();
        model.rafraichirToutesLesDonnees();
        configurerEvenementsGeneraux();
        configurerActionsEtudiants();
        configurerActionsEnseignants();
        configurerActionsMatieres();
    }

    // ═══════════════════════════════════════════════════
    // LIAISONS TABLES → MODÈLE
    // ═══════════════════════════════════════════════════
    private void configurerLiaisons() {
        model.setTableEtudiants(view.getjTable1());      // onglet Étudiants
        model.setTableEnseignants(view.getjTable2());    // onglet Enseignants
        model.setTableMatieres(view.getjTable7());       // onglet Matières
        model.setTableAffectations(view.getjTable5());   // onglet Cours Enseignants
        model.setTableInscriptions(view.getjTable6());   // onglet Cours Étudiants
    }

    // ═══════════════════════════════════════════════════
    // GÉNÉRAL
    // ═══════════════════════════════════════════════════
    private void configurerEvenementsGeneraux() {
        view.buttonRefrech((ActionEvent e) -> {
            model.rafraichirToutesLesDonnees();
            view.displayMessage("Données actualisées.");
        });
        view.addButtonListener1((ActionEvent e) -> {
            view.displayMessage("Session terminée.");
            view.close();
        });
    }

    // ═══════════════════════════════════════════════════
    // ÉTUDIANTS
    //   jTextField1  = ID étudiant
    //   jTextField2  = Nom étudiant
    //   jTextField3  = Genre étudiant
    //   jTextField9  = ID étudiant  (onglet Cours Étudiants)
    //   jTextField10 = ID matière   (onglet Cours Étudiants)
    // ═══════════════════════════════════════════════════
    private void configurerActionsEtudiants() {

        // Ajouter étudiant — password = id par défaut
        view.buttonAjoutEtduiant((ActionEvent e) -> {
            try {
                String id    = view.getjTextField1();
                String nom   = view.getjTextField2();
                String genre = view.getjTextField3();
                model.validerChamps(id, nom, genre);
                model.validerID(id);
                model.validerGenre(genre);
                model.setEtudiantId(id);
                model.setEtudiantNom(nom);
                model.setEtudiantGenre(genre);
                model.setEtudiantPassword(id);
                model.insererEtudiant();
                view.displayMessage("Étudiant ajouté. Mot de passe : " + id);
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // Modifier étudiant
        view.buttonUpdateEtduiant((ActionEvent e) -> {
            try {
                String id  = view.getjTextField1();
                String nom = view.getjTextField2();
                model.validerChamps(id, nom);
                model.validerID(id);
                model.setEtudiantId(id);
                model.setEtudiantNom(nom);
                model.modifierEtudiant();
                view.displayMessage("Étudiant modifié.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // Supprimer étudiant
        view.buttonSuppEtduiant((ActionEvent e) -> {
            try {
                String id = view.getjTextField1();
                model.validerID(id);
                model.setEtudiantId(id);
                model.supprimerEtudiant();
                view.displayMessage("Étudiant supprimé.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // Inscrire étudiant à une matière
        view.buttonAjoutMatiereEtudiant((ActionEvent e) -> {
            try {
                String idEtd = view.getjTextField9();
                String idMat = view.getjTextField10();
                model.validerChamps(idEtd, idMat);
                model.validerID(idEtd);
                model.validerID(idMat);
                model.setIdEtudiantInscription(idEtd);
                model.setIdMatiereInscription(idMat);
                model.inscrireEtudiantAMatiere();
                view.displayMessage("Étudiant inscrit à la matière.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // Désinscrire étudiant d'une matière
        view.buttonSuppMatiereEtudiant((ActionEvent e) -> {
            try {
                String idEtd = view.getjTextField9();
                String idMat = view.getjTextField10();
                model.validerChamps(idEtd, idMat);
                model.validerID(idEtd);
                model.validerID(idMat);
                model.setIdEtudiantInscription(idEtd);
                model.setIdMatiereInscription(idMat);
                model.supprimerInscription();
                view.displayMessage("Inscription supprimée.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        view.buttonFermetureEtudiant((ActionEvent e) -> view.close());
    }

    // ═══════════════════════════════════════════════════
    // ENSEIGNANTS
    //
    // Onglet Teachers (CRUD enseignant) :
    //   getEnseignantId()         → jTextField5
    //   getEnseignantNom()        → jTextField6
    //   getEnseignantGenre()      → jTextField4
    //   getEnseignantSpecialite() → champ dédié onglet Teachers  ✅ CORRIGÉ
    //   getEnseignantGrade()      → champ dédié onglet Teachers  ✅ CORRIGÉ
    //
    // Onglet Cours Enseignants (liaison uniquement) :
    //   jTextField7 → ID Enseignant pour affectation
    //   jTextField8 → ID Matière    pour affectation
    //
    // ✅ CORRECTION :
    //   Avant : buttonAjoutEnseignant lisait jTextField7 pour "spécialité"
    //           et jTextField8 pour "grade" → champs vides si on est dans
    //           l'onglet Étudiants → EmptyFieldException.
    //   Après : spécialité et grade lus via getEnseignantSpecialite() et
    //           getEnseignantGrade(), getters pointant vers des champs
    //           physiquement dans l'onglet Teachers. jTextField7/8 restent
    //           EXCLUSIVEMENT pour la liaison enseignant-matière.
    // ═══════════════════════════════════════════════════
    private void configurerActionsEnseignants() {

        // ── Ajouter enseignant ────────────────────────────────────────────────
        view.buttonAjoutEnseignant((ActionEvent e) -> {
            try {
                String id         = view.getEnseignantId();         // tf5
                String nom        = view.getEnseignantNom();        // tf6
                String genre      = view.getEnseignantGenre();      // tf4
                String specialite = view.getEnseignantSpecialite(); // ✅ champ dédié Teachers
                String grade      = view.getEnseignantGrade();      // ✅ champ dédié Teachers
                model.validerChamps(id, nom, genre, specialite, grade);
                model.validerID(id);
                model.validerGenre(genre);
                model.setEnseignantId(id);
                model.setEnseignantNom(nom);
                model.setEnseignantGenre(genre);
                model.setEnseignantSpecialite(specialite);
                model.setEnseignantGrade(grade);
                model.insererEnseignant();
                view.displayMessage("Enseignant ajouté.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // ── Modifier enseignant ───────────────────────────────────────────────
        view.buttonUpdateEnseignant((ActionEvent e) -> {
            try {
                String id  = view.getEnseignantId();  // tf5
                String nom = view.getEnseignantNom(); // tf6
                model.validerChamps(id, nom);
                model.validerID(id);
                model.setEnseignantId(id);
                model.setEnseignantNom(nom);
                model.modifierEnseignant();
                view.displayMessage("Enseignant modifié.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // ── Supprimer enseignant ──────────────────────────────────────────────
        view.buttonSuppEnseignant((ActionEvent e) -> {
            try {
                String id = view.getEnseignantId(); // tf5
                model.validerID(id);
                model.setEnseignantId(id);
                model.supprimerEnseignant();
                view.displayMessage("Enseignant supprimé.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // ── Affecter enseignant à une matière ─────────────────────────────────
        // jTextField7/8 utilisés ICI SEULEMENT pour la liaison ID ↔ ID
        view.buttonAjoutMatiereEnseignant((ActionEvent e) -> {
            try {
                String idEns = view.getjTextField7(); // ID enseignant — onglet Cours Enseignants
                String idMat = view.getjTextField8(); // ID matière   — onglet Cours Enseignants
                model.validerChamps(idEns, idMat);
                model.validerID(idEns);
                model.validerID(idMat);
                model.setIdEnseignantLiaison(idEns);
                model.setIdMatiereLiaison(idMat);
                model.affecterEnseignantAMatiere();
                view.displayMessage("Matière affectée à l'enseignant.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // ── Désaffecter enseignant d'une matière ──────────────────────────────
        view.buttonSuppMatiereEnseignant((ActionEvent e) -> {
            try {
                String idEns = view.getjTextField7(); // ID enseignant — onglet Cours Enseignants
                String idMat = view.getjTextField8(); // ID matière   — onglet Cours Enseignants
                model.validerChamps(idEns, idMat);
                model.validerID(idEns);
                model.validerID(idMat);
                model.setIdEnseignantLiaison(idEns);
                model.setIdMatiereLiaison(idMat);
                model.supprimerAffectation();
                view.displayMessage("Affectation supprimée.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        view.buttonFermetureEnseignant((ActionEvent e) -> view.close());
    }

    // ═══════════════════════════════════════════════════
    // MATIÈRES
    // ═══════════════════════════════════════════════════
    private void configurerActionsMatieres() {

        // Ajouter matière
        view.buttonAddMatiere((ActionEvent e) -> {
            try {
                String id    = view.getID_mat();
                String nom   = view.getNom_mat();
                String desc  = view.getDesc_mat();
                String vol   = view.getVol_mat();
                String coeff = view.getCoeff_mat();
                model.validerChamps(id, nom, desc, vol, coeff);
                model.validerID(id);
                model.setMatiereId(id);
                model.setMatiereNom(nom);
                model.setMatiereDesc(desc);
                model.setMatiereVol(vol);
                model.setMatiereCoeff(coeff);
                model.insererMatiere();
                view.displayMessage("Matière ajoutée.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // Modifier matière
        view.buttonUpdateMatiere((ActionEvent e) -> {
            try {
                String id    = view.getID_mat();
                String nom   = view.getNom_mat();
                String desc  = view.getDesc_mat();
                String vol   = view.getVol_mat();
                String coeff = view.getCoeff_mat();
                model.validerChamps(id, nom);
                model.validerID(id);
                model.setMatiereId(id);
                model.setMatiereNom(nom);
                model.setMatiereDesc(desc);
                model.setMatiereVol(vol);
                model.setMatiereCoeff(coeff);
                model.modifierMatiere();
                view.displayMessage("Matière modifiée.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        // Supprimer matière
        view.buttonRemoveMatiere((ActionEvent e) -> {
            try {
                String id = view.getID_mat();
                model.validerID(id);
                model.setMatiereId(id);
                model.supprimerMatiere();
                view.displayMessage("Matière supprimée.");
                model.rafraichirToutesLesDonnees();
            } catch (Exception ex) { gererErreur(ex); }
        });

        view.buttonCloseMatiere((ActionEvent e) -> view.close());
    }

    // ═══════════════════════════════════════════════════
    // GESTION ERREURS
    // ═══════════════════════════════════════════════════
    private void gererErreur(Exception ex) {
        String msg;
        if      (ex instanceof EmptyFieldException)    msg = "Remplir tous les champs.";
        else if (ex instanceof IDInvalidException)     msg = "L'ID doit être exactement 8 chiffres.";
        else if (ex instanceof GenderInvalidException) msg = "Le genre doit être M ou F.";
        else if (ex instanceof NumberFormatException)  msg = "Volume et Coefficient doivent être des nombres.";
        else msg = "Erreur : " + ex.getMessage();
        view.displayMessage(msg);
    }
}