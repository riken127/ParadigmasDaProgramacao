package exceptions;

public class InvalidIndexException extends Exception{
    public InvalidIndexException() {
    }

    public InvalidIndexException(String message) {
        super(message);
    }
}
