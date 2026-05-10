package Control.Exceptions; // Package mis à jour selon l'arborescence src/control/exceptions


public class PasswordInvalidException extends InputException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut avec un message générique.
     */
    public PasswordInvalidException() {
        super("Erreur : Le mot de passe est invalide ou trop court.");
    }

    /**
     * Constructeur permettant de définir un message d'erreur spécifique.
     * @param message Le message détaillant l'erreur du mot de passe.
     */
    public PasswordInvalidException(String message) {
        super(message);
    }
}