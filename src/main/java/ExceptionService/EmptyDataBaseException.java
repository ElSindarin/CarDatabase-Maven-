package ExceptionService;
public class EmptyDataBaseException extends Exception {
    public EmptyDataBaseException(String message) {
        super(message);
    }
}
