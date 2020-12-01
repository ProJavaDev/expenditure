package expenditure.expenditure.exception;

public class YourConsumptionNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public YourConsumptionNotFoundException(String message) {
        super(message);
    }
}
