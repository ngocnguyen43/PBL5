package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;

public class InvalidEndpointException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidEndpointException() {
        this.errorCode = ErrorCodes.InvalidEndpointException;
        this.statusCode = ErrorStatusCodes.InvalidEndpointException;
        this.message = "Not Found";
    }
}
