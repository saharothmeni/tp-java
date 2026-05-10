package Control.tools; // Package mis à jour pour correspondre à src/Control/tools

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Cette interface présente les méthodes utilitaires utilisées dans les interfaces graphiques.
 */
public interface Generator {

    /**
     * Génère une chaîne de caractères aléatoire (utilisée notamment pour le Captcha).
     */
    public static String randStr() {
        String alphabets = "AAzErTyUiOpMlKjhgFDsQWxVbj7856945";
        StringBuilder sb = new StringBuilder();
        Random r = new Random(); // Sorti de la boucle pour plus d'efficacité
        
        for (int i = 0; i < 7; i++) {
            int x = r.nextInt(alphabets.length());
            sb.append(alphabets.charAt(x));
        }
        return sb.toString();
    }

    /**
     * Retourne l'heure actuelle au format HH:mm:ss.
     */
    public static String updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}