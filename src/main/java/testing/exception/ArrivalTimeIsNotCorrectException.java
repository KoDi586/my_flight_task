package testing.exception;

public class ArrivalTimeIsNotCorrectException extends RuntimeException {

    public ArrivalTimeIsNotCorrectException(String message) {
        super(message);
    }
}
