package Control.Exceptions; // Package mis à jour selon votre arborescence



public class GenderInvalidException extends InputException {

    private static final long serialVersionUID = 1L;

    // Constructeur par défaut
    public GenderInvalidException() {
        super("Erreur : Genre invalide (M ou F uniquement).");
    }

    // Constructeur avec message personnalisé
    public GenderInvalidException(String message) {
        super(message);
    }
}