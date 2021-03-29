package twisk.exceptions;

public abstract class TwiskException extends Exception {
    /**
     * Constructeur de la classe abstraite des exceptions
     *
     * @param message
     */
    public TwiskException(String message) {
        super(message);
    }
}
