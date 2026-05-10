package Control.security; // Package mis à jour selon l'image image_4c86f3.png

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Cette classe présente l'algorithme de cryptage AES128 
 * pour l'utiliser dans le système de messagerie.
 * @author SGI
 */
public class AES128 {

    /**
     * Ajuste la taille de la clé à 16 caractères (128 bits) pour AES.
     */
    public static String padKey(String key) {
        int keyLength = 16; 
        StringBuilder paddedKey = new StringBuilder(key);
        if (paddedKey.length() < keyLength) {
            int missingLength = keyLength - paddedKey.length();
            for (int i = 0; i < missingLength; i++) {
                paddedKey.append("0");
            }
        } else if (paddedKey.length() > keyLength) {
            return paddedKey.substring(0, keyLength);
        }
        return paddedKey.toString();
    }

    /**
     * Crypte un texte en utilisant la clé fournie.
     */
    public static String encrypt(String key, String plaintext) throws Exception {
        key = padKey(key);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Décrypte un texte crypté en utilisant la clé correspondante.
     */
    public static String decrypt(String key, String encryptedText) throws Exception {
        key = padKey(key);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }    
}