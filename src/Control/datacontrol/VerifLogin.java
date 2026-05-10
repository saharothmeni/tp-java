package Control.datacontrol; // Package mis à jour selon l'arborescence src/Control/datacontrol

import java.sql.*;
import Control.security.SHA256;

import java.sql.*;
import Control.security.SHA256;

public class VerifLogin {

    public int verifcon(int id, String password) throws SQLException {
        Connection con = Singleton_Dbconnect.getInstance();

        PreparedStatement ps = con.prepareStatement(
            "SELECT password, role FROM authentication WHERE id = ?"
        );
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        // ✅ CORRECTION 1 : si aucune ligne trouvée → retour immédiat
        if (!rs.next()) {
            rs.close();
            ps.close();
            return -1;
        }

        String storedPass  = rs.getString("password");
        String role        = rs.getString("role");
        String hashedInput = SHA256.getInstance().hash(password);

        rs.close();
        ps.close();

        // ✅ CORRECTION 2 : vérifier le mot de passe d'abord
        if (storedPass == null || !storedPass.equals(hashedInput)) {
            return -1;
        }

        // ✅ CORRECTION 3 : .trim() supprime les espaces cachés dans role
        if (role == null) return -1;

        switch (role.trim()) {
            case "1":  return 1; // Étudiant
            case "0":  return 2; // Enseignant
            case "99": return 3; // Admin
            default:   return -1;
        }
    }
}