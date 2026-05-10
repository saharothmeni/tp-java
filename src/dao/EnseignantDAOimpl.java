package dao; // Package propre

import java.sql.*;
import entites.Enseignant;
import Control.datacontrol.Singleton_Dbconnect;

public class EnseignantDAOimpl implements EnseignantDAO {

    @Override
    public ResultSet getAllEnseignant() {
        try {
            Statement stmt = Singleton_Dbconnect.getInstance().createStatement();
            // ✅ minuscule : table réelle = "enseignant"
            return stmt.executeQuery("SELECT * FROM enseignant");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getEnseignant(Enseignant ens) {
        return getEnseignant(ens.getId());
    }

    @Override
    public ResultSet getEnseignant(int id) {
        try {
            PreparedStatement stmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "SELECT * FROM enseignant WHERE id = ?"
                );
            stmt.setInt(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void ajoutEnseignant(Enseignant ens) throws SQLException {
        // ✅ colonnes réelles : id | nom | genre | specialite | grade
        PreparedStatement stmt = Singleton_Dbconnect.getInstance()
            .prepareStatement(
                "INSERT INTO enseignant (id, nom, genre, specialite, grade) " +
                "VALUES (?, ?, ?, ?, ?)"
            );
        stmt.setInt(1, ens.getId());
        stmt.setString(2, ens.getNom());
        stmt.setString(3, String.valueOf(ens.getGenre()));
        stmt.setString(4, ens.getSpecialite());
        stmt.setString(5, ens.getGrade());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void supprimerEnseignant(Enseignant ens) {
        try {
            // ✅ Supprimer dépendances avant l'enseignant
            PreparedStatement stmt1 = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "DELETE FROM enscours WHERE id_enseignant = ?"
                );
            PreparedStatement stmt2 = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "DELETE FROM authentication WHERE id = ?"
                );
            PreparedStatement stmt3 = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "DELETE FROM enseignant WHERE id = ?"
                );

            stmt1.setInt(1, ens.getId());
            stmt2.setInt(1, ens.getId());
            stmt3.setInt(1, ens.getId());

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

    @Override
    public void updateEnseignant(Enseignant ens, String nouveauNom) {
        try {
            // ✅ colonne réelle : "nom"
            PreparedStatement stmt = Singleton_Dbconnect.getInstance()
                .prepareStatement(
                    "UPDATE enseignant SET nom = ? WHERE id = ?"
                );
            stmt.setString(1, nouveauNom);
            stmt.setInt(2, ens.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}