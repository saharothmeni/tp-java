package  Control.datacontrol;
import java.sql.*;
import entites.Enseignant;
import entites.Etudiant;
import Control.security.SHA256;

public class InsertAccount {

    
    public static void creationOnInsert(Etudiant etd, int sign) throws SQLException {
        String sql = "INSERT INTO authentication (id, password, role) VALUES (?, ?, ?)";
        
        
        try (Connection con = Singleton_Dbconnect.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, etd.getID());
            
            pstmt.setString(2, SHA256.getInstance().hash(String.valueOf(etd.getID())));
            pstmt.setInt(3, sign);

            pstmt.executeUpdate();
        } 
        
    }

    
    public static void creationAccount(int id, String pass, int role) throws SQLException {
        String sql = "INSERT INTO authentication (id, password, role) VALUES (?, ?, ?)";

        try (Connection con = Singleton_Dbconnect.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.setString(2, SHA256.getInstance().hash(pass));
            pstmt.setInt(3, role);

            pstmt.executeUpdate();
        }
    }

    
    public static void creationOnInsert(Enseignant ens, int sign) throws SQLException {
        String sql = "INSERT INTO authentication (id, password, role) VALUES (?, ?, ?)";

        try (Connection con = Singleton_Dbconnect.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, ens.getId());
            
            pstmt.setString(2, SHA256.getInstance().hash(String.valueOf(ens.getId())));
            pstmt.setInt(3, sign);

            pstmt.executeUpdate();
        }
    }
}