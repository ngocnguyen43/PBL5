package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;

public class DatabaseOperationException extends Exception {
    public DatabaseOperationException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.InvalidCredentialsException;
        this.statusCode = ErrorStatusCodes.InvalidCredentialsException;
    }
}
