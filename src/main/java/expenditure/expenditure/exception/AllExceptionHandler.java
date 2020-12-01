package expenditure.expenditure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(ConsumptionNotFoundException.class)
    public ResponseEntity<?> handleConsumptionNotFoundException
            (ConsumptionNotFoundException exception, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(YourConsumptionNotFoundException.class)
    public ResponseEntity<?> handleYourConsumptionNotFoundException
            (YourConsumptionNotFoundException exception, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException
            (UserNotFoundException exception, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<?> handleUserRoleNotFoundException
            (UserRoleNotFoundException exception, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);

    }
}