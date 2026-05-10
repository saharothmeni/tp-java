package Control.datacontrol; // Package mis à jour selon l'image

import java.sql.*;

/**
 * Cette classe permet de faire la correspondance entre les IDs et les Noms
 * en interrogeant à la fois les tables 'enseignant' et 'etudiant'.
 */
public class ResolveNameSystem {

    /**
     * Retourne toutes les données d'identification (ID et Nom) des deux types d'utilisateurs.
     */
    private static ResultSet getAllData() throws SQLException {
        // Utilisation du Singleton_Dbconnect présent dans le même package
        Connection con = Singleton_Dbconnect.getInstance();
        
        // La clause UNION permet de fusionner les résultats des deux tables
        String sql = "SELECT id, name FROM enseignant UNION SELECT id, name FROM etudiant";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        return pstmt.executeQuery();
    }

    /**
     * Retourne le nom correspondant à un identifiant (ID) donné.
     */
    public static String getName(int id) {
        try (ResultSet rs = getAllData()) { // Utilisation du try-with-resources pour fermer le ResultSet
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Inconnu"; // Retourne une valeur par défaut si non trouvé
    }

    /**
     * Retourne l'identifiant (sous forme de String) correspondant à un nom donné.
     */
    public static String getID(String name) {
        try (ResultSet rs = getAllData()) {
            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(name)) {
                    return String.valueOf(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}