package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.InvalidCredentialsException;
        this.statusCode = ErrorStatusCodes.InvalidCredentialsException;
    }
}
