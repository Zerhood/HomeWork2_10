package exceptions;

public class NoSuchElementEx extends RuntimeException {
    public NoSuchElementEx(String message) {
        super(message);
    }
}