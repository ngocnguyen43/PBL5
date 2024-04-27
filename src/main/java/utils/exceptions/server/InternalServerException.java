package utils.exceptions.server;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;

public class InternalServerException extends Exception {
    public InternalServerException() {
        this.message = "Internal Server Error";
        this.errorCode = ErrorCodes.InternalServerException;
        this.statusCode = ErrorStatusCodes.InternalServerException;
    }
}
