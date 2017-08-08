package nl.quintor.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(final String message) {
        super(message);
    }

}
