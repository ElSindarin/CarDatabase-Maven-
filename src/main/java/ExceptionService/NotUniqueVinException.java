package ExceptionService;

public class NotUniqueVinException extends Exception {
    public NotUniqueVinException(String message) {
        super(message);
    }
}
