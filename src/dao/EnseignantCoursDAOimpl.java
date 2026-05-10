package dao;





import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entites.Enseignant;
import entites.Matiere;
import Control.datacontrol.Singleton_Dbconnect;

/**
 * DAO Affectation enseignant ↔ matière
 *
 * Table DB  : affectationenseignant
 * Colonnes  : id_enseignant | id_matiere | annee_universitaire
 *
 * Table enseignant : id | nom | genre | specialite | grade
 * Table matiere    : id | nom | description | chargeHoraire | coefficient
 * Table etudiant   : id | name | gender
 * Table inscription: inscriptionmadule → id_etudiant | id_matiere
 */
public class EnseignantCoursDAOimpl implements EnseignantCoursDAO {

    private static final String TABLE_AFF  = "affectationenseignant";
    private static final String TABLE_INS  = "inscriptionmadule";

    // ─────────────────────────────────────────────────────────────
    // Toutes les affectations enseignant ↔ matière (admin)
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getTousLesCoursEtEnseignants() {
        try {
            Statement stmt = Singleton_Dbconnect.getInstance().createStatement();
            return stmt.executeQuery(
                "SELECT e.id, e.nom, mat.id AS mat_id, mat.nom AS mat_nom " +
                "FROM matiere mat " +
                "JOIN " + TABLE_AFF + " ae ON ae.id_matiere  = mat.id " +
                "JOIN enseignant e          ON e.id           = ae.id_enseignant"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Cours d'un enseignant donné
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getMoncours(int idEnseignant) {
        try {
            String sql =
                "SELECT mat.nom, mat.description " +
                "FROM matiere mat " +
                "JOIN " + TABLE_AFF + " ae ON ae.id_matiere   = mat.id " +
                "JOIN enseignant e          ON e.id            = ae.id_enseignant " +
                "WHERE e.id = ?";
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(sql);
            myStmt.setInt(1, idEnseignant);
            return myStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Étudiants partageant une matière avec cet enseignant
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getEtudiantsMemeCours(int idEnseignant) {
        try {
            String sql =
                "SELECT DISTINCT etd.name " +
                "FROM etudiant etd " +
                "JOIN " + TABLE_INS + " im ON im.id_etudiant  = etd.id " +
                "JOIN " + TABLE_AFF + " ae ON ae.id_matiere   = im.id_matiere " +
                "WHERE ae.id_enseignant = ?";
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(sql);
            myStmt.setInt(1, idEnseignant);
            return myStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Affecter un enseignant à une matière
    // ─────────────────────────────────────────────────────────────
    @Override
    public void affecterEnseignantACours(Enseignant ens, Matiere mat) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "INSERT INTO " + TABLE_AFF +
                    " (id_enseignant, id_matiere, annee_universitaire) " +
                    "VALUES (?, ?, ?)"
                );
            myStmt.setInt(1, ens.getId());
            myStmt.setInt(2, mat.getId());
            myStmt.setString(3, "2023-2024");
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Supprimer une affectation
    // ─────────────────────────────────────────────────────────────
    @Override
    public void supprimerAffectation(Enseignant ens, Matiere mat) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "DELETE FROM " + TABLE_AFF +
                    " WHERE id_enseignant = ? AND id_matiere = ?"
                );
            myStmt.setInt(1, ens.getId());
            myStmt.setInt(2, mat.getId());
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Liste matière + enseignant
    // ─────────────────────────────────────────────────────────────
    public ResultSet getbyNameEnscours() {
        try {
            Statement stmt = Singleton_Dbconnect.getInstance().createStatement();
            return stmt.executeQuery(
                "SELECT mat.nom, e.nom AS ens_nom, mat.description " +
                "FROM matiere mat " +
                "JOIN " + TABLE_AFF + " ae ON ae.id_matiere  = mat.id " +
                "JOIN enseignant e          ON e.id           = ae.id_enseignant"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Affectations filtrées par matière
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getAffectationsParMatiere(Matiere mat) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "SELECT e.*, m.* " +
                    "FROM enseignant e " +
                    "JOIN " + TABLE_AFF + " ae ON ae.id_enseignant = e.id " +
                    "JOIN matiere m             ON m.id             = ae.id_matiere " +
                    "WHERE m.id = ?"
                );
            myStmt.setInt(1, mat.getId());
            return myStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}