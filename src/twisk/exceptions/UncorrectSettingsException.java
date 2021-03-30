package twisk.exceptions;

public class UncorrectSettingsException extends Exception {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'utilisateur saisie des paramètres incorrects (Délai et écart)
     *
     * @param message
     */
    public UncorrectSettingsException(String message) {
        super(message);
    }
}
