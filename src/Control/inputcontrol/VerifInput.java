package Control.inputcontrol;

import Control.Exceptions.EmptyFieldException;
import Control.Exceptions.GenderInvalidException;
import Control.Exceptions.IDInvalidException;
import Control.Exceptions.IncorrectCaptchaException;
import Control.Exceptions.NoteInvalidException;
import Control.Exceptions.PasswordInvalidException;

public class VerifInput {

    // ─────────────────────────────────────────────────────────────
    // Vérification ID : entier positif
    // ─────────────────────────────────────────────────────────────
    public static void verifID(String id) throws IDInvalidException {
        if (id == null || id.trim().isEmpty()) {
            throw new IDInvalidException("L'ID ne peut pas être vide.");
        }
        try {
            int val = Integer.parseInt(id.trim());
            if (val <= 0) {
                throw new IDInvalidException("L'ID doit être un entier positif.");
            }
        } catch (NumberFormatException e) {
            throw new IDInvalidException("L'ID doit être un nombre entier.");
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Vérification champs vides
    // ─────────────────────────────────────────────────────────────
    public static void verifFIELDS(String champ) throws EmptyFieldException {
        if (champ == null || champ.trim().isEmpty()) {
            throw new EmptyFieldException("Tous les champs sont obligatoires.");
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Vérification genre : M ou F uniquement
    // ─────────────────────────────────────────────────────────────
    public static void verifGender(String genre) throws GenderInvalidException {
        if (genre == null || (!genre.equalsIgnoreCase("M")
                           && !genre.equalsIgnoreCase("F"))) {
            throw new GenderInvalidException("Le genre doit être M ou F.");
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Vérification captcha
    // ─────────────────────────────────────────────────────────────
    public static void __verifCAPTCHA__(String saisi, String original)
            throws IncorrectCaptchaException {
        if (saisi == null || !saisi.equals(original)) {
            throw new IncorrectCaptchaException("Captcha incorrect.");
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Vérification mot de passe : les deux champs identiques
    // ─────────────────────────────────────────────────────────────
    public static void __verifPASS__(char[] password, char[] rpassword)
            throws PasswordInvalidException {
        if (password == null || rpassword == null) {
            throw new PasswordInvalidException("Le mot de passe ne peut pas être vide.");
        }
        if (!new String(password).equals(new String(rpassword))) {
            throw new PasswordInvalidException("Les mots de passe ne correspondent pas.");
        }
        if (password.length < 4) {
            throw new PasswordInvalidException("Le mot de passe doit contenir au moins 4 caractères.");
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Vérification note : entre 0 et 20
    // ─────────────────────────────────────────────────────────────
    public static void verifNote(double note) throws NoteInvalidException {
        if (note < 0 || note > 20) {
            throw new NoteInvalidException("La note doit être comprise entre 0 et 20.");
        }
    }
}