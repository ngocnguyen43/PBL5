package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        this.errorCode = ErrorCodes.InvalidCredentialsException;
        this.statusCode = ErrorStatusCodes.InvalidCredentialsException;
        this.message = message;
    }
}
