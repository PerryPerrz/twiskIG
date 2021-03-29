package twisk.exceptions;

public class ArcAlreadyCreateException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on essai de créer un arc déjà créer
     *
     * @param message
     */
    public ArcAlreadyCreateException(String message) {
        super(message);
    }
}
