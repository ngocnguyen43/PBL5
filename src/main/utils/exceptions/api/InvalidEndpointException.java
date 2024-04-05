package main.utils.exceptions.api;

import main.utils.enums.ErrorCodes;
import main.utils.enums.ErrorStatusCodes;
import main.utils.exceptions.Exception;

public class InvalidEndpointException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidEndpointException() {
        this.errorCode = ErrorCodes.InvalidEndpointException.getValue();
        this.statusCode = ErrorStatusCodes.InvalidEndpointException.getValue();
        this.message = "Not Found";
    }
}
