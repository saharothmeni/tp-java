package Control.security; // Package mis à jour pour correspondre à votre structure réelle

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Cette classe implémente l'algorithme de hachage SHA-256 en utilisant le patron 
 * de conception Singleton pour le système d'authentification.
 * @author SGI
 */
public class SHA256 {
    
    private static SHA256 instance;

    // Constructeur privé pour le Singleton
    private SHA256() {}

    /**
     * Retourne l'instance unique de la classe SHA256.
     */
    public static SHA256 getInstance() {
        if (instance == null) {
            instance = new SHA256();
        }
        return instance;
    }

    /**
     * Convertit un tableau de bytes en une chaîne hexadécimale.
     */
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Hache une chaîne de caractères en utilisant l'algorithme SHA-256.
     * @param input La chaîne à hacher (ex: mot de passe).
     * @return Le haché sous forme hexadécimale.
     */
    public String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(input.getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}