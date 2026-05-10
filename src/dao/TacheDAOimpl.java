package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Control.datacontrol.Singleton_Dbconnect;
import entites.Tache;





public class TacheDAOimpl implements TacheDAO {

    @Override
    public void ajouterTache(Tache t) throws SQLException {
        String sql = "INSERT INTO todo (etd_id, tdate, description) VALUES (?, ?, ?)";
        PreparedStatement myStmt = Singleton_Dbconnect
                .getInstance().prepareStatement(sql);

        myStmt.setInt(1, t.getId());
        myStmt.setString(2, t.getDateEcheance());
        myStmt.setString(3, t.getDescription());

        myStmt.executeUpdate();
    }

    @Override
    public void supprimerTache(Tache t) throws SQLException {
        String sql = "DELETE FROM todo WHERE id = ?";
        PreparedStatement myStmt = Singleton_Dbconnect
                .getInstance().prepareStatement(sql);

        myStmt.setInt(1, t.getId());

        myStmt.executeUpdate();
    }

    @Override
    public ResultSet getMesTaches(int id) {
        try {
            String sql = "SELECT id, tdate, description FROM todo WHERE etd_id = ?";
            PreparedStatement pstmt = Singleton_Dbconnect
                    .getInstance().prepareStatement(sql);

            pstmt.setInt(1, id);
            return pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}