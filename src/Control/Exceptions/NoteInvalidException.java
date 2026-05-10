package Control.Exceptions; // Package mis à jour pour correspondre à src/control/exceptions


public class NoteInvalidException extends InputException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut avec un message générique.
     */
    public NoteInvalidException() {
        super("Erreur : La note doit être une valeur numérique comprise entre 0 et 20.");
    }

    /**
     * Constructeur permettant de définir un message d'erreur spécifique.
     * @param message Le message détaillant l'erreur de saisie de la note.
     */
    public NoteInvalidException(String message) {
        super(message);
    }
}