package model;


import java.sql.SQLException;

import Control.inputcontrol.VerifInput;
import Control.Exceptions.*;

import dao.*;
import entites.*;
import Control.datacontrol.InsertAccount;

/**
 * Modèle inscription (signup)
 *
 * Tables utilisées :
 *   etudiant       : id | name | gender
 *   enseignant     : id | nom  | genre | specialite | grade
 *   authentication : id | password | role
 *                    role = 1 → étudiant
 *                    role = 0 → enseignant
 */
public class SignupModel {

    private String userid;
    private String username;
    private String gender;
    private char[] password;
    private char[] rpassword;
    private boolean check;       // false = étudiant, true = enseignant
    private String orcapt;
    private String capt;

    // ─────────────────────────────────────────────────────────────
    // VALIDATIONS
    // ─────────────────────────────────────────────────────────────

    public void verifChamps() throws EmptyFieldException {
        VerifInput.verifFIELDS(userid);
        VerifInput.verifFIELDS(username);
        VerifInput.verifFIELDS(String.valueOf(password));
        VerifInput.verifFIELDS(String.valueOf(rpassword));
    }

    public void verifID() throws IDInvalidException {
        VerifInput.verifID(userid);
    }

    public void verifGender() throws GenderInvalidException {
        VerifInput.verifGender(gender);
    }

    public void verifPASS() throws PasswordInvalidException {
        VerifInput.__verifPASS__(password, rpassword);
    }

    public void verifCAPT() throws IncorrectCaptchaException {
        VerifInput.__verifCAPTCHA__(capt, orcapt);
    }

    // ─────────────────────────────────────────────────────────────
    // CRÉER UN COMPTE
    // ─────────────────────────────────────────────────────────────
    public void createAccount() throws SQLException {

        int    id   = Integer.parseInt(userid);
        String pass = new String(password);

        if (!check) {
            // ── ÉTUDIANT ──────────────────────────────────────────
            // Table etudiant : id | name | gender
            Etudiant etd = new Etudiant(id);
            etd.setName(username);
            etd.setGender(gender.charAt(0));   // colonne : gender

            new EtudiantDAOimpl().ajoutEtudiant(etd);

            // Table authentication : id | password | role
            // role = 1 pour étudiant
            InsertAccount.creationAccount(id, pass, 1);

        } else {
            // ── ENSEIGNANT ────────────────────────────────────────
            // Table enseignant : id | nom | genre | specialite | grade
            Enseignant ens = new Enseignant(id);
            ens.setNom(username);
            ens.setGenre(gender.charAt(0));    // colonne : genre

            new EnseignantDAOimpl().ajoutEnseignant(ens);

            // Table authentication : id | password | role
            // role = 0 pour enseignant
            InsertAccount.creationAccount(id, pass, 0);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // GETTERS / SETTERS
    // ─────────────────────────────────────────────────────────────
    public String  getUserid()               { return userid; }
    public void    setUserid(String userid)  { this.userid = userid; }

    public String  getUsername()                  { return username; }
    public void    setUsername(String username)   { this.username = username; }

    public String  getGender()               { return gender; }
    public void    setGender(String gender)  { this.gender = gender; }

    public char[]  getPassword()                   { return password; }
    public void    setPassword(char[] password)    { this.password = password; }

    public char[]  getRpassword()                    { return rpassword; }
    public void    setRpassword(char[] rpassword)   { this.rpassword = rpassword; }

    public boolean isCheck()                 { return check; }
    public void    setCheck(boolean check)   { this.check = check; }

    public String  getOrcapt()               { return orcapt; }
    public void    setOrcapt(String orcapt)  { this.orcapt = orcapt; }

    public String  getCapt()              { return capt; }
    public void    setCapt(String capt)  { this.capt = capt; }
}