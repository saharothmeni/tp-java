package model;



import java.sql.ResultSet;
import java.sql.SQLException;

import dao.EtudiantDAOimpl;
import dao.EnseignantDAOimpl;

import Control.Exceptions.EmptyFieldException;
import Control.Exceptions.IDInvalidException;
import Control.Exceptions.IncorrectCaptchaException;
import Control.inputcontrol.VerifInput;

import entites.Enseignant;
import entites.Etudiant;

/**
 * Modèle login
 *
 * Tables utilisées :
 *   authentication : id | password | role
 *                    role = 0 → enseignant
 *                    role = 1 → étudiant
 *                    role = 99 → admin
 *
 *   etudiant       : id | name | gender
 *   enseignant     : id | nom  | genre | specialite | grade
 */
public class LoginModel {

    private String userid;
    private char[] password;
    private String captcha;
    private String orcaptcha;

    // ─────────────────────────────────────────────────────────────
    // GETTERS / SETTERS
    // ─────────────────────────────────────────────────────────────
    public String  getID1()                      { return userid; }
    public void    setID(String id)              { this.userid = id; }

    public char[]  getPass()                     { return password; }
    public void    setPass(char[] pass)          { this.password = pass; }

    public String  getCapt()                     { return captcha; }
    public void    setCapt(String capt)          { this.captcha = capt; }

    public String  getOrCapt()                   { return orcaptcha; }
    public void    setOrcaptcha(String or)       { this.orcaptcha = or; }

    // ─────────────────────────────────────────────────────────────
    // VALIDATIONS
    // ─────────────────────────────────────────────────────────────

    /** Vérifie que userid et password ne sont pas vides */
    public void verifchamps() throws EmptyFieldException {
        VerifInput.verifFIELDS(userid);
        VerifInput.verifFIELDS(new String(password));
    }

    /** Vérifie que l'ID est bien un entier de 8 chiffres */
    public void verifID() throws IDInvalidException {
        VerifInput.verifID(userid);
    }

    /** Vérifie le captcha */
    public void verifCaptcha() throws IncorrectCaptchaException {
        VerifInput.__verifCAPTCHA__(captcha, orcaptcha);
    }

    // ─────────────────────────────────────────────────────────────
    // AUTHENTIFICATION
    // Retourne : 0 = enseignant | 1 = étudiant | 99 = admin | -1 = échec
    // Table    : authentication (id | password | role)
    // ─────────────────────────────────────────────────────────────
    public int authentication() throws SQLException {
        return new Control.datacontrol.VerifLogin()
                   .verifcon(Integer.parseInt(userid), new String(password));
    }

    // ─────────────────────────────────────────────────────────────
    // RÉCUPÉRER L'ÉTUDIANT CONNECTÉ
    // Table etudiant : id | name | gender
    // ─────────────────────────────────────────────────────────────
    public Etudiant getID() throws SQLException {

        ResultSet rs = new EtudiantDAOimpl()
                           .getEtudiant(Integer.parseInt(userid));

        int    id     = 0;
        String name   = "";
        String gender = "";

        while (rs.next()) {
            id     = rs.getInt("id");
            name   = rs.getString("name");    // colonne confirmée : name
            gender = rs.getString("gender");  // colonne confirmée : gender
        }

        char g = (gender != null && !gender.isEmpty())
                 ? gender.charAt(0) : 'U';

        return new Etudiant(id, name, g);
    }

    // ─────────────────────────────────────────────────────────────
    // RÉCUPÉRER L'ENSEIGNANT CONNECTÉ
    // Table enseignant : id | nom | genre | specialite | grade
    // ─────────────────────────────────────────────────────────────
    public Enseignant getENS() throws SQLException {

        ResultSet rs = new EnseignantDAOimpl()
                           .getEnseignant(Integer.parseInt(userid));

        int    id         = 0;
        String nom        = "";
        String genre      = "";
        String specialite = "";
        String grade      = "";

        while (rs.next()) {
            id         = rs.getInt("id");
            nom        = rs.getString("nom");          // colonne confirmée : nom
            genre      = rs.getString("genre");        // colonne confirmée : genre
            specialite = rs.getString("specialite");   // colonne confirmée : specialite
            grade      = rs.getString("grade");        // colonne confirmée : grade
        }

        char g = (genre != null && !genre.isEmpty())
                 ? genre.charAt(0) : 'U';

        return new Enseignant(id, nom, g, specialite, grade);
    }
}
