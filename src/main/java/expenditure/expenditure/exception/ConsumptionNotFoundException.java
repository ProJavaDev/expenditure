package expenditure.expenditure.exception;

public class ConsumptionNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConsumptionNotFoundException(String message) {
        super(message);
    }
}

