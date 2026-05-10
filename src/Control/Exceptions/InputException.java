package Control.Exceptions; // Package mis à jour selon l'arborescence src/control/exceptions


public class InputException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut.
     */
    public InputException() {
        super("Erreur de saisie détectée.");
    }

    /**
     * Constructeur permettant de passer un message d'erreur spécifique 
     * qui sera affiché à l'utilisateur via JOptionPane.
     * @param message Le message d'erreur détaillé.
     */
    public InputException(String message) {
        super(message);
    }
}
