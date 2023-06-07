package exceptions;

public class InvalidProjectNameException extends Exception{
    public InvalidProjectNameException() {
    }

    public InvalidProjectNameException(String message) {
        super(message);
    }
}
