package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entites.Etudiant;
import entites.Matiere;
import Control.datacontrol.Singleton_Dbconnect;

/**
 * DAO Inscription étudiant ↔ matière
 *
 * Table DB  : inscriptionmodule
 * Colonnes  : id_etudiant | id_matiere | note_controle | note_examen
 *
 * Table etudiant  : id | name | gender
 * Table matiere   : id | nom  | description | chargeHoraire | coefficient
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entites.Etudiant;
import entites.Matiere;
import Control.datacontrol.Singleton_Dbconnect;

/**
 * DAO Inscription étudiant ↔ matière
 *
 * Table DB  : inscriptionmadule   ← NOM EXACT dans gestion_ecole
 * Colonnes  : id_etudiant | id_matiere | note_controle | note_examen
 *
 * Table etudiant : id | name | gender
 * Table matiere  : id | nom  | description | chargeHoraire | coefficient
 */
public class EtudiantCoursDAOimpl implements EtudiantCoursDAO {

    // Nom exact de la table dans la base de données
    private static final String TABLE = "inscriptionmadule";

    // ─────────────────────────────────────────────────────────────
    // Toutes les inscriptions (admin : onglet Cours Étudiants)
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getAllEtudCours() {
        try {
            Statement stmt = Singleton_Dbconnect.getInstance().createStatement();
            return stmt.executeQuery(
                "SELECT e.id, e.name, mat.id AS mat_id, mat.nom " +
                "FROM matiere mat " +
                "JOIN " + TABLE + " im ON mat.id = im.id_matiere " +
                "JOIN etudiant e        ON e.id   = im.id_etudiant"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Cours d'un étudiant (surcharge objet)
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getEtudCours(Etudiant etd) {
        return getEtudCours(etd.getID());
    }

    // ─────────────────────────────────────────────────────────────
    // Cours d'un étudiant par ID
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getEtudCours(int idEtudiant) {
        try {
            String sql =
                "SELECT mat.nom, mat.description, " +
                "       im.note_controle, im.note_examen " +
                "FROM matiere mat " +
                "JOIN " + TABLE + " im ON mat.id = im.id_matiere " +
                "JOIN etudiant e        ON e.id   = im.id_etudiant " +
                "WHERE e.id = ?";
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(sql);
            myStmt.setInt(1, idEtudiant);
            return myStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Inscrire un étudiant à une matière
    // ─────────────────────────────────────────────────────────────
    @Override
    public void ajoutEtudCours(Etudiant etd, Matiere mat) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "INSERT INTO " + TABLE + " (id_etudiant, id_matiere) " +
                    "VALUES (?, ?)"
                );
            myStmt.setInt(1, etd.getID());
            myStmt.setInt(2, mat.getId());
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Désinscrire un étudiant d'une matière
    // ─────────────────────────────────────────────────────────────
    @Override
    public void supprimerCours(Etudiant etd, Matiere mat) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "DELETE FROM " + TABLE +
                    " WHERE id_etudiant = ? AND id_matiere = ?"
                );
            myStmt.setInt(1, etd.getID());
            myStmt.setInt(2, mat.getId());
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Mettre à jour note_controle ET note_examen
    // ─────────────────────────────────────────────────────────────
    @Override
    public void updateNote(int idMat, int idEtudiant, double note)
            throws SQLException {
        PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
            .prepareStatement(
                "UPDATE " + TABLE +
                " SET note_controle = ?, note_examen = ? " +
                "WHERE id_etudiant = ? AND id_matiere = ?"
            );
        myStmt.setDouble(1, note);
        myStmt.setDouble(2, note);
        myStmt.setInt(3, idEtudiant);
        myStmt.setInt(4, idMat);
        myStmt.executeUpdate();
        myStmt.close();
    }

    // ─────────────────────────────────────────────────────────────
    // Mettre à jour uniquement note_controle
    // ─────────────────────────────────────────────────────────────
    public void updateNoteControle(int idMatiere, int idEtudiant, double note)
            throws SQLException {
        PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
            .prepareStatement(
                "UPDATE " + TABLE +
                " SET note_controle = ? " +
                "WHERE id_etudiant = ? AND id_matiere = ?"
            );
        myStmt.setDouble(1, note);
        myStmt.setInt(2, idEtudiant);
        myStmt.setInt(3, idMatiere);
        myStmt.executeUpdate();
        myStmt.close();
    }

    // ─────────────────────────────────────────────────────────────
    // Mettre à jour uniquement note_examen
    // ─────────────────────────────────────────────────────────────
    public void updateNoteExamen(int idMatiere, int idEtudiant, double note)
            throws SQLException {
        PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
            .prepareStatement(
                "UPDATE " + TABLE +
                " SET note_examen = ? " +
                "WHERE id_etudiant = ? AND id_matiere = ?"
            );
        myStmt.setDouble(1, note);
        myStmt.setInt(2, idEtudiant);
        myStmt.setInt(3, idMatiere);
        myStmt.executeUpdate();
        myStmt.close();
    }

    // ─────────────────────────────────────────────────────────────
    // Méthodes de l'interface non utilisées
    // ─────────────────────────────────────────────────────────────
    @Override
    public void updateEtudiantC(int idEtudiant, Etudiant etd) {
        // non implémenté
    }

    @Override
    public void updateEtudiantC(int idEtudiant, Matiere mat) {
        // non implémenté
    }
}