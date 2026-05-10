package Control.Exceptions; // Package corrigé selon l'arborescence src/control/exceptions


public class IDInvalidException extends InputException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut avec un message générique.
     */
    public IDInvalidException() {
        super("Erreur : L'identifiant saisi est invalide.");
    }

    /**
     * Constructeur permettant de définir un message d'erreur spécifique.
     * @param message Le message détaillant l'erreur d'ID.
     */
    public IDInvalidException(String message) {
        super(message);
    }
}
