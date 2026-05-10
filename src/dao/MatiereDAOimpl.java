package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Control.datacontrol.Singleton_Dbconnect;
import entites.Matiere;

/**
 * DAO Matière
 *
 * Table DB  : matiere
 * Colonnes  : id | nom | description | chargeHoraire | coefficient
 *
 * Tables liées :
 *   inscriptionmadule     → id_etudiant | id_matiere | note_controle | note_examen
 *   affectationenseignant → id_enseignant | id_matiere | annee_universitaire
 */
public class MatiereDAOimpl implements MatiereDAO {

    private static final String COL_NOM   = "nom";
    private static final String COL_DESC  = "description";
    private static final String COL_VOL   = "chargeHoraire";
    private static final String COL_COEFF = "coefficient";

    private static final String TABLE_INS = "inscriptionmadule";
    private static final String TABLE_AFF = "affectationenseignant";

    // ─────────────────────────────────────────────────────────────
    // Toutes les matières
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getAllMatiere() {
        try {
            Statement stmt = Singleton_Dbconnect.getInstance().createStatement();
            return stmt.executeQuery("SELECT * FROM matiere");
        } catch (SQLException e) {
            Logger.getLogger(MatiereDAOimpl.class.getName())
                  .log(Level.SEVERE, "Erreur getAllMatiere", e);
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Une matière par objet
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getMatiere(Matiere mat) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement("SELECT * FROM matiere WHERE id = ?");
            myStmt.setInt(1, mat.getId());
            return myStmt.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(MatiereDAOimpl.class.getName())
                  .log(Level.SEVERE, "Erreur getMatiere", e);
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Ajouter une matière
    // Colonnes : id | nom | description | chargeHoraire | coefficient
    // ─────────────────────────────────────────────────────────────
    @Override
    public void ajoutMatiere(Matiere mat) {
        try {
            String sql =
                "INSERT INTO matiere (id, " + COL_NOM + ", " + COL_DESC + ", " +
                COL_VOL + ", " + COL_COEFF + ") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(sql);
            myStmt.setInt(1, mat.getId());
            myStmt.setString(2, mat.getNom());
            myStmt.setString(3, mat.getDescription());
            myStmt.setInt(4, mat.getChargeHoraire());
            myStmt.setDouble(5, mat.getCoefficient());
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            Logger.getLogger(MatiereDAOimpl.class.getName())
                  .log(Level.SEVERE, "Erreur ajoutMatiere", e);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Supprimer une matière
    // ─────────────────────────────────────────────────────────────
    @Override
    public void supprimerMatiere(Matiere mat) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement("DELETE FROM matiere WHERE id = ?");
            myStmt.setInt(1, mat.getId());
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            Logger.getLogger(MatiereDAOimpl.class.getName())
                  .log(Level.SEVERE, "Erreur supprimerMatiere", e);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Modifier une matière
    // ─────────────────────────────────────────────────────────────
    @Override
    public void updateMatiere(Matiere mat) {
        try {
            String sql =
                "UPDATE matiere SET " +
                COL_NOM   + " = ?, " +
                COL_DESC  + " = ?, " +
                COL_VOL   + " = ?, " +
                COL_COEFF + " = ? " +
                "WHERE id = ?";
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(sql);
            myStmt.setString(1, mat.getNom());
            myStmt.setString(2, mat.getDescription());
            myStmt.setInt(3, mat.getChargeHoraire());
            myStmt.setDouble(4, mat.getCoefficient());
            myStmt.setInt(5, mat.getId());
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            Logger.getLogger(MatiereDAOimpl.class.getName())
                  .log(Level.SEVERE, "Erreur updateMatiere", e);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // ID matière commune à un étudiant et un enseignant
    //   inscriptionmadule     : id_etudiant, id_matiere
    //   affectationenseignant : id_enseignant, id_matiere
    // ─────────────────────────────────────────────────────────────
    @Override
    public Integer getIDMat(int idEtudiant, int idEnseignant) {
        try {
            String sql =
                "SELECT im.id_matiere " +
                "FROM " + TABLE_INS + " im " +
                "JOIN " + TABLE_AFF + " ae ON ae.id_matiere  = im.id_matiere " +
                "WHERE im.id_etudiant   = ? " +
                "AND   ae.id_enseignant = ? " +
                "LIMIT 1";
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(sql);
            myStmt.setInt(1, idEtudiant);
            myStmt.setInt(2, idEnseignant);
            ResultSet rs = myStmt.executeQuery();
            if (rs.next()) return rs.getInt("id_matiere");
        } catch (SQLException ex) {
            Logger.getLogger(MatiereDAOimpl.class.getName())
                  .log(Level.SEVERE, "Erreur getIDMat", ex);
        }
        return null;
    }
}