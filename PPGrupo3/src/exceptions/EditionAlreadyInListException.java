package exceptions;

public class EditionAlreadyInListException extends Exception{
	public EditionAlreadyInListException() {

	}
	public EditionAlreadyInListException(String message) {
		super(message);
	}
}
