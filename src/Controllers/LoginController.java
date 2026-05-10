package Controllers;

import views.LoginView;
import views.StudentPanelView;
import views.TeacherPanelView;
import views.AdminPanelView;
import views.SignupView;
import java.awt.event.ActionEvent;
import Control.Exceptions.*;
import model.*;

public class LoginController {

    private LoginModel lm;
    private LoginView  lv;

    public LoginController(LoginModel lm, LoginView lv) {
        this.lm = lm;
        this.lv = lv;
      

        // ─── Bouton Connexion ──────────────────────────────
        lv.addButtonListener((ActionEvent e) -> {
            try {
                updateModel();
                lm.verifchamps();
                lm.verifID();
                lm.verifCaptcha();

                int r = lm.authentication();

                switch (r) {

                    case 1: // Étudiant
                        lv.displayMessgae(lm.getID().getName() + " connecté");
                        lv.Close();
                        EspaceEtudiant    modelEtd = new EspaceEtudiant(lm.getID());
                        StudentPanelView  viewEtd  = new StudentPanelView();
                        new EspaceEtudiantController(modelEtd, viewEtd);
                        
                        break;

                    case 2: // Enseignant
                        lv.displayMessgae(lm.getENS().getNom() + " connecté");
                        lv.Close();
                        EspaceEnseignant  modelEns = new EspaceEnseignant(lm.getENS());
                        TeacherPanelView  viewEns  = new TeacherPanelView();
                        new EspaceEnseignantController(modelEns, viewEns);
                        
                        break;

                    case 3: // Admin
                        lv.displayMessgae("Admin connecté");
                        lv.Close();
                        GestionnaireDonneesAdmin modelAdm = new GestionnaireDonneesAdmin();
                        AdminPanelView           viewAdm  = new AdminPanelView();
                        new AdminController(modelAdm, viewAdm);
                        
                        break;

                    default:
                        lv.displayMessgae("Identifiants incorrects");
                        break;
                }

            } catch (EmptyFieldException ex) {
                lv.displayMessgae("Veuillez remplir tous les champs.");
            } catch (IDInvalidException ex) {
                lv.displayMessgae("Identifiant invalide.");
            } catch (IncorrectCaptchaException ex) {
                lv.displayMessgae("Captcha incorrect.");
            } catch (Exception ex) {
                lv.displayMessgae("Erreur : " + ex.getMessage());
            }
        });

        // ─── Bouton Signup ─────────────────────────────────
        lv.addButtonListener2((ActionEvent e) -> {
            lv.Close();
            new SignupController(new SignupModel(), new SignupView());
        });
    }

    private void updateModel() {
        lm.setID(lv.GetID());
        lm.setPass(lv.GetPass());
        lm.setCapt(lv.GetCapt());
        lm.setOrcaptcha(lv.GetOrCapt());
    }
}