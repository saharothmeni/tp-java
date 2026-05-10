package Control.datacontrol; // Mis à jour pour correspondre à l'arborescence src/Control/datacontrol

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe suit le patron de conception 'Singleton' pour assurer 
 * qu'une seule connexion à la base de données est ouverte durant l'exécution.
 */
public class Singleton_Dbconnect {
    
    private static Connection connect;

    // Constructeur privé pour empêcher l'instanciation externe
    private Singleton_Dbconnect() {
        try {
            // Chargement du pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Établissement de la connexion
            // Note : Vérifiez que le nom de la base 'ProjetJava' est toujours correct
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_ecole", "root", "sahar+1234");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Singleton_Dbconnect.class.getName()).log(Level.SEVERE, "Pilote JDBC non trouvé", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Singleton_Dbconnect.class.getName()).log(Level.SEVERE, "Erreur de connexion à la base de données", ex);
        }
    }

    /**
     * Retourne l'instance unique de la connexion.
     * Si la connexion n'existe pas encore, elle est créée.
     */
    public static Connection getInstance() {
        try {
            // On vérifie si la connexion est nulle OU si elle a été fermée
            if (connect == null || connect.isClosed()) {
                new Singleton_Dbconnect();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Singleton_Dbconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connect;
    }
}