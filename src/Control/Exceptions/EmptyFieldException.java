package Control.Exceptions; // Package mis à jour selon l'image image_4c8ad4.png

/**
 * Exception levée lorsqu'un champ de saisie obligatoire est laissé vide.
 * @author SGI
 */
public class EmptyFieldException extends InputException {

    private static final long serialVersionUID = 1L;

    // Constructeur par défaut
    public EmptyFieldException() {
        super("Erreur : Un ou plusieurs champs sont vides.");
    }

    // Constructeur avec message personnalisé
    public EmptyFieldException(String message) {
        super(message);
    }
}
