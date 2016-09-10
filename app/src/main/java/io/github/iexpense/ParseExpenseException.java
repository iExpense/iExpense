package io.github.iexpense;

public class ParseExpenseException extends Exception {
    public ParseExpenseException() {
        super();
    }

    public ParseExpenseException(String message) {
        super(message);
    }

    public ParseExpenseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseExpenseException(Throwable cause) {
        super(cause);
    }
}
