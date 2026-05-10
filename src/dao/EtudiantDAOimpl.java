package dao; // Package propre


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entites.Etudiant;
import Control.datacontrol.Singleton_Dbconnect;

/**
 * DAO Etudiant
 *
 * Table DB   : etudiant
 * Colonnes   : id | name | gender
 *
 * Tables liées supprimées en cascade :
 *   inscriptionmadule  : id_etudiant | id_matiere | note_controle | note_examen
 *   authentication     : id | password | role
 */
public class EtudiantDAOimpl implements EtudiantDAO {

    // Nom exact de la table d'inscription dans la DB
    private static final String TABLE_INSCRIPTION = "inscriptionmadule";

    // ─────────────────────────────────────────────────────────────
    // Tous les étudiants
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getAllEtudiant() {
        try {
            Statement stmt = Singleton_Dbconnect.getInstance().createStatement();
            return stmt.executeQuery("SELECT * FROM etudiant");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Un étudiant par objet
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getEtudiant(Etudiant etd) {
        return getEtudiant(etd.getID());
    }

    // ─────────────────────────────────────────────────────────────
    // Un étudiant par ID
    // ─────────────────────────────────────────────────────────────
    @Override
    public ResultSet getEtudiant(int id) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement("SELECT * FROM etudiant WHERE id = ?");
            myStmt.setInt(1, id);
            return myStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Ajouter un étudiant
    // Colonnes : id | name | gender
    // ─────────────────────────────────────────────────────────────
    @Override
    public void ajoutEtudiant(Etudiant etd) throws SQLException {
        PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
            .prepareStatement(
                "INSERT INTO etudiant (id, name, gender) VALUES (?, ?, ?)"
            );
        myStmt.setInt(1, etd.getID());
        myStmt.setString(2, etd.getName());
        myStmt.setString(3, String.valueOf(etd.getGender()));
        myStmt.executeUpdate();
        myStmt.close();
    }

    // ─────────────────────────────────────────────────────────────
    // Supprimer un étudiant
    // Ordre : inscriptionmadule → authentication → etudiant
    // ─────────────────────────────────────────────────────────────
    @Override
    public void supprimerEtudiant(Etudiant etd) {
        try {
            // 1. Supprimer les inscriptions aux matières
            PreparedStatement stmt1 = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "DELETE FROM " + TABLE_INSCRIPTION + " WHERE id_etudiant = ?"
                );
            // 2. Supprimer le compte authentication
            PreparedStatement stmt2 = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "DELETE FROM authentication WHERE id = ?"
                );
            // 3. Supprimer l'étudiant
            PreparedStatement stmt3 = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "DELETE FROM etudiant WHERE id = ?"
                );

            stmt1.setInt(1, etd.getID());
            stmt2.setInt(1, etd.getID());
            stmt3.setInt(1, etd.getID());

            stmt1.executeUpdate();
            stmt2.executeUpdate();
            stmt3.executeUpdate();

            stmt1.close();
            stmt2.close();
            stmt3.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Modifier le nom d'un étudiant
    // Colonne : name
    // ─────────────────────────────────────────────────────────────
    @Override
    public void updateEtudiant(Etudiant etd, String nouveauNom) {
        try {
            PreparedStatement myStmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "UPDATE etudiant SET name = ? WHERE id = ?"
                );
            myStmt.setString(1, nouveauNom);
            myStmt.setInt(2, etd.getID());
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}