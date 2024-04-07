package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;

public class RegistrationFailedException extends Exception {
    public RegistrationFailedException() {
        this.message = "Register Failed";
        this.errorCode = ErrorCodes.RegistrationFailedException;
        this.statusCode = ErrorStatusCodes.RegistrationFailedException;
    }
}
