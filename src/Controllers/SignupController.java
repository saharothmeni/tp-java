package Controllers;




import java.awt.event.ActionEvent;
import java.sql.SQLException;

import model.LoginModel;
import model.SignupModel;
import views.LoginView;
import views.SignupView;

import Control.Exceptions.*;

public class SignupController {

    private SignupModel sum;
    private SignupView suv;

    public SignupController(SignupModel sum, SignupView suv) {
        this.sum = sum;
        this.suv = suv;

        // --- Bouton "S'inscrire" ---
        this.suv.addButtonListener((ActionEvent e) -> {
            try {
                // 1. On synchronise les données de la Vue vers le Modèle
                syncModelWithView();

                // 2. On lance les vérifications définies dans le SignupModel
                sum.verifChamps();
                sum.verifID();
                sum.verifGender();
                sum.verifPASS();
                sum.verifCAPT();

                // 3. Tentative de création du compte (DAO + Authentication)
                sum.createAccount();
                suv.displayMessage("Compte créé avec succès !");
                
                // Optionnel : Rediriger vers le login après succès
                ouvrirLogin();

            } catch (EmptyFieldException ex) {
                suv.displayMessage("Veuillez remplir tous les champs.");
            } catch (IDInvalidException ex) {
                suv.displayMessage("L'identifiant n'est pas valide.");
            } catch (GenderInvalidException ex) {
                suv.displayMessage("Veuillez sélectionner votre sexe.");
            } catch (PasswordInvalidException ex) {
                suv.displayMessage("Les mots de passe ne correspondent pas ou sont invalides.");
            } catch (IncorrectCaptchaException ex) {
                suv.displayMessage("Le code captcha est incorrect.");
            } catch (SQLException ex) {
                suv.displayMessage("Erreur : Cet identifiant est déjà utilisé ou problème de base de données.");
            } catch (Exception ex) {
                suv.displayMessage("Une erreur inattendue est survenue.");
                ex.printStackTrace();
            }
        });

        // --- Bouton "Retour" (Annuler) ---
        this.suv.addButtonListener2((ActionEvent e) -> {
            ouvrirLogin();
        });
    }

    /**
     * Synchronise proprement les types entre SignupView et SignupModel
     */
    private void syncModelWithView() {
        // Identité
        sum.setUserid(suv.getID());
        sum.setUsername(suv.getName());
        
        // Genre : On passe le String "Homme"/"Femme" directement car le modèle 
        // s'occupe de faire le .charAt(0) dans sa méthode createAccount()
        sum.setGender(suv.genGender());

        // Mots de passe : Le modèle attend des char[]
        sum.setPassword(suv.getPass());
        sum.setRpassword(suv.getrPass());

        // Options et Sécurité
        sum.setCheck(suv.getCheck()); // Pour savoir si c'est un enseignant
        sum.setOrcapt(suv.GetOrCapt());
        sum.setCapt(suv.GetCapt());
    }

    /**
     * Méthode utilitaire pour fermer l'inscription et revenir au Login
     */
    private void ouvrirLogin() {
        suv.Close();
        new LoginController(new LoginModel(), new LoginView());
    }
}