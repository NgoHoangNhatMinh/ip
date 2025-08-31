package lux.exception;

/**
 * Custom exception for the chat bot
 */
public class LuxException extends Exception {
    public LuxException(String err) {
        super(err);
    }
}
