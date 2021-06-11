package task4.exception;

/**
 * Exception thrown when authentication credentials are invalid.
 *
 * @author Dmitrii_Mishenev
 */
public class InvalidAuthCredentialsException extends IllegalArgumentException {
    private static final String MESSAGE_TEMPLATE = "Invalid login or password passed. Login:%s, Password:%s";
    public InvalidAuthCredentialsException(String login, String password) {
        super(String.format(MESSAGE_TEMPLATE, login, password));
    }
}