package Control.Exceptions; // Package corrigé selon l'arborescence src/control/exceptions


public class IncorrectCaptchaException extends InputException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut avec un message générique.
     */
    public IncorrectCaptchaException() {
        super("Erreur : Le code Captcha est incorrect. Veuillez réessayer.");
    }

    /**
     * Constructeur permettant de définir un message d'erreur spécifique.
     * @param message Le message détaillant l'erreur de Captcha.
     */
    public IncorrectCaptchaException(String message) {
        super(message);
    }
}
